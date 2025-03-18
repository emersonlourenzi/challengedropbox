package br.com.challengedropbox.service.file;

import br.com.challengedropbox.commons.exceptions.file.ErrorDeleteFileException;
import br.com.challengedropbox.commons.exceptions.file.ErrorSaveFileException;
import br.com.challengedropbox.commons.exceptions.file.ErrorVerifyFileException;
import br.com.challengedropbox.commons.exceptions.ftp.ErrorConectFtpException;
import br.com.challengedropbox.commons.exceptions.ftp.ErrorDisconnectFtpException;
import br.com.challengedropbox.commons.exceptions.user.ErrorUserNotFoundException;
import br.com.challengedropbox.mapper.file.FileEntityToUploadResponseMapper;
import br.com.challengedropbox.model.file.request.FileDeleteRequest;
import br.com.challengedropbox.model.file.response.FileResponse;
import br.com.challengedropbox.model.file.response.FileUploadResponse;
import br.com.challengedropbox.repository.file.FileRepository;
import br.com.challengedropbox.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static br.com.challengedropbox.mapper.file.FileEntityMapper.toFileEntityMapper;
import static br.com.challengedropbox.mapper.file.FileResponseMapper.INSTANCE;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.user}")
    private String user;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.port}")
    private Integer port;
    private final FTPClient ftpClient;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    private void connectServerFTP() {
        try {
            ftpClient.connect(host, port);
            ftpClient.login(user, password);
            ftpClient.enterLocalPassiveMode();
            log.info("Conectado ao servidor FTP: {}", host);
        } catch (IOException e) {
            log.error("Erro ao conectar ao servidor FTP: {}", e.getMessage(), e);
            throw new ErrorConectFtpException();
        }
    }

    private void disconnectFTP() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                log.info("Desconectado do servidor FTP.");
            }
        } catch (IOException e) {
            log.error("Erro ao desconectar do FTP: {}", e.getMessage(), e);
            throw new ErrorDisconnectFtpException();
        }
    }

    public FileUploadResponse uploadFile(MultipartFile file, String idUser) {
        try {
            connectServerFTP();

            if (!verifyUserExists(idUser)) {
                throw new ErrorUserNotFoundException();
            }

            enterDirectoryUser(idUser);

            String newFileName = generateUniqueFileName(Objects.requireNonNull(file.getOriginalFilename()));
            boolean success = ftpClient.storeFile(newFileName, file.getInputStream());

            if (success) {
                log.info("Arquivo '{}' salvo com sucesso para o usuário '{}'", newFileName, idUser);
                return saveUpload(file, newFileName, idUser);
            } else {
                throw new ErrorSaveFileException();
            }

        } catch (IOException e) {
            log.error("Erro no upload do arquivo: {}", e.getMessage(), e);
            throw new ErrorSaveFileException();
        } finally {
            disconnectFTP();
        }
    }

    private void enterDirectoryUser(String idUser) {
        try {
            boolean directoryExistis = ftpClient.changeWorkingDirectory(idUser);
            if (!directoryExistis) {
                ftpClient.makeDirectory(idUser);
                ftpClient.changeWorkingDirectory(idUser);
            }
        } catch (IOException e) {
            log.error("Erro ao tentar entrar do arquivo: {}", e.getMessage(), e);
            throw new ErrorSaveFileException();
        }
    }

    private String generateUniqueFileName(String originalName) {
        int counter = 0;
        String baseName = originalName;
        String extension = "";

        if (originalName.contains(".")) {
            baseName = originalName.substring(0, originalName.lastIndexOf('.'));
            extension = originalName.substring(originalName.lastIndexOf('.'));
        }

        while (fileExists("(" + counter + ")" + baseName + extension)) {
            counter++;
        }
        return "(" + counter + ")" + baseName + extension;
    }

    private boolean fileExists(String fileName) {
        try {
            String[] files = ftpClient.listNames();
            if (files != null) {
                for (String file : files) {
                    if (file.equalsIgnoreCase(fileName)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            log.error("Erro ao verificar se o arquivo existe no FTP: {}", e.getMessage(), e);
            throw new ErrorVerifyFileException();
        }
        return false;
    }

    private boolean verifyUserExists(String idUser) {
        return userRepository.findById(idUser)
            .map(user -> Boolean.TRUE)
            .orElse(false);
    }

    private FileUploadResponse saveUpload(MultipartFile file, String fileName, String idUser) {
        return Optional.ofNullable(file)
            .map(fileMultipart -> toFileEntityMapper(file, fileName, idUser))
            .map(fileRepository::save)
            .map(FileEntityToUploadResponseMapper::toFileUploadResponse)
            .orElseThrow();
    }

    public List<FileResponse> listFilesUser(String idUser, Integer page, Integer size) {
        int currentPage = Optional.ofNullable(page)
            .orElse(1) - 1;
        int pageSize = Optional.ofNullable(size)
            .orElse(10);

        try {

            if (!verifyUserExists(idUser)) {
                throw new ErrorUserNotFoundException();
            }

            connectServerFTP();

            enterDirectoryUser(idUser);

            List<FileResponse> allFilesUser = INSTANCE.toFileResponseList(ftpClient.listFiles());

            return paginateList(allFilesUser, currentPage, pageSize);
        } catch (IOException e) {
            log.error("Erro ao verificar se o arquivo existe no FTP: {}", e.getMessage(), e);
            throw new ErrorVerifyFileException();
        } finally {
            disconnectFTP();
        }
    }

    private List<FileResponse> paginateList(List<FileResponse> files, int page, int size) {
        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, files.size());

        if (fromIndex >= files.size()) {
            return Collections.emptyList();
        }

        return files.subList(fromIndex, toIndex);
    }

    public void deleteFilesUser(FileDeleteRequest request) {

        if (!verifyUserExists(request.getIdUser())) {
            throw new ErrorUserNotFoundException();
        }

        connectServerFTP();

        enterDirectoryUser(request.getIdUser());

        request.getNameFiles()
            .forEach(name -> {
                try {
                    boolean deleted = ftpClient.deleteFile(name);
                    if (deleted) {
                        fileRepository.deleteByNameFile(name);
                    } else {
                        throw new ErrorDeleteFileException(name);
                    }
                } catch (IOException e) {
                    throw new ErrorDeleteFileException(name);
                }
            });

        disconnectFTP();
    }

}

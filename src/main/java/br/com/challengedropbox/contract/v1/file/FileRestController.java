package br.com.challengedropbox.contract.v1.file;

import br.com.challengedropbox.model.file.request.FileDeleteRequest;
import br.com.challengedropbox.model.file.response.FileResponse;
import br.com.challengedropbox.model.file.response.FileUploadResponse;
import br.com.challengedropbox.service.file.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/files")
@Tag(name = "File")
public class FileRestController {

    private final FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Upload de arquivos")
    public FileUploadResponse uploadFile(@RequestParam MultipartFile file,
                                         @RequestParam String idUser) {
        return fileService.uploadFile(file, idUser);
    }

    @GetMapping("/user")
    @Operation(description = "Lista de arquivos do usuário")
    public List<FileResponse> listFilesUser(@RequestParam String idUser,
                                            @RequestParam Integer page,
                                            @RequestParam Integer size) {
        return fileService.listFilesUser(idUser, page, size);
    }

    @DeleteMapping
    @Operation(description = "Deletar arquivos do usuário")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFilesUser(@RequestBody FileDeleteRequest request) {
        fileService.deleteFilesUser(request);
    }

}

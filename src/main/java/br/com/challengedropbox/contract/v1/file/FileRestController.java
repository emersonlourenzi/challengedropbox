package br.com.challengedropbox.contract.v1.file;

import br.com.challengedropbox.model.file.response.FileUploadResponse;
import br.com.challengedropbox.service.file.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/files")
@Tag(name = "File")
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/upload")
    @Operation(description = "Upload de arquivos")
    public FileUploadResponse uploadFile(@RequestParam @Schema(type = "string", format = "binary") MultipartFile file,
                                         @RequestParam String userId) {
        return fileService.uploadFile(file, userId);
    }

}

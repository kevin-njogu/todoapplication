package com.kevin.todo.todo_application.reconciler.uploader.controller;

import com.kevin.todo.todo_application.reconciler.uploader.dto.UploadResponseDto;
import com.kevin.todo.todo_application.reconciler.uploader.service.UploaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/uploader")
@RequiredArgsConstructor
public class FileUploaderController {
    private final UploaderService uploaderService;
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<UploadResponseDto> upload(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body(UploadResponseDto.builder().message("File is empty").build());
            }
            if (type == null) {
                return ResponseEntity.badRequest().body(UploadResponseDto.builder().message("Type is null").build());
            }
            return ResponseEntity.ok(uploaderService.saveFile(file, type));
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(UploadResponseDto.builder().message(e.getMessage()).build());
        }
    }

}

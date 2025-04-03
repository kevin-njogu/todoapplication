package com.kevin.todo.todo_application.reconciler.controller;

import com.kevin.todo.todo_application.reconciler.dto.UploadResponseDto;
import com.kevin.todo.todo_application.reconciler.service.upload.UploaderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/reconcile")
public class FileUploaderController {

    private UploaderServiceImpl uploaderService;

    public FileUploaderController(UploaderServiceImpl uploaderService) {
        this.uploaderService = uploaderService;
    }

    @PostMapping(value = "/uploadstatement", consumes = "multipart/form-data")
    public ResponseEntity<?> uploadstatement(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        try {
            if (file.isEmpty() || file == null) {
                throw new IllegalArgumentException("Statement type is missing");
            }
            if(!type.equalsIgnoreCase("equitybank") && !type.equalsIgnoreCase("workpayequity")) {
                throw new IllegalArgumentException("Invalid statement type");
            }

            if ("equitybank".equals(type)) {
                uploaderService.uploadEquityBankStatement(file);
            }else if ("workpayequity".equals(type)) {
                uploaderService.uploadWorkpayEquityStatement(file);
            }

            return ResponseEntity.ok(UploadResponseDto.builder().message("File uploaded successfully").build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(UploadResponseDto.builder().message(e.getMessage()).build());
        }
    }

}

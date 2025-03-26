package com.kevin.todo.todo_application.reconciler.uploader.service;

import com.kevin.todo.todo_application.reconciler.uploader.dto.UploadResponseDto;
import com.kevin.todo.todo_application.reconciler.uploader.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.uploader.repository.WorkpayEquityTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploaderService {

    private final EquityBankTransactionRepository equityBankTransactionRepository;
    private final WorkpayEquityTransactionRepository workpayEquityTransactionRepository;
    private final UploaderUtils uploaderUtils;

    public UploadResponseDto saveFile(MultipartFile file, String type)  {
        try (InputStream inputStream = file.getInputStream()) {
            if ("equitybank".equals(type)) {
                equityBankTransactionRepository.saveAll(uploaderUtils.processEquityBankStatement(inputStream));
            } else if ("workpayequity".equals(type)) {
                workpayEquityTransactionRepository.saveAll(uploaderUtils.processWorkpayEquityStatement(inputStream));
            }
            return UploadResponseDto.builder().message("file uploaded successfully").build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}


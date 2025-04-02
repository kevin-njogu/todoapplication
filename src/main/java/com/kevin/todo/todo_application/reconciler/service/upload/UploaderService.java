package com.kevin.todo.todo_application.reconciler.service.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploaderService {

    void uploadEquityBankStatement(MultipartFile file) throws IOException;

    void uploadWorkpayEquityStatement(MultipartFile file) throws IOException;

}


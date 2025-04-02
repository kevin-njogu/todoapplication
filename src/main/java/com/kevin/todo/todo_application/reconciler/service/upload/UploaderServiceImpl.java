package com.kevin.todo.todo_application.reconciler.service.upload;

import com.kevin.todo.todo_application.reconciler.repository.EquityBankTransactionRepository;
import com.kevin.todo.todo_application.reconciler.repository.WorkpayEquityTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class UploaderServiceImpl implements UploaderService {

    @Autowired
    private  EquityBankTransactionRepository equityBankTransactionRepository;

    @Autowired
    private WorkpayEquityTransactionRepository workpayEquityTransactionRepository;

    @Autowired
    private UploaderUtils uploaderUtils;

    @Override
    public void uploadEquityBankStatement(MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IOException("File is empty");
        try {
            InputStream inputStream = uploaderUtils.convertFileToInputStream(file);
            equityBankTransactionRepository.saveAll(uploaderUtils.processEquityBankStatement(inputStream));
            inputStream.close();
        }catch (IOException e) {
            throw new IOException("Error converting file to input stream in equity bank statement");
        }
    }

    @Override
    public void uploadWorkpayEquityStatement(MultipartFile file) throws IOException {
        if (file.isEmpty()) throw new IOException("File is empty");
        try {
            InputStream inputStream = uploaderUtils.convertFileToInputStream(file);
            workpayEquityTransactionRepository.saveAll(uploaderUtils.processWorkpayEquityStatement(inputStream));
            inputStream.close();
        }catch (IOException e) {
            throw new IOException("Error converting file to input stream in workpay equity statement");
        }
    }
}

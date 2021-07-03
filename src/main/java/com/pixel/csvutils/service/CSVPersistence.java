package com.pixel.csvutils.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CSVPersistence {
    void save(MultipartFile multipartFile) throws IOException;
}

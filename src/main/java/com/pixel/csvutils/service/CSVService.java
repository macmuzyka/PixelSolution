package com.pixel.csvutils.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

interface CSVService {
    void save(MultipartFile multipartFile) throws IOException;
}

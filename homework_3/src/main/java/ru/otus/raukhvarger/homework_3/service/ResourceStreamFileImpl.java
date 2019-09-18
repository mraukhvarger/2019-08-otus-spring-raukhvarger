package ru.otus.raukhvarger.homework_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ResourceStreamFileImpl implements ResourceStream {

    private final String fileName;

    @Autowired
    public ResourceStreamFileImpl(@Value("${answerFile}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public InputStream getStream() {
        return ResourceStreamFileImpl.class.getClassLoader().getResourceAsStream(fileName);
    }
}

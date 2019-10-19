package ru.otus.raukhvarger.homework_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_3.config.ValueConfig;

import java.io.InputStream;
import java.util.Locale;

@Service
public class ResourceStreamFileImpl implements ResourceStream {

    private final ValueConfig config;

    @Autowired
    public ResourceStreamFileImpl(ValueConfig config) {
        this.config = config;
    }

    @Override
    public InputStream getStream() {
        String fileName = Locale.getDefault().getLanguage().equals("ru") ? config.getAnswerFileRu() : config.getAnswerFile();
        return ResourceStreamFileImpl.class.getClassLoader().getResourceAsStream(fileName);
    }
}

package ru.otus.raukhvarger.homework_1.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ResourceStreamStringImpl implements ResourceStream {

    private final String data;

    public ResourceStreamStringImpl(String data) {
        this.data = data;
    }

    @Override
    public InputStream getStream() {
        return new ByteArrayInputStream(data.getBytes());
    }
}

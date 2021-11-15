package com.pixel.controller;

import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 12.11.2021
 * TODO:
 */

class TestObject {
    private boolean connected;
    private String length;
    private List<String> errors;

    TestObject(final boolean connected, final String length, final List<String> errors) {
        this.connected = connected;
        this.length = length;
        this.errors = errors;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getLength() {
        return length;
    }

    public List<String> getErrors() {
        return errors;
    }
}

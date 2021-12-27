package ru.rseu.lovkin.mergesort.view;

import lombok.Data;

import java.util.Map;

@Data
public class TextFieldsResult {
    private Statuses status;
    private Map<String, Object> resultMap;
    private String message;
}

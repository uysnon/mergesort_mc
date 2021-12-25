package ru.rseu.lovkin.mergesort.model.params;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class ModelParamsManager {
    private static  final String MODEL_PARAMS_FILE_PATH = "src/main/resources/params.json";

    public ModelParams read(){
        ObjectMapper objectMapper = new ObjectMapper();
        ModelParams modelParams = null;
        try {
            modelParams = objectMapper.readValue(getFile(), ModelParams.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelParams;
    }

    public void write(ModelParams modelParams){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(getFile(), modelParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile(){
        return new File(MODEL_PARAMS_FILE_PATH);
    }
}

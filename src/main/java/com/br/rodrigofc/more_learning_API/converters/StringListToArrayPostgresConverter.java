package com.br.rodrigofc.more_learning_API.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import java.util.Arrays;
import java.util.List;

public class StringListToArrayPostgresConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> attribute){
        if(attribute == null || attribute.isEmpty()) return null;

        return "{" + String.join(",", attribute) + "}";
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData){
        if(dbData == null || dbData.length() < 2) return List.of();

        String trimmed = dbData.substring(1, dbData.length() - 1);

        return Arrays.asList(trimmed.split(","));
    }
}

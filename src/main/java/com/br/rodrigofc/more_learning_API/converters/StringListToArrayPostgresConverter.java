package com.br.rodrigofc.more_learning_API.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

import java.util.Arrays;
import java.util.List;

@Convert
public class StringListToArrayPostgresConverter implements AttributeConverter<List<String>, String[]> {

    @Override
    public String[] convertToDatabaseColumn(List<String> attribute){
        if(attribute == null) return null;

        return attribute.toArray(new String[0]);
    }

    @Override
    public List<String> convertToEntityAttribute(String[] dbData){
        if(dbData == null) return List.of();
        return Arrays.asList(dbData);
    }
}

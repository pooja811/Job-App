package com.app.entity;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonToListConverter implements AttributeConverter<List<String>, String> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		try {
			return attribute == null ? null : objectMapper.writeValueAsString(attribute);
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert list to JSON string", e);
		}
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		try {
			return dbData == null ? null : objectMapper.readValue(dbData, new TypeReference<List<String>>() {
			});
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert JSON string to list", e);
		}
	}
}


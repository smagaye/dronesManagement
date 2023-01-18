package com.musala.dronesmanagement.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Formatter {
    
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String asJsonString(final Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.backend;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnitTestFixture {

    public static String toJson(Object object) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(object);
    }

}

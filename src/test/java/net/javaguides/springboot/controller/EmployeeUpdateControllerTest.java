package net.javaguides.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.JsonParseUtil;
import net.javaguides.springboot.model.UpdateEmployeeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

public class EmployeeUpdateControllerTest {


    protected ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Test
    public void employeeUpdateTest() throws Exception {
        var request = objectMapper
                .readValue(JsonParseUtil.getJson(
                        "vova/successUpdate.json"),
                        UpdateEmployeeRequest.class);
        request.setId(14);

        System.out.println(objectMapper.writeValueAsString(request));

    }
}

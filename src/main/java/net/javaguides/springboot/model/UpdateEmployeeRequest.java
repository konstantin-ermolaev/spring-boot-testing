package net.javaguides.springboot.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateEmployeeRequest {
    private int id;
    private String firstName;
    private String secondName;
}

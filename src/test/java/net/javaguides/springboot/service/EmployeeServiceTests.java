package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.impl.EmployeeServiceImpl;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        employee = Employee.builder()
                .id(1L)
                .firstName("Suresh")
                .lastName("Mkrtchan")
                .email("suresh@gmail.com")
                .build();
    }

    // JUnit test for
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {

        BDDMockito
                .given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        BDDMockito
                .given(employeeRepository.save(employee))
                .willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        Employee savedEmployee = employeeService.savedEmployee(employee);

        System.out.println(savedEmployee);

        assertThat(savedEmployee).isNotNull();
    }


    @Test
    public void givenExisitingEmail_whenSaveEmployee_thenThrowException() {

        BDDMockito
                .given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.savedEmployee(employee);
        });
        verify(employeeRepository, never()).save(any(Employee.class));
    }


    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Tony")
                .lastName("Stark")
                .email("stark@gmail.com")
                .build();
        BDDMockito
                .given(employeeRepository.findAll())
                .willReturn(List.of(employee, employee1));

        List<Employee> employeeList = employeeService.getAllEmployees();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }


    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){

        BDDMockito
                .given(employeeRepository.findAll())
                .willReturn(Collections.emptyList());

        List<Employee> employeeList = employeeService.getAllEmployees();

        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }


    @Test
    public void givnEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        BDDMockito
                .given(employeeRepository.findById(1L))
                .willReturn(Optional.of(employee));

        Employee savedEmployee = employeeService.getEmployeeById(1L).get();

        assertThat(savedEmployee).isNotNull();
    }



}

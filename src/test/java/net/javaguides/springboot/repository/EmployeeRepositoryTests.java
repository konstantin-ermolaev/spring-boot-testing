package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.Stream;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

   /* @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();
    }*/

    @DisplayName("test for save operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    @DisplayName("find all saved items")
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeesList(){

        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        List<Employee> employeeList = employeeRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList).size().isEqualTo(2);
    }


    @DisplayName("find item by id   ")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);

        Employee employeeDb = employeeRepository.findById(employee.getId()).get();

        assertThat(employeeDb).isNotNull();
    }

    @Test
    public void givenEmployeeEmial_whenFindByEmial_thenReturnEmployeeObject(){
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);

        Employee employeeDb = employeeRepository.findByEmail(employee.getEmail()).get();

        assertThat(employeeDb).isNotNull();
    }

    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdateEmployee(){
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("rameshka@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        assertThat(updatedEmployee.getEmail()).isEqualTo("rameshka@gmail.com");
    }


    @Test
    public void givenFirstNameAndLastName_whenFindByJpql_thenReturnEmployeeObject(){
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        Employee savedEmployee = employeeRepository.findByJpqlIndexParameter(firstName, lastName);

        assertThat(savedEmployee).isNotNull();
    }


    @Test
    public void givenEmloyeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();

        employeeRepository.save(employee);

        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();

        savedEmployee.setEmail("new@gmail.com");

        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        assertThat(updatedEmployee.getEmail()).isEqualTo("new@gmail.com");
    }

}

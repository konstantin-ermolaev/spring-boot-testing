package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    Optional<Employee> findByEmail (String email);

    // define custom query using JPQL with index params
    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJpqlIndexParameter(String firstName, String lastName);

    // define custom query using JPQL with named params
    @Query("select e from Employee e where e.firstName =:firstName and e.lastName =:lastName")
    Employee findByJpqlNamedParameter(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // define custom query using native SQL with index params
    @Query(value = "select * from employee e where e.first_name = ?1 and e.last_name = ?2", nativeQuery = true)
    Employee findBySqlIndexParameter(String firstName, String lastName);

    // define custom query using native SQL with named params
    @Query(value = "select * from employee e where e.first_name =:firstName and e.last_name =:lastName", nativeQuery = true)
    Employee findBySqlNamedParameter(@Param("firstName") String firstName, @Param("lastName") String lastName);
}

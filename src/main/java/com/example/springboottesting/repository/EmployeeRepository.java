package com.example.springboottesting.repository;

import com.example.springboottesting.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//SimpleJpaRepository used by JpaRepository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
	//custom query using JPQL with index
	@Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
	Employee findByJPQL(String firstName, String lastName);

	//custom query using JPQL with named params
	@Query("select e from Employee e where e.firstName = :firstName and e.lastName = :lastName")
	Employee findByJPQLNamedParams(@Param("firstName") String firstName,@Param("lastName") String lastName);
}

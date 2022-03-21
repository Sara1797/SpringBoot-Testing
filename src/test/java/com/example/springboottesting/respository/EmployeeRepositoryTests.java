package com.example.springboottesting.respository;

import com.example.springboottesting.entity.Employee;
import com.example.springboottesting.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	private Employee employee;

	@BeforeEach
	public void setup(){
		employee = Employee.builder()
			.firstName("Ramesh")
			.lastName("Jain")
			.email("ramesh.jain@gmail.com")
			.build();
	}

	@DisplayName("JUnit - Save Employee")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

	//when
		Employee savedEmployee = employeeRepository.save(employee);

	//then
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);

	}

	//custom query using JPQL - Test
	@DisplayName("JUnit - Query By JPQL with index")
	@Test
	public void givenFirstNamrAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){

		employeeRepository.save(employee);
		String firstName = "Ramesh";
		String lastName = "Jain";

		//when
		Employee savedEmployee = employeeRepository.findByJPQL(firstName,lastName);


		//then
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);

	}

	//custom query using JPQL with named params Test
	@DisplayName("JUnit - Query By JPQL with Named Params")
	@Test
	public void givenFirstNameAndLastNameWithNamedParams_whenFindByJPQL_thenReturnEmployeeObject(){

		employeeRepository.save(employee);
		String firstName = "Ramesh";
		String lastName = "Jain";

		//when
		Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName,lastName);


		//then
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);

	}

}

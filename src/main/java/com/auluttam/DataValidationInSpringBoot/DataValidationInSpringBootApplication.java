package com.auluttam.DataValidationInSpringBoot;

import jakarta.validation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Set;


@SpringBootApplication
public class DataValidationInSpringBootApplication {

	public static void main(String[] args) {

	ApplicationContext ctx =   SpringApplication.run(DataValidationInSpringBootApplication.class, args);

	Student s1 = ctx.getBean(Student.class);
	s1.setName(null);
	s1.setDob(LocalDate.of(2025,12, 12));
	s1.setEmail("abc");
	s1.setRoll(200);

	StudentValidator(s1);
	System.out.println(s1);
	}


	public static void StudentValidator( Student s)
	{
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator =  vf.getValidator();
		Set<ConstraintViolation<Student>> violations = validator.validate(s);

		if(violations.isEmpty())
		{
			System.out.println("Valid Student");
		}
		else
		{
			System.out.println("Invalid Student");

			for(ConstraintViolation<Student> violation: violations)
			{
				System.out.println(violation.getMessage());
			}
		}

	}

}

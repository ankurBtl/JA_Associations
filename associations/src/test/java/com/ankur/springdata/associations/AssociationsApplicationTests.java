package com.ankur.springdata.associations;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.springdata.associations.manytomany.entities.Programmer;
import com.ankur.springdata.associations.manytomany.entities.Project;
import com.ankur.springdata.associations.manytomany.repos.ProgrammerRepository;
import com.ankur.springdata.associations.onetomany.entities.Customer;
import com.ankur.springdata.associations.onetomany.entities.PhoneNumber;
import com.ankur.springdata.associations.onetomany.repos.CustomerRepository;
import com.ankur.springdata.associations.onetoone.entities.License;
import com.ankur.springdata.associations.onetoone.entities.Person;
import com.ankur.springdata.associations.onetoone.repos.LicenseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsApplicationTests {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProgrammerRepository programmerRepository;
	@Autowired
	LicenseRepository licenseRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("Anubha");
		PhoneNumber ph1 = new PhoneNumber();
		ph1.setPhonenumber("9536511288");
		ph1.setType("Pre-Paid");

		PhoneNumber ph2 = new PhoneNumber();
		ph2.setPhonenumber("8888888888");
		ph2.setType("Pre-Paid");

		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);

		customerRepository.save(customer);
	}

	@Test
	@Transactional
	public void testReadCustomer() {
		Customer customer = customerRepository.findById(3).get();
		System.out.println(customer.getName());
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(n -> System.out.println(n.getPhonenumber()));
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerRepository.findById(3).get();
		customer.setName("Janett");
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(n -> n.setType("PostPaid"));

		customerRepository.save(customer);
	}

	@Test
	public void testDelete() {

		customerRepository.deleteAll();
	}

	@Test
	public void testmtomCreate() {
		Programmer programmer = new Programmer();
		programmer.setName("Anubha");
		programmer.setSal(90000);

		HashSet<Project> projects = new HashSet<Project>();
		Project project = new Project();
		project.setName("Web Automation");
		projects.add(project);

		programmer.setProjects(projects);

		programmerRepository.save(programmer);
	}

	@Test
	@Transactional
	public void testmtomRead() {

		System.out.println(programmerRepository.findById(2).get());
		System.out.println(programmerRepository.findById(2).get().getProjects());
	}
	
	@Test
	public void testOnetoOneCreate() {
		License license = new License();
		license.setType("Car");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());
		
		Person person = new Person();
		person.setAge(23);
		person.setFirstName("John");
		person.setLastName("Doe");
		
		license.setPerson(person);
		
		licenseRepository.save(license);
	}
}

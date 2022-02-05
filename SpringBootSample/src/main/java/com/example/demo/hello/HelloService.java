package com.example.demo.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

	@Autowired
	private HelloRepositry repositry;
	
	public Employ getEmploy(String id) {
		
		Map<String, Object> map = repositry.findById(id);
		
		String employeeId = (String) map.get("id");
		String name = (String) map.get("name");
		
		int age = (Integer) map.get("age");
		
		Employ employee = new Employ();
		
		employee.setEmployeeAge(age);
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(name);
		
		return employee;
	}
}

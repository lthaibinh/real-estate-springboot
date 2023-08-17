package com.demo.real_estate.request;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeRequest {
	private String name;
	private Long age = 0L;
	private String location;
	private String email;
	private String department;
	private List<String> addresses;
}

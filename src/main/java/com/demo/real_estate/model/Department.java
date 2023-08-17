package com.demo.real_estate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "tbl_department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	// map 1-1 bidirection: bidirect mới có thể xóa bảng chứa khóa ngoại
	// + xoa bang nay bang kia cung xoa theo
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "department")
	@JsonIgnoreProperties("department")
	private Employee employee;
	
	
}

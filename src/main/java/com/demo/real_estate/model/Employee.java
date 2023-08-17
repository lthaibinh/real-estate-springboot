package com.demo.real_estate.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.demo.real_estate.request.EmployeeRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	@NotNull(message = "Name should not be null")
	private String name;

	private Long age = 0L;

	private String location;

	private String email;

//	@Column(name="department")
//	private String department;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Timestamp createAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Timestamp updateAt;

	// one to one mapping in data jpa
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id", referencedColumnName = "id")

	private Department department;

	// one to many in data jpa
	// Many to One Có nhiều người ở 1 địa điểm.
	@JsonManagedReference
	@OneToMany( mappedBy = "employee",targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY ) // mappedBy = "employee",
	//private Set<Address> address = new HashSet<Address>();
	private List<Address> address = new ArrayList<Address>();
	
	
	
	
}

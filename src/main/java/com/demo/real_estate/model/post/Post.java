package com.demo.real_estate.model.post;

import com.demo.real_estate.model.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;

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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Column(length=9000)
	private String description;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryOfRealEstate categoryOfRealEstate;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private AddressOfRealEstate addressOfRealEstate;
	
	@OneToOne(cascade = CascadeType.ALL) // DETACH 
	@JoinColumn(name = "info_id")
	private InfoOfRealEstate infoOfRealEstate;
}

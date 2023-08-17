package com.demo.real_estate.model.address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.demo.real_estate.model.post.ImageOfRealEstate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "ad_province")
public class Province {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String _name;
	private String _code;
	
	
//	@ToString.Exclude
//	@JsonManagedReference
//	@OneToMany( 
//		mappedBy = "province",
//		cascade = CascadeType.ALL,
//		fetch = FetchType.EAGER,
//		orphanRemoval = true ) // mappedBy = "employee", FetchType=lazy : err when update
//	private List<District> district = new ArrayList<District>();
	
}

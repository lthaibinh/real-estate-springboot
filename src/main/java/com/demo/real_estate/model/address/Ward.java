package com.demo.real_estate.model.address;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "ad_ward")
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String _name;
	private String _prefix;
	
	@ToString.Exclude
	//@JsonBackReference
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_province_id")
	private Province province;
	
	@ToString.Exclude
	//@JsonBackReference
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "_district_id")
	private District district;
	
}

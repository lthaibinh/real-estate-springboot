package com.demo.real_estate.model.post;

import com.demo.real_estate.dto.RoleDTO;
import com.demo.real_estate.model.Employee;
import com.demo.real_estate.model.address.District;
import com.demo.real_estate.model.address.Province;
import com.demo.real_estate.model.address.Street;
import com.demo.real_estate.model.address.Ward;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AddressOfRealEstate")
@Builder
public class AddressOfRealEstate {
	
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // lazy enhance performance
	private Street street;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // lazy enhance performance
	private Ward ward ;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // lazy enhance performance
	private District district;
	
	//private String province;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // lazy enhance performance
	private Province province;
	
}

package com.demo.real_estate.model.post;

import java.util.ArrayList;
import java.util.List;

import com.demo.real_estate.model.Address;
import com.demo.real_estate.model.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter

@Data
@Entity
@Table(name = "InfoOfRealEstate")
public class InfoOfRealEstate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int area;
	private int price;
	private String monetary_unit ;
	private String legal_documentation;  
	private int num_bedrooms;
	private int num_bathrooms;
	
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "infoOfRealEstate")
	@JsonIgnoreProperties("infoOfRealEstate")
	private Post post;
	
	@ToString.Exclude
	@JsonManagedReference
	@OneToMany( 
		mappedBy = "infoOfRealEstate",
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER,
		orphanRemoval = true ) // mappedBy = "employee", FetchType=lazy : err when update
	private List<ImageOfRealEstate> image = new ArrayList<ImageOfRealEstate>();
	
	
	
	public void addImage(ImageOfRealEstate imageOf) {
		image.add(imageOf);
		imageOf.setInfoOfRealEstate(this);
    }
 
    public void removeImage(ImageOfRealEstate imageOf) {
    	image.remove(imageOf);
		imageOf.setInfoOfRealEstate(this);
    }
	
}

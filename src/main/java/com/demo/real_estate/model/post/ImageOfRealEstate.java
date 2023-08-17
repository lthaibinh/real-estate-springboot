package com.demo.real_estate.model.post;

import com.demo.real_estate.model.Employee;
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
@Table(name = "ImageOfRealEstate")
public class ImageOfRealEstate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String url ;
	private long size;
	

	@ToString.Exclude
	@JsonBackReference
	@ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "info_id")
	private InfoOfRealEstate infoOfRealEstate;

	
	
	
	public String getUrl() {
		return "http://localhost:9090/api/v1/" + url; 
	}


	public void setUrl(String url) {
		this.url = url.replace("http://localhost:9090/api/v1/", "");
	}
	
	
}

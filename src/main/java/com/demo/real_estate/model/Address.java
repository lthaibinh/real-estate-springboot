package com.demo.real_estate.model;

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
@Table(name = "tbl_address")
public class Address {
	

	@Id // Đánh dấu là primary key
	@GeneratedValue // Giúp tự động tăng
	private Long id;

	private String city;
	private String province;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY) // lazy enhance performance
	private Employee employee;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Address))
			return false;
		return id != null && id.equals(((Address) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

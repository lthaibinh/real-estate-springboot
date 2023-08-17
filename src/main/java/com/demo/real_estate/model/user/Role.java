package com.demo.real_estate.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
public class Role implements Serializable{
  private static final long serialVersionUID = -5204391003825277886L;

  @Id
  @Column(name = "role_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
    
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<User> users = new ArrayList<>();

  public Role(String name) {
    this.name = name;
  }
}
package com.demo.real_estate.model.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.demo.real_estate.model.Token;
import com.demo.real_estate.model.TokenType;
import com.demo.real_estate.model.post.ImageOfRealEstate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")  //Để không bị lỗi khi dùng Postgresql
public class User implements UserDetails {

  private static final long serialVersionUID = 6268404888144025944L;

  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @Column(unique = true, nullable = false)
  public String username;

  @Column(nullable = false)
  private String password;
  
  private boolean enabled;
  
  private String firstname;
  private String lastname;
  private String email;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
    name = "users_roles", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();
  
  
  @OneToMany( 
			mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true ) // mappedBy = "employee", FetchType=lazy : err when update
  private List<Token> token = new ArrayList<Token>();
  

  public void addRole(Role role) {
    roles.add(role);
    role.getUsers().add(this);
  }

  public void removeRole(Role role) {
    roles.remove(role);
    role.getUsers().remove(this);
  }

  //--- Constructor --------------------------------
  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.enabled = true;
  }


  //-------- Implements các methods của interface UserDetails
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();         
    for (Role role : roles) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
    }      
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

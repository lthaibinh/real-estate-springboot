package com.demo.real_estate.request;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	  private String firstname;
	  private String lastname;
	  private String email;
	  private String password;
	  private String username;
	  private List<String> role;
}

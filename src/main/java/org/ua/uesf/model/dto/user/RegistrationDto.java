package org.ua.uesf.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegistrationDto {
	@NotBlank
	private String firstName;
	private String lastName;
		@NotBlank
	private String userName;
	@NotBlank
	@Pattern(regexp="[a-zA-Z0-9][a-zA-Z0-9-.]*@[a-zA-Z]+([.][a-zA-z]*)*")
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String confirmPassword;

	private String img;
	
}

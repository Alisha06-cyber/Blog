package com.test.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/*this class use for transfering data*/

public class UserDto {
	@NotEmpty
	@Size(min =4,message="username must be min 4 charcters !!)")
	private int id;
	private String name;
	@Email(message="email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min =3,max=10,message="password must be min 3 chars and max of 10 chars!")
	private String password;
	@NotEmpty
	private String about;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
}

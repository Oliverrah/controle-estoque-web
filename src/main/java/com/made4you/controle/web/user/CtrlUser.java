package com.made4you.controle.web.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.made4you.controle.web.validation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "Os campos de senha devem ser iguais")
})
public class CtrlUser {

	@NotNull(message = "Preencha seu nome de usuário")
	@Size(min = 5, message = "Nome de usuário precisa ter ao menos 5 caracteres")
	private String username;

	@NotNull(message = "Preencha sua senha")
	@Size(min = 6, message = "A senha precisa ter no mínimo 6 caracteres.")
	private String password;
	
	@NotNull(message = "Confirme sua senha")
	@Size(min = 6, message = "A senha precisa ter no mínimo 6 caracteres.")
	private String matchingPassword;

	@NotNull(message = "Preencha seu nome")
	@Size(min = 3, message = "Seu nome precisa ter ao menos 3 caracteres")
	private String firstName;

	@NotNull(message = "Preencha seu sobrenome")
	@Size(min = 3, message = "Seu sobrenome precisa ter ao menos 3 caracteres")
	private String lastName;

	@NotNull(message = "Preencha seu e-mail")
	@Size(min = 6, message = "E-mail precisa ter ao menos 6 caracteres")
	private String email;
	
	public CtrlUser() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}

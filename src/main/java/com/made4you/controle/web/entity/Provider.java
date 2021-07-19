package com.made4you.controle.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="provider")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotNull(message="Informe o nome do fornecedor.")
	@Size(min=1, max=100, message="O nome precisa ter entre 1 e 200 caracteres")
	private String name;
	
	@Column(name="cnpj")
	@NotNull(message="Informe o CNPJ.")
	@Size(min=14, max=14, message="O CNPJ precisa ter 14 caracteres")
	private String cnpj;
	
	@Column(name="uf")
	@NotNull(message="Informe a UF do estado.")
	@Size(min=2, max=2, message="A UF é composta por dois caracteres.")
	private String uf;
	
	@Column(name="city")
	@NotNull(message="Informe a cidade.")
	@Size(min=1, max=30, message="A cidade precisa ter entre 1 e 30 caracteres")
	private String city;
	
	@Column(name="adress_street")
	@NotNull(message="Informe o nome da rua.")
	@Size(min=5, max=60, message="O nome da rua precisa ter entre 5 e 60 caracteres")
	private String adressStreet;
	
	@Column(name="adress_number")
	@NotNull(message="Informe o numero do endereço.")
	@Size(min=1, max=10, message="O numero de endereço precisa ter entre 1 e 10 caracteres")
	private String adressNumber;
	
	@Column(name="adress_zipcode")
	@NotNull(message="Informe o CEP.")
	@Size(min=8, max=8, message="O CEP precisa ter 8 caracteres")
	private String adressZipCode;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "provider", cascade = CascadeType.REMOVE)
	private List<Product> products;
		
	
	public Provider() {
		
	}

	public Provider(String name, String cnpj, String uf, String city, String adressStreet, String adressNumber,
			String adressZipCode) {
		this.name = name;
		this.cnpj = cnpj;
		this.uf = uf;
		this.city = city;
		this.adressStreet = adressStreet;
		this.adressNumber = adressNumber;
		this.adressZipCode = adressZipCode;
	}

	public Provider(Long id, String name, String cnpj, String uf, String city, String adressStreet, String adressNumber,
			String adressZipCode) {
		this.id = id;
		this.name = name;
		this.cnpj = cnpj;
		this.uf = uf;
		this.city = city;
		this.adressStreet = adressStreet;
		this.adressNumber = adressNumber;
		this.adressZipCode = adressZipCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdressStreet() {
		return adressStreet;
	}

	public void setAdressStreet(String adressStreet) {
		this.adressStreet = adressStreet;
	}

	public String getAdressNumber() {
		return adressNumber;
	}

	public void setAdressNumber(String adressNumber) {
		this.adressNumber = adressNumber;
	}

	public String getAdressZipCode() {
		return adressZipCode;
	}

	public void setAdressZipCode(String adressZipCode) {
		this.adressZipCode = adressZipCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	public void add(Product product) {
		if(products == null) {
			products = new ArrayList<>();
		}
		
		products.add(product);
		
		product.setProvider(this);
	}

	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", cnpj=" + cnpj + ", uf=" + uf + ", city=" + city
				+ ", adressStreet=" + adressStreet + ", adressNumber=" + adressNumber + ", adressZipCode="
				+ adressZipCode + ", products=" + products + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressNumber == null) ? 0 : adressNumber.hashCode());
		result = prime * result + ((adressStreet == null) ? 0 : adressStreet.hashCode());
		result = prime * result + ((adressZipCode == null) ? 0 : adressZipCode.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (adressNumber == null) {
			if (other.adressNumber != null)
				return false;
		} else if (!adressNumber.equals(other.adressNumber))
			return false;
		if (adressStreet == null) {
			if (other.adressStreet != null)
				return false;
		} else if (!adressStreet.equals(other.adressStreet))
			return false;
		if (adressZipCode == null) {
			if (other.adressZipCode != null)
				return false;
		} else if (!adressZipCode.equals(other.adressZipCode))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}		
}

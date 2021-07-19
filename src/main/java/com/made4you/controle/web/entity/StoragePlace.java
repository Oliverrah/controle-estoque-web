package com.made4you.controle.web.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name="storage_place")
public class StoragePlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="location_name")
	@NotNull(message="Informe o nome do local de armazenamento.")
	@Size(min=1, max=60, message="O nome do local precisa ter entre 1 e 60 caracteres.")
	private String locationName;
	
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
	
	@Column(name="uf")
	@NotNull(message="Informe a UF do estado.")
	@Size(min=2, max=2, message="A UF é composta por dois caracteres.")
	private String uf;
	
	@Column(name="city")
	@NotNull(message="Informe a cidade.")
	@Size(min=1, max=30, message="A cidade precisa ter entre 1 e 30 caracteres")
	private String city;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "storagePlace")
	private List<Stock> stocks;
	
	public StoragePlace() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void add(Stock stock) {
		if(stocks == null) {
			stocks = new ArrayList<>();
		}
		
		stocks.add(stock);
		
		stock.setStoragePlace(this);
	}

	@Override
	public String toString() {
		return "StoragePlace [id=" + id + ", locationName=" + locationName + ", adressStreet=" + adressStreet
				+ ", adressNumber=" + adressNumber + ", adressZipCode=" + adressZipCode + ", uf=" + uf + ", city="
				+ city + ", user=" + user + ", stocks=" + stocks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressNumber == null) ? 0 : adressNumber.hashCode());
		result = prime * result + ((adressStreet == null) ? 0 : adressStreet.hashCode());
		result = prime * result + ((adressZipCode == null) ? 0 : adressZipCode.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((locationName == null) ? 0 : locationName.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		StoragePlace other = (StoragePlace) obj;
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
		if (locationName == null) {
			if (other.locationName != null)
				return false;
		} else if (!locationName.equals(other.locationName))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}		
}

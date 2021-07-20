package com.made4you.controle.web.entities;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	@NotNull(message="Informe o nome do produto.")
	@Size(min=1, max=80, message="O nome do produto precisa ter entre 1 a 80 caracteres.")
	private String name;
	
	@Column(name="cust_price")
	@NotNull(message="Informe o preço de custo.")
	@Min(value = 0)
	private Double custPrice;
	
	@Column(name="sell_price")
	@NotNull(message="Informe o preço de custo.")
	@Min(value = 0)
	private Double sellPrice;
	
	@Column(name="unit")
	@NotNull(message="Informe a unidade de medida.")
	@Size(min=2, max=3, message="A unidade de medida precisa ter 2 ou 3 caracteres.")
	private String unit;
	
	@Column(name="gtin")
	@Size(min=10, max=20, message="O código de barras precisa ter entre 10 e 20 caracteres ")
	private String gtin;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@NotNull(message="Informe a categoria.")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="provider_id")
	@NotNull(message="Informe o fornecedor.")
	private Provider provider;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
		
	@OneToMany(mappedBy = "product")
	private List<Stock> stocks;
	
	
	public Product() {
		
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

	public Double getCustPrice() {
		return custPrice;
	}

	public void setCustPrice(Double custPrice) {
		this.custPrice = custPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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
		
		stock.setProduct(this);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", custPrice=" + custPrice + ", sellPrice=" + sellPrice
				+ ", unit=" + unit + ", gtin=" + gtin + ", category=" + category + ", provider=" + provider + ", user="
				+ user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((custPrice == null) ? 0 : custPrice.hashCode());
		result = prime * result + ((gtin == null) ? 0 : gtin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((sellPrice == null) ? 0 : sellPrice.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (custPrice == null) {
			if (other.custPrice != null)
				return false;
		} else if (!custPrice.equals(other.custPrice))
			return false;
		if (gtin == null) {
			if (other.gtin != null)
				return false;
		} else if (!gtin.equals(other.gtin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (sellPrice == null) {
			if (other.sellPrice != null)
				return false;
		} else if (!sellPrice.equals(other.sellPrice))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
		
}

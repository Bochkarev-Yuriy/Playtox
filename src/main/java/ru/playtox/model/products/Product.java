package ru.playtox.model.products;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "name", length = 30, nullable = false)
	private String name;


	@Size(max = 3000)
	@Column(name = "description", length = 3000, nullable = false)
	private String description;

	@NotNull
	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@NotNull
	@Column(name = "count", nullable = false)
	private Integer count;

	@NotNull
	@Column(name = "available", nullable = false)
	private boolean isAvailable = true;

	public Product() {

	}

	public Product(String name, String description, BigDecimal price, Integer count) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.count = count;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (id != null ? !id.equals(product.id) : product.id != null) return false;
		if (count != null ? !count.equals(product.count) : product.count != null) return false;
		if (price != null ? !price.equals(product.price) : product.price != null) return false;
		if (description != null ? !description.equals(product.description) : product.description != null) return false;
		return name != null ? name.equals(product.name) : product.name == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (count != null ? count.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", count=" + count +
				", isAvailable=" + isAvailable +
				'}';
	}
}

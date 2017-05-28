package ru.playtox.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

	@NotNull
	@Column(name = "date")
	private LocalDate date;

	@NotNull
	@Column(name = "totalPrice")
	private BigDecimal totalPrice;

	@NotNull
	@Column(name = "count")
	private Integer count;

	public Purchase() {
	}

	public Purchase(User user, Product product, LocalDate date, BigDecimal totalPrice, Integer count) {
		this.user = user;
		this.product = product;
		this.date = date;
		this.totalPrice = totalPrice;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Purchase purchase = (Purchase) o;

		if (id != null ? !id.equals(purchase.id) : purchase.id != null) return false;
		if (user != null ? !user.equals(purchase.user) : purchase.user != null) return false;
		if (product != null ? !product.equals(purchase.product) : purchase.product != null) return false;
		if (date != null ? !date.equals(purchase.date) : purchase.date != null)
			return false;
		if (count != null ? !count.equals(purchase.count) : purchase.count != null)
			return false;
		return totalPrice != null ? totalPrice.equals(purchase.totalPrice) : purchase.totalPrice == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (product != null ? product.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (count != null ? count.hashCode() : 0);
		result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Purchase{" +
				"id=" + id +
				", user=" + user +
				", product=" + product +
				", date=" + date +
				", totalPrice=" + totalPrice +
				", count=" + count +
				'}';
	}
}

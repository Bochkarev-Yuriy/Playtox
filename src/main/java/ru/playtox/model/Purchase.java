package ru.playtox.model.purchases;

import ru.playtox.model.products.Product;
import ru.playtox.model.users.User;

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
	@Column(name = "dateOfPurchase")
	private LocalDate dateOfPurchase;

	@NotNull
	@Column(name = "priceOfPurchase")
	private BigDecimal priceOfPurchase;

	@NotNull
	@Column(name = "countOfPurchase")
	private Integer countOfPurchase;

	public Purchase() {
	}

	public Purchase(User user, Product product, LocalDate dateOfPurchase, BigDecimal priceOfPurchase, Integer countOfPurchase) {
		this.user = user;
		this.product = product;
		this.dateOfPurchase = dateOfPurchase;
		this.priceOfPurchase = priceOfPurchase;
		this.countOfPurchase = countOfPurchase;
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

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public BigDecimal getPriceOfPurchase() {
		return priceOfPurchase;
	}

	public void setPriceOfPurchase(BigDecimal priceOfPurchase) {
		this.priceOfPurchase = priceOfPurchase;
	}

	public Integer getCountOfPurchase() {
		return countOfPurchase;
	}

	public void setCountOfPurchase(Integer countOfPurchase) {
		this.countOfPurchase = countOfPurchase;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Purchase purchase = (Purchase) o;

		if (id != null ? !id.equals(purchase.id) : purchase.id != null) return false;
		if (user != null ? !user.equals(purchase.user) : purchase.user != null) return false;
		if (product != null ? !product.equals(purchase.product) : purchase.product != null) return false;
		if (dateOfPurchase != null ? !dateOfPurchase.equals(purchase.dateOfPurchase) : purchase.dateOfPurchase != null)
			return false;
		if (countOfPurchase != null ? !countOfPurchase.equals(purchase.countOfPurchase) : purchase.countOfPurchase != null)
			return false;
		return priceOfPurchase != null ? priceOfPurchase.equals(purchase.priceOfPurchase) : purchase.priceOfPurchase == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (product != null ? product.hashCode() : 0);
		result = 31 * result + (dateOfPurchase != null ? dateOfPurchase.hashCode() : 0);
		result = 31 * result + (countOfPurchase != null ? countOfPurchase.hashCode() : 0);
		result = 31 * result + (priceOfPurchase != null ? priceOfPurchase.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Purchase{" +
				"id=" + id +
				", user=" + user +
				", product=" + product +
				", dateOfPurchase=" + dateOfPurchase +
				", priceOfPurchase=" + priceOfPurchase +
				", countOfPurchase=" + countOfPurchase +
				'}';
	}
}

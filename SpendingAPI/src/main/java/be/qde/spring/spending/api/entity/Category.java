package be.qde.spring.spending.api.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="categories")
public class Category {

	private Integer id;
	private String name;
	private User owner;
	private List<Spending> spendings;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="ownerId")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@OneToMany
	public List<Spending> getSpendings() {
		return spendings;
	}

	public void setSpendings(List<Spending> spendings) {
		this.spendings = spendings;
	}

	@Transient
	public double getTotal() {
		double total = 0;
		if(this.spendings != null)
			total += this.spendings.stream().mapToDouble(spending -> spending.getValue()).sum();
		return total;
	}

}

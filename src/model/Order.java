package model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date creationTime;
	
	@ManyToOne
	private Customer customer;
	
    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "orders_id")
    private List<OrderLine> orderLines;
	
	
	
	public Order(){}
	
	public Order(Date creationTime, Customer customer,
			List<OrderLine> orderLines) {
		this.creationTime = creationTime;
		this.customer = customer;
		this.orderLines = orderLines;
	}


	public Long getId() {
		return id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", creationTime=" + creationTime
				+ ", id_customer=" + customer.getId() + "]";
	}
	
	
}

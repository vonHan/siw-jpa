package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Column;

	@Entity
	@NamedQueries({
		@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p"),
		@NamedQuery(name="findAllProviders", query = "SELECT p FROM Product p JOIN FETCH p.providers WHERE p.id = :id")
	})

	public class Product {
        
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	private Float price;
	
	@Column(length = 2000)
	private String description;

	@Column(nullable = false)
	private String code;
	
	@ManyToMany
	private List<Provider> providers;
	
	public Product() {
    }

	public Product(String name, Float price, String description, String code) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.code = code;
	}


    //Getters & Setters        
    
   public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    
    public String getCode() {
        return this.code;
    }


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
	
    public List<Provider> getProviders() {
		return providers;
	}

	public boolean equals(Object obj) {
        Product product = (Product)obj;
        return this.getCode().equals(product.getCode());
    }

    public int hashCode() {
         return this.code.hashCode();
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Product"); 
        sb.append("{id=").append(id); 
        sb.append(", name='").append(name); 
        sb.append(", price=").append(price); 
        sb.append(", description='").append(description); 
        sb.append(", code='").append(code);
        sb.append("}\n");
        return sb.toString();
    }
	
}
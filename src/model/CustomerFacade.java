package model;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class CustomerFacade {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	
	public CustomerFacade(){}
	

	private void openEntityManager(){
		this.emf=Persistence.createEntityManagerFactory("products-unit");
		this.em=emf.createEntityManager();
	}
	

	private void closeEntityManager(){
		this.em.close();
		this.emf.close();
	}
	
	
	public  Customer createCustomer(String firstName, String lastName, String email, String phoneNumber, Date dateOfBirth, Date registrationDate,
			String street, String city, String state, String zipcode, String country){
		
		Address address=new Address(street, city, state, zipcode, country);
		Customer customer=new Customer(firstName, lastName, email, phoneNumber, dateOfBirth, registrationDate, address, null);
		
		this.openEntityManager();
		EntityTransaction tx=em.getTransaction();
		
		tx.begin();
		
		try{
			em.persist(customer);
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();
			customer=null;
			
		}finally{
			this.closeEntityManager();
		}
		
		return customer;
	}
	
	
	public List<Customer> getAll(){
		
		List<Customer> result;
		this.openEntityManager();
		
		try{
			TypedQuery<Customer> query=em.createQuery("SELECT c FROM Customer c", Customer.class);
			result=query.getResultList();
			
		}catch(Exception e){
			result=null;
			
		}finally{
			this.closeEntityManager();
		}
		
		return result;
	}
		
	
	public List<Order> getOrders(Long id){
		
		List<Order> result;
		this.openEntityManager();
		
		
		try{
			result=em.find(Customer.class, id).getOrders();
			
		}catch(Exception ex){
			result=null;
			
		}finally{
			this.closeEntityManager();
		}
		
		return result;
	}
	
	
	
	
	
	
		
}

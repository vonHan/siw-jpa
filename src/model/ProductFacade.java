package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductFacade {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public ProductFacade(){}
	

	private void openEntityManager(){
		this.emf=Persistence.createEntityManagerFactory("products-unit");
		this.em=emf.createEntityManager();
	}
	
	private void closeEntityManager(){
		this.em.close();
		this.emf.close();
	}
	
	
	public List<Provider> retrieveProviders(Long id){
		
		this.openEntityManager();
		EntityTransaction tx = this.em.getTransaction();
		
		List<Provider> result = null;
		
		tx.begin();
		
		try{
			TypedQuery<Product> query=this.em.createNamedQuery("findAllProviders", Product.class);
			query.setParameter("id", id);
			result=query.getSingleResult().getProviders();
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();
			
		}finally{
			this.closeEntityManager();
		}
		
		return result;
	}
}


import java.util.List;
import model.ProductFacade;
import model.Provider;


public class Main {
	
	public static void main(String[] args){
		
		ProductFacade productFacade=new ProductFacade();
		List<Provider> providers=productFacade.retrieveProviders(new Long(251));
		
		
		System.out.println(providers.size());
		System.out.println(providers.get(0));
		
	}
		
} 

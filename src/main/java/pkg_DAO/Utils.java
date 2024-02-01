package pkg_DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Utils {
	static Session session;
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	static MetadataSources sources;

	public static synchronized SessionFactory getSessionFactory() {
	    if ( sessionFactory == null ) {

	        // exception handling omitted for brevityaa
	    		//usar listeners
	           /*BootstrapServiceRegistry bootstrapRegistry =
	                   new BootstrapServiceRegistryBuilder()
	                   .applyIntegrator(new EventListenerIntegrator())
	                   .build();
	            */
	        serviceRegistry = new StandardServiceRegistryBuilder()
	                .configure("hibernate.cfg.xml")
	                .build();

	        sources = new MetadataSources( serviceRegistry );
	        
	        Metadata metadata = sources.getMetadataBuilder().build();
	        
	        ///comentar o descomentar la linea del interceptor
	        sessionFactory = metadata.getSessionFactoryBuilder()
	        		//.applyInterceptor(new LoggingInterceptor())
	        		.build();
	        	
	                
	    }
	    return sessionFactory;
	}
}
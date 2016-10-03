package com.eli.calc.shape.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.eli.calc.shape.service.ws.ShapeCalculatorWebService;
import com.eli.calc.shape.service.ws.impl.ShapeCalculatorWebServiceImpl;

@Configuration
@ComponentScan(basePackages="com.eli.calc.shape") //this finds base and persist config
public class WebServiceContext {

	private static final Logger logger = LoggerFactory.getLogger(WebServiceContext.class);


    //@ApplicationPath("/ShapeCalculatorWebService")
    //public class JaxRsApiApplication extends Application { }


    public WebServiceContext() {
    	logger.debug("\n\n\nConstructor\n\n\n");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID) //this is REQUIRED or you get "No bean named 'cxf' is defined"
	public SpringBus cxf() {
        logger.debug("\n\n\n\nWebServiceContext cxf (SpringBus) \n\n\n\n");
	    return new SpringBus();
	}

/*
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
		logger.debug("\n\n\n\nELI: WebServiceContext jaxRsApiApplication \n\n\n\n");
        return new JaxRsApiApplication();
    }
*/

    @Bean
    public ShapeCalculatorWebService shapeCalculatorWebServiceImpl() {
		logger.debug("\n\n\n\nWebServiceContext shapeCalculatorWebServiceImpl \n\n\n\n");
    	return new ShapeCalculatorWebServiceImpl();
    }
 
/*
    @Bean
    public JacksonJsonProvider jsonProvider() {
		logger.debug("\n\n\n\nWebServiceContext jsonProvider \n\n\n\n");
    	return new JacksonJsonProvider();
    }
*/

    //simple version
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {

		logger.debug("\n\n\n\nWebServiceContext JaxRsServer \n\n\n\n");

    	JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
/*
        JAXRSServerFactoryBean factory = 
        		RuntimeDelegate.getInstance().createEndpoint(
        				jaxRsApiApplication(), 
        				JAXRSServerFactoryBean.class);
*/
    	factory.setServiceBean(shapeCalculatorWebServiceImpl());
    	factory.setAddress("/shapecalc");
    	//factory.setProvider(jsonProvider());
/*
*/
		logger.debug("\n\n\n\nEND OF WebServiceContext JaxRsServer \n\n\n\n");
    	return factory.create();
    }
    
/* 
    // THIS WORKS COMPLETELY
    @Bean
    //@DependsOn("cxf")  // <----so far,this not needed
    public Server jaxRsServer() {

		logger.debug("\n\n\n\nWebServiceContext JaxRsServer \n\n\n\n");

        JAXRSServerFactoryBean factory = 
        		RuntimeDelegate.getInstance().createEndpoint(
        				jaxRsApiApplication(), 
        				JAXRSServerFactoryBean.class);

    	factory.setServiceBean(shapeCalculatorWebServiceImpl());
    	factory.setAddress("/ShapeCalculatorWebService");
    	factory.setProvider(jsonProvider());
		logger.debug("\n\n\n\nEND OF WebServiceContext JaxRsServer \n\n\n\n");
        return factory.create();
    }
*/
    
/*
    // so far, this version not needed
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer(ApplicationContext appContext) {
        JAXRSServerFactoryBean factory = 
        		RuntimeDelegate.getInstance().createEndpoint(
        				jaxRsApiApplication(), 
        				JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(userResource(), exceptionResource()));
        factory.setAddress("/" + factory.getAddress());
        factory.setProvider(jsonProvider());
        return factory.create();
    }
*/

}

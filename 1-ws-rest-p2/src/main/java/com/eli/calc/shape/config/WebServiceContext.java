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


    public WebServiceContext() {
    	logger.debug("\n\n\nConstructor\n\n\n");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID) //this is REQUIRED or you get "No bean named 'cxf' is defined"
	public SpringBus cxf() {
        logger.debug("\n\n\n\nWebServiceContext cxf (SpringBus) \n\n\n\n");
	    return new SpringBus();
	}

    @Bean
    public ShapeCalculatorWebService shapeCalculatorWebServiceImpl() {
		logger.debug("\n\n\n\nWebServiceContext shapeCalculatorWebServiceImpl \n\n\n\n");
    	return new ShapeCalculatorWebServiceImpl();
    }
 
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {

		logger.debug("\n\n\n\nWebServiceContext JaxRsServer \n\n\n\n");

    	JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
    	factory.setServiceBean(shapeCalculatorWebServiceImpl());
    	factory.setAddress("/shapecalc");
		logger.debug("\n\n\n\nEND OF WebServiceContext JaxRsServer \n\n\n\n");
    	return factory.create();
    }
}

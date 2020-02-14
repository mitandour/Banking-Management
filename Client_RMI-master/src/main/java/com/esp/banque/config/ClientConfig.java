package com.esp.banque.config;

import com.esp.banque.config.properties.ServeurProperties;
import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;
import com.esp.banque.service.CompteService;
import com.esp.banque.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class ClientConfig {

    private ServeurProperties serveurProperties;

    @Autowired
    public ClientConfig(ServeurProperties serveurProperties) {
        this.serveurProperties = serveurProperties;
    }

    @Bean
    RmiProxyFactoryBean agenceServiceRMI() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(makeURL(AgenceService.class.getSimpleName()));
        rmiProxyFactory.setServiceInterface(AgenceService.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean clientServiceRMI() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(makeURL(ClientService.class.getSimpleName()));
        rmiProxyFactory.setServiceInterface(ClientService.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean compteServiceRMI() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(makeURL(CompteService.class.getSimpleName()));
        rmiProxyFactory.setServiceInterface(CompteService.class);
        return rmiProxyFactory;
    }

    @Bean
    RmiProxyFactoryBean operationServiceRMI() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl(makeURL(OperationService.class.getSimpleName()));
        rmiProxyFactory.setServiceInterface(OperationService.class);
        return rmiProxyFactory;
    }

    private String makeURL(String serviceName) {
        return "rmi://"+serveurProperties.getHost()+":"+serveurProperties.getPort()+"/"+serviceName;
    }
}



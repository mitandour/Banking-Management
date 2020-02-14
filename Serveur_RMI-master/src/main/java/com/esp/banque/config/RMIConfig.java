package com.esp.banque.config;

import com.esp.banque.config.properties.ServerProperties;
import com.esp.banque.service.AgenceService;
import com.esp.banque.service.ClientService;
import com.esp.banque.service.CompteService;
import com.esp.banque.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RMIConfig {

    private ServerProperties serverProperties;

    @Autowired
    public RMIConfig(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Bean
    RmiServiceExporter agenceServiceExporter(AgenceService service) {
        return makeExporter(AgenceService.class, service);
    }

    @Bean
    RmiServiceExporter clientServiceExporter(ClientService service) {
        return makeExporter(ClientService.class, service);
    }

    @Bean
    RmiServiceExporter compteServiceExporter(CompteService service) {
        return makeExporter(CompteService.class, service);
    }

    @Bean
    RmiServiceExporter operationServiceExporter(OperationService service) {
        return makeExporter(OperationService.class, service);
    }


    private RmiServiceExporter makeExporter(Class serviceInterface, Object serviceImplementation) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(serviceImplementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(serverProperties.getPort());
        return exporter;
    }
}

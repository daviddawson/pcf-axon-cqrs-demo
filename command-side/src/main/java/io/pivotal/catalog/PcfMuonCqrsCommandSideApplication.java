package io.pivotal.catalog;

import io.muoncore.newton.EnableNewton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@SpringBootApplication
@EnableNewton
public class PcfMuonCqrsCommandSideApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PcfMuonCqrsCommandSideApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PcfMuonCqrsCommandSideApplication.class, args);
        LOG.info("Starting the COMMAND-SIDE PCF Muon CQRS Demo using SpringBoot.");
    }
}

package pe.devsu.development.operationservicetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = { "pe.devsu.development.operationservicetest.*" })
@SpringBootApplication
public class DemoBankOperationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBankOperationsApplication.class, args);
    }

}

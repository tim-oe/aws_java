package org.tec.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages ={
        "org.tec.aws.conf",
        "org.tec.aws.svc"
})
@EntityScan(basePackages = {"org.tec.aws.entity"})
@EnableJpaRepositories(basePackages = {"org.tec.aws.repository"})
@EnableJpaAuditing
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}

package co.com.daleb.booking.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "co.com.daleb.booking")
@EntityScan("co.com.daleb.booking.infraestructure.sql")
@EnableJpaRepositories("co.com.daleb.booking.infraestructure.sql.jpa")
public class BookingApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(BookingApplication.class,args);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

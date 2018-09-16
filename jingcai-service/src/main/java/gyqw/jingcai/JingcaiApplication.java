package gyqw.jingcai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class JingcaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(JingcaiApplication.class, args);
    }
}

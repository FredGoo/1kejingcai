package gyqw.jingcai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:database.xml"})
public class XmlConfig {
}

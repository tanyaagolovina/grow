package eventloggerapp.annotations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("event-logger/properties/client.properties")
@PropertySource("event-logger/properties/logger.properties")
public class PropertiesFilesConfig {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

package eventloggerapp.annotations.config;

import eventloggerapp.annotations.*;
import eventloggerapp.xml.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Import({PropertiesFilesConfig.class, EventLoggersConfig.class})
@Configuration
public class BeansConfig {

    @Bean
    @Autowired
    @Scope("prototype")
    public Event event(Date date, DateFormat df){
        return new Event(date, df);
    }

    @Bean
    public Date date(){
        return new Date();
    }

    @Bean
    public DateFormat df(){
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Client client(){
        return new Client();
    }

    @Bean
    @Autowired
    public App app(Client client, Map<EventType, EventLogger> loggersMap){
        return new App(client, loggersMap);
    }

    @Bean
    @Autowired
    public Map<EventType, EventLogger> loggersMap(EventLogger consoleEventLogger, EventLogger combinedEventLogger){
        Map<EventType, EventLogger> map = new HashMap<>();
        map.put(EventType.INFO, consoleEventLogger);
        map.put(EventType.ERROR, combinedEventLogger);
        return map;
    }


}

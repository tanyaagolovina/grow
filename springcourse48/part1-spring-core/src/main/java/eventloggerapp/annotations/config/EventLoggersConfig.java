package eventloggerapp.annotations.config;

import eventloggerapp.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class EventLoggersConfig {

    @Bean
    public ConsoleEventLogger consoleEventLogger(){
        return new ConsoleEventLogger();
    }

    @Bean
    public FileEventLogger fileEventLogger(){
        return new FileEventLogger();
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger(){
        return new CacheFileEventLogger();
    }

    @Autowired
    @Bean
    public CombinedEventLogger combinedEventLogger(List<EventLogger> loggers){
        return new CombinedEventLogger(loggers);
    }

    @Autowired
    @Bean
    public List<EventLogger> loggers(FileEventLogger fileEventLogger, ConsoleEventLogger consoleEventLogger){
        List<EventLogger> list = new ArrayList<>();
        list.add(fileEventLogger);
        list.add(consoleEventLogger);
        return list;
    }
}

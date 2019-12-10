package eventloggerapp.annotations;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    @Value("${cache}")
    private int cacheSize;

    private List<Event> cache = new ArrayList<>();

    public CacheFileEventLogger(){}

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @PreDestroy
    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache(){
        for (Event event:cache) {
            super.logEvent(event);
        }
    }

}

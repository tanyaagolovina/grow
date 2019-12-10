package eventloggerapp.xml;


import eventloggerapp.xml.enums.EventType;

import java.util.Map;

public class App {
    private Client client;
    private Map<EventType, EventLogger> loggersMap ;
    private CacheFileEventLogger defaultLogger;

    public App(Client client, Map<EventType, EventLogger> loggersMap){
        this.client = client;
        this.loggersMap = loggersMap;
    }

    public void logEvent(EventType type, Event event){
        EventLogger logger = loggersMap.get(type);
        if(logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);
        System.out.println(client + "registered event " + event + "with level - " + type + "\n");

    }


    @Override
    public String toString() {
        return client + " registered event in" + loggersMap.toString();
    }

    public void setDefaultLogger(CacheFileEventLogger defaultLogger){
        this.defaultLogger = defaultLogger;
    }

}

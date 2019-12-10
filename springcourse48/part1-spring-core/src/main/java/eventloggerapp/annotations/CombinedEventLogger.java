package eventloggerapp.annotations;

import java.util.List;

public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers){
        this.loggers = loggers;
    }
    @Override
    public void logEvent(Event event) {
        for (EventLogger logger1 : loggers) {
            logger1.logEvent(event);
        }
    }


}

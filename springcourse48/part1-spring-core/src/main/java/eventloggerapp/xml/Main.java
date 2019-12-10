package eventloggerapp.xml;

import eventloggerapp.xml.enums.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("event-logger/event_logger");
        ((ClassPathXmlApplicationContext) ctx).registerShutdownHook();
        Event event1 = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");

        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.DEFAULT, event1);
        app.logEvent(EventType.INFO, event2);
    }

}

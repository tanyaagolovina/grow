package eventloggerapp.annotations;

import eventloggerapp.annotations.config.BeansConfig;
import eventloggerapp.annotations.config.EventLoggersConfig;
import eventloggerapp.xml.enums.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeansConfig.class, EventLoggersConfig.class);
        ((AnnotationConfigApplicationContext) ctx).registerShutdownHook();

        Event event = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");

        App app = (App) ctx.getBean("app");
        app.logEvent(EventType.ERROR, event);
        app.logEvent(EventType.INFO, event2);

    }
}

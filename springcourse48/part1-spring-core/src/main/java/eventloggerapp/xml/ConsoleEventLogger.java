package eventloggerapp.xml;

public class ConsoleEventLogger implements EventLogger {
    @Override
    public void logEvent(Event event) {
        System.out.println(event);
    }

    @Override
    public String toString() {
        return "ConsoleEventLogger ";
    }
}

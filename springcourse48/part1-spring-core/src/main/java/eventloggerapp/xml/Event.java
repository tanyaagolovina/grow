package eventloggerapp.xml;

import java.text.DateFormat;
import java.util.Date;


public class Event {
    private static int idStatic = 0;
    private int id;
    private String message;
    private Date date;
    private DateFormat df;
    private static int time;


    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        id = ++idStatic;
        time = date.getHours();

    }

    public static boolean isDay(){
        return (time > 8 && time <= 17);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{id"  + id +
                " message='" + message + '\'' +
                ", date=" + df.format(date) +
                '}' + '\n';
    }
}

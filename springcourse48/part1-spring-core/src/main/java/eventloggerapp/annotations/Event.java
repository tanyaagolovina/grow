package eventloggerapp.annotations;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private static int idStatic = 0;
    private int id;
    private String message;

    private Date date;
    private DateFormat df;


    public Event( Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        id = ++idStatic;

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

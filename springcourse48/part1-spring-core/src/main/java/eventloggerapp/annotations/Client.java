package eventloggerapp.annotations;

import org.springframework.beans.factory.annotation.Value;

public class Client {
    @Value("${id}")
    private int id;
    @Value("${name}")
    private String fullName;
    @Value("${greeting}")
    private String greeting;

    public Client(){}

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Client " +
                "id=" + id +
                ", fullName='" + fullName + '\'' + "!" + greeting +
                "\n";
    }
}

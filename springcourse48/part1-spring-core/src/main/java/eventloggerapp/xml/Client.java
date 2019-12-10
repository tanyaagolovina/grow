package eventloggerapp.xml;

public class Client {
    private int id;
    private String fullName;
    private String greeting;

    public Client(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public void setGreeting(String greeting){
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return "Client " +
                "id=" + id +
                ", fullName='" + fullName + '\'' + "!" + greeting +
                "\n";
    }
}

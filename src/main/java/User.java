import java.util.ArrayList;

/**
 * Created by vasantia on 7/11/16.
 */
public class User {

    private String name;
    private String password;
    ArrayList<Message> myMessages = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Message> getMyMessages() {
        return myMessages;
    }
}

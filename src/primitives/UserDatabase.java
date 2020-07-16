package primitives;

import com.thoughtworks.xstream.XStream;
import exceptions.DuplicateUserName;
import java.io.File;
import exceptions.IncorrectUsername;
import exceptions.SignUpDenied;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


//TODO: CONSIDER IT TO BE A SINGLETON
public class UserDatabase implements Serializable {

    private final File xmlFile = new File("Database.xml");
    private final XStream xstream = new XStream();
    private List<User> users = new LinkedList<>();

    private void serialize() {
        try {
            xstream.toXML(users, new FileWriter(xmlFile));
        } catch (IOException ex) {
            System.out.println("Error while seriliazing!");
            System.exit(1);
        }
    }

    public List<User> deserialize() {
        return (List<User>) xstream.fromXML(xmlFile);
    }


    public UserDatabase() {

        xstream.alias("User", User.class);
        xstream.alias("Token", Token.class);
        xstream.alias("CardId", CreditCard.class);

        if (!xmlFile.exists()) {
            serialize();
        }
    }

    public void add(User userToAdd) throws IOException, DuplicateUserName {

        users = deserialize();
        
        //check for duplicates!
        for (User regUser : users) {
            if (regUser.getUsername().equals(userToAdd.getUsername())) {
                throw new DuplicateUserName();
            }
        }
        
        //no dups found, ready to add the user
        users.add(userToAdd);
        serialize();
    }

    /*Returns User by given name
    @param name The unique name of the user.
    */

    public User getUserByName(String name) throws IncorrectUsername, SignUpDenied {
        users = deserialize();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return new User(user);
            }
        }
        throw new IncorrectUsername();
    }

    public void updateDatabase(User user) throws SignUpDenied {
        users = deserialize();
        
        // find the desired user
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                users.set(i, new User(user));
                serialize();
            }
        }
    }
}

package primitives;

import exceptions.IncorrectUserPassword;
import exceptions.InvalidPassword;
import exceptions.InvalidUsername;
import exceptions.SignUpDenied;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.Objects;

public class User implements Serializable {

    private String username;
    private String password;
    private TreeMap<Token, CreditCard> tokenMap = new TreeMap<>();

    private boolean canRegisterToken;
    private boolean canReadCardId;

    public void setCanRegisterToken(boolean canRegisterToken) {
        this.canRegisterToken = canRegisterToken;
    }
    public void setCanReadCardId(boolean canReadCardId) {
        this.canReadCardId = canReadCardId;
    }

    public boolean canRegisterToken() {
        return canRegisterToken;
    }

    public boolean canReadCardId() {
        return canReadCardId;
    }

    public User(String username, String password) throws SignUpDenied, InvalidUsername, IncorrectUserPassword {
        setData(username,password);

    }

    private void setData(String username, String password) throws SignUpDenied, InvalidUsername, IncorrectUserPassword {
        if(username.isEmpty()){
            throw new SignUpDenied();
        }

        if( password.isEmpty()){
            throw new SignUpDenied();

        }
        if(!isUsernameRelevant(username)){
            throw  new InvalidUsername();
        }
        if(!isPasswordRelevant(password)){
            throw  new IncorrectUserPassword();
        }
        this.username = username;
        this.password = password;
    }

    private boolean isUsernameRelevant(String username) {
        return username.matches("^[a-zA-Z0-9]+$");
    }
    private boolean isPasswordRelevant(String password) {
        return username.matches("^[a-zA-Z0-9]+$");
    }


    public User(User user) throws SignUpDenied, InvalidUsername, IncorrectUserPassword {
        this(user.getUsername(), user.getPassword());
        this.tokenMap = user.getTokenMap();
        this.canReadCardId = user.canReadCardId();
        this.canRegisterToken = user.canRegisterToken();
    }

    public void addTuple(Token token, CreditCard cardId) {
        tokenMap.put(token, cardId);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public TreeMap<Token, CreditCard> getTokenMap() {
        return tokenMap;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User:" + username + ", " + password + ", map="
                + tokenMap + " read: " + canReadCardId + "write: " + canRegisterToken;
    }

}

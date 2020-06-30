package primitives;

import java.io.Serializable;
import java.util.TreeMap;
import java.util.Objects;

public class User implements Serializable {

    private String username;
    private String password;
    private TreeMap<Token, CreditCard> tokenMap = new TreeMap<>();

    private boolean canRegisterToken;
    private boolean canReadCardId;

    /**
     *
     * @param canRegisterToken
     */
    public void setCanRegisterToken(boolean canRegisterToken) {
        this.canRegisterToken = canRegisterToken;
    }

    /**
     *
     * @param canReadCardId
     */
    public void setCanReadCardId(boolean canReadCardId) {
        this.canReadCardId = canReadCardId;
    }

    /**
     *
     * @return
     */
    public boolean canRegisterToken() {
        return canRegisterToken;
    }

    /**
     *
     * @return
     */
    public boolean canReadCardId() {
        return canReadCardId;
    }

    /**
     *
     * @param username 
     * @param password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param user User to be copied.
     */
    public User(User user) {
        this(user.getUsername(), user.getPassword());
        this.tokenMap = user.getTokenMap();
        this.canReadCardId = user.canReadCardId();
        this.canRegisterToken = user.canRegisterToken();
    }

    /**
     *
     * @param token The token of the card
     * @param cardId The card
     */
    public void addTuple(Token token, CreditCard cardId) {
        tokenMap.put(token, cardId);
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
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

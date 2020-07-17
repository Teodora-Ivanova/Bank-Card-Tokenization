package primitives;

import org.junit.Test;

public class InvalidUsernameTest {

    public InvalidUsernameTest() {
    }

    @Test
    public void handleInvalidUsername() throws Exception {
        User user1 = new User("Mimi123","12345");//only letters and numbers allowed in username

    }
}

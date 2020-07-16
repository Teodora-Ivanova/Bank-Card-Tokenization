package primitives;

import org.junit.Test;

public class InvalidPasswordTest {

    public InvalidPasswordTest() {
    }

    @Test
    public void handleEmptySignUpData() throws Exception {
        User user1 = new User("Mimi","da()");//only letters and numbers allowed in password

    }
}

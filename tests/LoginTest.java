import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void doLoginWrongPassword() {
        //testing login with wrong password of existing user (wp expected as a description of wrong password)
        //testing with user alexia, right password:123
        assertEquals("wp",Login.doLogin("alexia","567"));
    }

    @Test
    public void doLoginNotActivated() {
        //testing login with a non activated user (na expected as a description of non activated user)
        //testing with user s who is not activated
        assertEquals("na",Login.doLogin("s","ds"));
    }

    @Test
    public void doLoginNotExisting() {
        //testing login with a not existing user (ukn expected as a description of not existing user)
        //testing with a random user not existing in files
        assertEquals("ukn",Login.doLogin("LazarosT","ds"));
    }

    @Test
    public void doLoginSuccessful() {
        //testing successful login (wp expected as a description of wrong password)
        //testing with user alexia, right password:123
        assertEquals("Login!",Login.doLogin("alexia","123"));
    }


}

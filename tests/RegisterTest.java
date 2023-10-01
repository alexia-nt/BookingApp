import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Before
    public void setUp() throws Exception {
        User a = new User("alexia","ntanti","6969696969","an@gmail.com","AlexiaN","123","Customer",false);
        User b = new User("alexia","ntontou","6996969696","alnt@gmail.com","AlexiaN","321","Provider",false);
    }


    @Test
    public void doRegistrationSameUsername() {
        //testing registration with same username (ut expected as a description of username taken)
        Register.doRegistration("AlexiaN","123","alexia","ntanti","6969696969","an@gmail.com","Customer");
        assertEquals("ut",Register.doRegistration("AlexiaN","321","alexia","ntontou","6996969696","alnt@gmail.com","Provider"));
    }

    @Test
    public void doRegistrationPasswordTooShort() {
        //testing if password is shorter than 3 chars (pw2 expected as a description of password with length of 2 or shorter)
        assertEquals("pw2",Register.doRegistration("SpyrosT","12","spyros","terzis","6996969696","st@gmail.com","Provider"));
    }
}
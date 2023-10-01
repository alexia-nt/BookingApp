import org.junit.Test;

import static org.junit.Assert.*;

public class AccommodationTest {

    @Test
    public void usernameIsOwnerOfThisAccommod() {
        //searching owner of Amalia Palace but giving wrong provider as name
        assertEquals(false,Accommodation.usernameIsOwnerOfThisAccommod("orfeas","spyros hotel"));
    }

    @Test
    public void removeaccomodation() {
        //trying to delete an accommnodation without being the owner of it(expecting no as result)
        assertEquals("no",Accommodation.removeaccomodation("spyros","Amalia Palace"));
    }

    @Test
    public void editAccommodation() {
        //trying to edit accommodation that does not exist(expecting not as result)
        assertEquals("not",Accommodation.editAccommodation("spyros","MedHotel","hotel","223","beautiful hotel","komotini","45","breakfast view".split(" ")));
        //trying to edit accommodation that does exist but not being the owner(expecting not as result)
        assertEquals("not",Accommodation.editAccommodation("orfeas","spyros hotel","hotel","223","beautiful hotel","komotini","45","breakfast view".split(" ")));
    }

}
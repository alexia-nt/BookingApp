import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ReservationTest {


    @Test
    public void newReservationCheckOutBeforeCheckIn() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date checkIn=new Date();
        Date checkOut=new Date();
        try {
            checkIn = format.parse("17-07-2022");
            checkOut = format.parse("16-07-2022");
        }
        catch(ParseException pe){
            System.out.println("ParseException");
        }
        assertEquals("cbc",Reservation.newReservation("alexia","spyros hotel",checkIn,checkOut));
    }

    @Test
    public void newReservationWhenReservedAndWhenNot() {
        //testing reservation when the accommodation is reserved (res expected as a description of reserved accommodation)
        //accommodation spyros hotel is reserved in 16-07-2022
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date checkIn=new Date();
        Date checkOut=new Date();
        try {
            checkIn = format.parse("16-07-2022");
            checkOut = format.parse("17-07-2022");
        }
        catch(ParseException pe){
            System.out.println("ParseException");
        }
        assertEquals("res",Reservation.newReservation("alexia","spyros hotel",checkIn,checkOut));

        //removing the reservation and trying again so the reservation is successful (sr expected as a description of successful reservation)
        Reservation.removeReservationFromFile("spyros hotel",checkIn,"alexia");
        assertEquals("sr",Reservation.newReservation("alexia","spyros hotel",checkIn,checkOut));
    }

}
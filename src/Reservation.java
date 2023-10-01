import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class Reservation{
    private Date checkIn, checkOut;
    private String booker;

    /**
     * This is the main constructor for every new Reservations objects. It initializes the dates
     * of Check-In and Check-Out and keeps the name of the user that makes the reservation
     * in a field called booker.
     * @param checkIn the date of the check in
     * @param checkOut the date of the check out
     * @param booker the username of the customer that makes reservations
     */
    public Reservation(Date checkIn, Date checkOut,String booker){
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.booker = booker;
    }


    /**
     * This method read the file "reservationslist.txt" and checks if there is a list of reservations
     * for this accommodation in the file
     * @param accommodWanted the name of the accommodation that the customer wants to reserve
     * @return if there is a list of reservations for this accommodation
     */
    public static boolean reservationListForThisAccommodExist(String accommodWanted){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(words[0].equals(accommodWanted)){
                    found=true;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This method appends in the file "reservationslist.txt" a new line with the details of the reservation
     * for the accommodation that its name is passed as a parameter.
     * @param accommod_name the name of the accommodation
     * @param checkIn the date of the check-in
     * @param checkOut the date of the check-out
     * @param customer_username the username of the users
     */
    public static void newReservationListForThisAccommod(String accommod_name, Date checkIn, Date checkOut, String customer_username){
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/reservationslist.txt",true));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String checkInStr = dateFormat.format(checkIn);
            String checkOutStr = dateFormat.format(checkOut);
            bw.write(accommod_name+"%"+checkInStr+"%"+checkOutStr+"%"+customer_username+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "reservationslist.txt" and checks if the accommodation
     * is reserved in the time period that the customer wants to make a reservation for.
     * @param accommod_name the name of the accommodation
     * @param checkInWanted the date of the check-in that the customer wants
     * @param checkOutWanted the date of the check-out that the customer wants
     * @return if the accommodation is reserved in the time period that the customer wants
     */
    public static boolean acommodIsReserved(String accommod_name, Date checkInWanted, Date checkOutWanted){
        boolean reserved = false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[0].equals(accommod_name)) {
                            String checkInStr = words[1];
                            String checkOutStr = words[2];
                            try{
                                Date checkIn = new SimpleDateFormat("dd-MM-yyyy").parse(checkInStr);
                                Date checkOut = new SimpleDateFormat("dd-MM-yyyy").parse(checkOutStr);

                                if(!((checkOutWanted.before(checkIn)) || checkInWanted.after(checkOut))){
                                    reserved = true;
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return reserved;
    }

    /**
     * This method reads the file "reservationslist.txt" and finds the line that is
     * about the accommodation that the customer wants to reserve.
     * Then it appends at the end of the line the details of the reservation.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param accommod_name the name of the accommodation
     * @param checkIn the date of the check-in
     * @param checkOut the date of the check-out
     * @param booker the username of the booker
     */
    public static void editReservationListForThisAccommod(String accommod_name, Date checkIn, Date checkOut, String booker){
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[0].equals(accommod_name)) {
                    line = accommod_name+"%";
                    int i=1;
                    for(String reservation: words){
                        if(i!=1) {
                            line = line + reservation + "%";
                        }
                        i++;
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String checkInStr = dateFormat.format(checkIn);
                    String checkOutStr = dateFormat.format(checkOut);
                    line = line + checkInStr+","+checkOutStr+","+booker;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/reservationslist.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the reservation for the user.
     * @param accommod_name the name of the accommodation
     * @param check_in the date of the check-in
     * @param check_out the date of the check-out
     * @param customer_username the username of the customer
     * @return if the reservation was successful or not
     */
    public static boolean addReservationToFile(String accommod_name, Date check_in, Date check_out, String customer_username){
        boolean successful_reservation = false;

            boolean reserved = acommodIsReserved(accommod_name, check_in, check_out);
            if(!reserved){
                newReservationListForThisAccommod(accommod_name, check_in, check_out, customer_username);
                successful_reservation = true;
            }
        return successful_reservation;
    }

    /**
     * This method asks from the user to insert the dates of Check-In and Check-Out.
     * Then it checks if the specific Accommodation that the user wants is availiable during these
     * days. If it is, it adds the object of the Reservation in the Reservations List of this specific accommodation.
     * The method continues as long as the user types "1" (Yes) as an answer to the question of "Do you want to make
     * another reservation in the same Accommoodation?", and it stops once user types "2" (No) in that question.
     * @param customer_username the username of the user
     * @param accommodWanted the name of the accommodation that the customer wants to reserve
     * @param checkIn date of checkIn
     * @param checkOut date of checkOut
     */
    public static String newReservation(String customer_username, String accommodWanted,Date checkIn,Date checkOut ){
        String choice="a";

        if(checkOut.getTime()<=checkIn.getTime()){
            choice="cbc";
        }

        boolean successful_reservation = addReservationToFile(accommodWanted, checkIn, checkOut, customer_username);
        if(!choice.equals("cbc")) {
            if (successful_reservation) {
                choice = "sr";
            } else {
                choice = "res";
            }
        }
        return choice;
    }

    /**
     * This method reads the file "reservationslist.txt" and finds the line that the has the details
     * of the customer's reservation. Then it removes from the line the details of this reservation.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param accommod_name the name of the accommodation
     * @param checkInToBeFound the date of the check-in that we are searching for
     * @param customer_username the username of the customer
     * @return if the reservation was found or not
     */
    public static boolean removeReservationFromFile(String accommod_name, Date checkInToBeFound, String customer_username){
        boolean found_reservation=false;
        int found=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String checkInToBeFoundStr = dateFormat.format(checkInToBeFound);
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                found_reservation=false;
                String[] words = line.split("%");
                if(words[0].equals(accommod_name)) {
                    //line = accommod_name;
                            String checkIn = words[1];
                            String booker = words[3];
                            System.out.println(customer_username.equals(booker));
                            System.out.println(checkInToBeFoundStr.equals(checkIn));
                            if(customer_username.equals(booker) && checkInToBeFoundStr.equals(checkIn)){
                                found_reservation = true;
                                found+=1;
                            }
                }
                if(!found_reservation) {
                    line = words[0]+"%"+words[1]+"%"+words[2]+"%"+words[3];
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/reservationslist.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            //System.out.println("Problem reading file.");
            e.printStackTrace();
        }
        System.out.println(found_reservation);
        System.out.println(found);
        if(found==1){
            found_reservation=true;
        }
        return found_reservation;
    }

    /**
     * This method read the file "cancellationslist.txt" and checks if there is a list of cancellations
     * for this accommodation in the file.
     * @param accommodWanted the name of the accommodation that the customer wants to cancel their reservation from
     * @return if the reservation was found or not
     */
    public static boolean cancellationListForThisAccommodExist(String accommodWanted){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/cancellationslist.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(words[0].equals(accommodWanted)){
                    found=true;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This method appends in the file "cancellationslist.txt" a new line with the details of the cancellation
     * for the accommodation that its name is passed as a parameter.
     * @param accommod_name the name of the accommodation
     * @param checkIn the date of the check-in
     * @param customer_username the username of the customer
     */
    public static void newCancellationListForThisAccommod(String accommod_name, Date checkIn, String customer_username){
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/cancellationslist.txt",true));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String checkInStr = dateFormat.format(checkIn);
            bw.write(accommod_name+"%"+checkInStr+"%"+customer_username+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "cancellationslist.txt" and finds the line that is
     * about the accommodation that the customer wants to cancel their reservation from.
     * Then it appends at the end of the line the details of the reservation.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * with the new one and store the updated line in StringBuffer.
     * @param accommod_name the name of the accommodation
     * @param checkIn the date of the check-in
     * @param booker the username of the booker
     */
    public static void editCancellationListForThisAccommod(String accommod_name, Date checkIn, String booker){
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/cancellationslist.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;
            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                    line = accommod_name+"%";
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String checkInStr = dateFormat.format(checkIn);
                    line = line + checkInStr+"%"+booker;
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream("src/files/cancellationslist.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks from the user to insert the date of Check-In of his/her reservation.
     * If a reservation exists on that date and it is made by the user that requested to remove the Reservation
     * it calls the function @code cancelReservation
     * which removes the object of the specific Reservation from the Reservations List of the Accommodation.
     * In any other case, it informs the user that there is no Reservation on that date or made by him/her and the user
     * has the choice of trying again or exiting.
     * The method continues as long as the user types "1" (Yes) as an answer to the question of "Do you want to cancel
     * another reservation in the same Accommoodation?", and it stops once user types "2" (No) in that question.
     * @param customer_username the username of the user
     * @param accommod_name the name of the accommodation that the customer wants to delete reservation for
     */
    public static String removeReservation(String customer_username, String accommod_name, Date checkIn){
        String message;
            boolean successful_cancellation = removeReservationFromFile(accommod_name, checkIn, customer_username);
            if(successful_cancellation){
                newCancellationListForThisAccommod(accommod_name, checkIn, customer_username);
                message = "sc";
            }
            else{
                message = "nsc";
            }
        return message;
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and checks if the accommodation is in file or not.
     * @param keyname the name of the accommodation
     * @return if the accommodation exists or not
     */
    public static boolean accommodationExists(String keyname){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(words[0].equals(keyname)){
                    found=true;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return found;
    }

    /**
     * This method reads the file "reservationslist.txt" and print the reservations that
     * this accommodation has.
     * @param accommod_name the name of the accommodation
     * @return if the accommodation has reservations or not
     */
    public static boolean showReservationsForThisAccommodation(String accommod_name){
        boolean hasReservations=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[0].equals(accommod_name)){
                    if(words.length>1){
                        int i=0; //index of words
                        for(String reservation: words){
                            if(i!=0) {
                                String[] reserv_details = reservation.split(",");
                                System.out.println(reserv_details[0] + " " + reserv_details[1] + " " + reserv_details[2]);
                            }
                            i++;
                        }
                        hasReservations = true;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return hasReservations;
    }

    /**
     * This method is used to help Admin search through all Reservations of all the Accommodations.
     * The user can search by two criteria. (1) By the name of Accommodation which gives as result
     * the Reservations of the Accommodation that has the name that the Admin types. (if they exist)
     * (2) By a specific date which gives as result the Reservations of all Accommodations on the
     * given date (if they exist).
     * the booker or not, and if it is, it removes the object from the List.
     */
    public static void searchReservations(){
        String choice = "0";
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("Type the name of the accommodation to show its reservations");
            String keyname = in.nextLine();
            if(accommodationExists(keyname)) {
                boolean hasReservations = showReservationsForThisAccommodation(keyname);
                if (!hasReservations) {
                    System.out.println("This accommodation does not have any reservations yet.");
                }
            }
            else{
                System.out.println("This accommodation does not exist!");
            }
            System.out.println("Do you want to search other reservations? (Type (1) for Yes or (2) for No)");
            choice = in.nextLine();
        } while(choice.equals("1"));
    }

    /**
     * Sets the date of Check-In.
     * @param checkIn the date of the check in
     */
    public void setCheckIn(Date checkIn){
        this.checkIn = checkIn;
    }

    /**
     * Sets the date of Check-Out.
     * @param checkOut the date of the check out
     */
    public void setCheckOut(Date checkOut){
        this.checkOut = checkOut;
    }

    /**
     * Sets the name of the user that does the reservation.
     * @param booker the username of the booker
     */
    public void setBooker(String booker){
        this.booker = booker;
    }

    /**
     * Returns the date of Check-In of a Reservation object.
     * @return checkIn
     */
    public Date getCheckIn(){
        return this.checkIn;
    }

    /**
     * Returns the date of Check-Out of a Reservation object.
     * @return checkOut
     */
    public Date getCheckOut(){
        return this.checkOut;
    }

    /**
     * Returns the name of the user that did the reservation.
     * @return booker
     */
    public String getBooker(){
        return this.booker;
    }
}
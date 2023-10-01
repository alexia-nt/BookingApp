import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 * This class is the User Interface of this BookingPlatform.
 * It consists of methods that show messages to User and
 * also is used to select data from the user and send them
 * to other methods.
 */
class Menu {

    private static Scanner in = new Scanner(System.in);

    /**
     * This is the Main-Menu which appears to all the users every-time
     * they enter the platform or when they Log-Out.
     */
    public static String m1() {
        System.out.println("------------------------------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("-------Type 1 or 2 or 3-------");
        String choice = in.nextLine();
        return choice;
    }

    /**
     * This method asks from the user his/her Log-In Data.
     * @return LogIndata
     */
    public static String LoginMenu() {
        String LogIndata;
        System.out.println("Enter your username");
        String username = in.nextLine();
        System.out.println("Enter your password");
        String password = in.nextLine();
        LogIndata = username + "-" + password;
        return LogIndata;
    }

    /**
     * This method asks from the user his/her Register Data.
     * @return Registerdata
     */
    public static String RegisterMenu() {
        String Registerdata;
        System.out.println("Choose your username");
        String username = in.nextLine();
        System.out.println("Choose your password // It must be longer than 2 characters");
        String password = in.nextLine();
        Registerdata = username + "-" + password;
        return Registerdata;
    }

    /**
     * This is the Menu for the Customers. It appears only when a Customer
     * enters the platform. It shows all actions that can be done by a Customer.
     * After showing the Menu, it requires from the user to choose one number
     * between 1 and 5. Each number is connected to a method from other class
     * that should be called. The choice is being saved to a String that the
     * method returns.
     * @return choice
     */
    public static String MenuCustomer() {
        System.out.println("------------------------------");
        System.out.println("1. Search acommodation");
        System.out.println("2. Reserve acommodation");
        System.out.println("3. Cancel a reservation");
        System.out.println("4. Your Profile Card");
        System.out.println("5. Log Out");
        System.out.println("--Type a number between 1 and 5--");
        String choice = in.nextLine();
        while (!(Arrays.asList("1", "2", "3", "4", "5").contains(choice))) {
            System.out.println("--Type a number between 1 and 5--");
            choice = in.nextLine();
        }
        return choice;
    }

    /**
     * This is the Menu for the Providers. It appears only when a Provider
     * enters the platform. It shows all actions that can be done by a Provider.
     * After showing the Menu, it requires from the user to choose one number
     * between 1 and 5. Each number is connected to a method from other class
     * that should be called. The choice is being saved to a String that the
     * method returns.
     * @return choice
     */
    public static String MenuProvider() {
        System.out.println("------------------------------");
        System.out.println("1. Add acommodation");
        System.out.println("2. Edit your acommodation");
        System.out.println("3. Remove your acommodation");
        System.out.println("4. Your Profile Card");
        System.out.println("5. Log Out");
        System.out.println("--Type a number between 1 and 5--");
        String choice = in.nextLine();
        while (!(Arrays.asList("1", "2", "3", "4", "5").contains(choice))) {
            System.out.println("--Type a number between 1 and 5--");
            choice = in.nextLine();
        }
        return choice;
    }

    /**
     * This is the Menu for the Admin. It appears only when a Admin
     * enters the platform. It shows all actions that can be done by a Admin.
     * After showing the Menu, it requires from the user to choose one number
     * between 1 and 5. Each number is connected to a method from other class
     * that should be called. The choice is being saved to a String that the
     * method returns.
     * @return choice
     */
    public static String madm() {
        System.out.println("------------------------------");
        System.out.println("1. Show reservations");
        System.out.println("2. Search reservations");
        System.out.println("3. Show users");
        System.out.println("4. Search users");
        System.out.println("5. Approve new users");
        System.out.println("6. Send Message to a user");
        System.out.println("7. Log Out");
        System.out.println("--Type a number between 1 and 7--");
        String choice = in.nextLine();
        while (!(Arrays.asList("1", "2", "3", "4", "5", "6", "7").contains(choice))) {
            System.out.println("--Type a number between 1 and 7--");
            choice = in.nextLine();
        }
        return choice;
    }

    /**
     * This method shows all the types of Accommodations that exist in
     * the platform.
     */
    public static void mtypes(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodtypes.txt"));
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method shows the data of all the registered users in the platform.
     */
    public static void showusers(){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(!words[4].equals("admin")){
                    System.out.println("Username: " + words[4] + " Password: " + words[5] + " Full Name: " + words[0] + " " + words[1] + " Phone Number: " + words[2] + " Mail: " + words[3] + " Type of user: " + words[6]);
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Below there are some Welcoming or Warning messages
     */
    public static void WelcomeMessage(){
        System.out.printf(" Welcome to our Booking platform! %n You can navigate to the platform by using the menu %n");
    }

    public static void RegistrationMessage(){
        System.out.printf(" To complete your registration fill out the next fields. %n ");
    }

    public static void UsernameWarning(){
        System.out.printf(" This username you typed already exists. %n");
    }

    public static void PasswordWarning(){
        System.out.printf(" The password must contain at least 2 characters %n");
    }

    public static void LoginWarning(){
        System.out.printf(" Password or username incorrect %n");
    }

    public static void AccountNotActivated(){
        System.out.printf(" Account is not activated yet %n");
    }

    public static void InvalidInput(){
        System.out.printf(" Invalid Input %n");
    }

    public static void AccommodationWarning(){
        System.out.printf(" This accommodation does not exist! %n");
    }

    public static String AccommodationName(){
        System.out.println("Type the name of accommodation: ");
        String AccName = in.nextLine();
        return AccName;
    }

    public static String backMenu(){
        System.out.println("Try again (1) or Back to Main Menu (2) or Exit (3)");
        String choice = in.nextLine();
        return choice;
    }

    public static void MessageSent(){
        System.out.println("Message Sent!");
    }

    /**
     * This method reads the file "messages.txt" and changes the field of the line from "false" to "true"
     * for the messages that the user read.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * and store the updated line in StringBuffer.
     * Then, we overwrite the file with the new line.
     * @param receiver the username of the receiver
     * @param message  the message
     */
    public static void setMessageAsRead(String receiver, String message) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/messages.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                String[] message_details = line.split("%");
                if(message_details[0].equals(receiver) && message_details[1].equals(message)) {
                    line = message_details[0]+"%"+message_details[1]+"%true"; // replace the line here
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/messages.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method shows all the messages a user has and divides them by Read and Unread messages.
     * @param username username of the receiver
     */
    public static void showMessages(String username){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/messages.txt"));
            String line;
            while((line = br.readLine()) != null){
                String message_details[] = line.split("%");
                if(message_details[0].equals(username) && message_details[2].equals("false")){
                    System.out.printf("New unread message: " + "\n" + message_details[1] + "\n");
                    setMessageAsRead(message_details[0], message_details[1]);
                }
                else if(message_details[0].equals(username) && message_details[2].equals("true")){
                    System.out.printf("Already read message: " + "\n" + message_details[1] + "\n");
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method asks the user to enter some more details about them.
     * @return registerDetails
     */
    public static String[] registerDetailsString(){
        String registerString;
        System.out.println("Enter your First Name");
        registerString = in.nextLine();
        System.out.println("Enter your Last Name");
        registerString = registerString + "-" + in.nextLine();
        System.out.println("Enter your Phone Number");
        registerString = registerString + "-" + in.nextLine();
        System.out.println("Enter your Mail");
        registerString = registerString + "-" + in.nextLine();
        System.out.println("Enter the type of user: Provider(1) / Costumer(2)");
        registerString = registerString + "-" + in.nextLine();

        String[] registerDetails = registerString.split("-");

        while(!registerDetails[4].equals("1") && !registerDetails[4].equals("2")){
            Menu.InvalidInput();
            System.out.println("Enter the type of user: Provider(1) / Costumer(2)");
            registerDetails[4] = in.nextLine();
        }
        return registerDetails;
    }

    /**
     * This method asks the user to enter the details about their accommodation.
     * @return accommodDetails
     */
    public static String[] accommodDetailsString(){
        String accommodString;
        System.out.println("Enter your ID Number");
        accommodString = in.nextLine();
        System.out.println("Enter description about your accomodation");
        accommodString = accommodString + "-" + in.nextLine();
        System.out.println("Enter the area of your accomodation");
        accommodString = accommodString + "-" + in.nextLine();
        System.out.println("Enter price per night");
        accommodString = accommodString + "-" + in.nextLine();
        System.out.println("Do you want to add an image (Y/N)");
        accommodString = accommodString + "-" + in.nextLine();

        String[] accommodDetails = accommodString.split("-");

        if(accommodDetails[4].equals("Y") || (accommodDetails[4].equals("y"))){
            accommodDetails[4] = "true";
        }
        else{
            accommodDetails[4] = "false";
        }
        return accommodDetails;
    }

    /**
     * This method reads the file "usersdatabase.txt" and returns "true" if the username exists or "false" is doesn't.
     * @param username the username that we are searching for
     * @return         if the username is in the database or not
     */
    public static boolean usernameExists(String username){
        boolean found=false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while(((line = br.readLine()) != null) && !found){
                String[] words = line.split("%");
                if(username.equals(words[4])){
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
     * This method asks the admin to enter some  details about the message.
     * @return messageDetails
     */
    public static String[] messageDetails(){
        String messageString;
        System.out.println("Enter the username of the receiver:");
        messageString = in.nextLine();
        boolean user_exists = usernameExists(messageString);
        while(!user_exists){
            System.out.println("This user does not exist! Enter another username of the receiver:");
            messageString = in.nextLine();
            user_exists = usernameExists(messageString);
        }
        System.out.println("Enter the message you want to send");
        messageString = messageString + "-" + in.nextLine();
        String[] messageDetails = messageString.split("-");
        return messageDetails;
    }


    /**
     * This method shows the fields that a Provider can edit in an already
     * existing accommodation. The Provider can choose a number between 1 and 8
     * based on the field he/she wants to edit. The method then will return the
     * choice so the Provider can edit the selected field.
     * @return choice
     */
    public static String EditMenu(){
        System.out.println("Type the category number you want to edit:");
        System.out.println("------------------------------");
        System.out.println("1. Name");
        System.out.println("2. Type");
        System.out.println("3. ID Number");
        System.out.println("4. Description");
        System.out.println("5. Area");
        System.out.println("6. Price");
        System.out.println("7. Has Image");
        System.out.println("8. Facilities");
        System.out.println("--Type a number between 1 and 8--");
        String choice = in.nextLine();
        while(!(Arrays.asList("1","2","3","4","5","6","7","8").contains(choice)))
        {
            System.out.println("--Type a number between 1 and 8--");
            choice = in.nextLine();
        }
        return choice;
    }

    /**
     * This method shows the Reservations of every Accommodation in the platform.
     * if there are no Reservations made in that Accommodation it shows a messages
     * that there are not any reservations
     */
    public static void showReservations(){
        int cnt = 0;
        System.out.println("----------RESERVATIONS----------");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                if(words.length>1){
                    int i=0; //index of words
                    for(String reservation: words){
                        if(i!=0) {
                            String reserv_details[] = reservation.split(",");
                            System.out.println(words[0]+": "+reserv_details[0] + " " + reserv_details[1] + " " + reserv_details[2]);
                            cnt++;
                        }
                        i++;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(cnt==0){
            System.out.println("No reservations found");
        }
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and return the username
     * of the provider based on the name of the accommodation.
     * @param accommod_name the name of the accommodation
     * @return the username of the provider of this accommodation
     */
    public static String getProviderOfThisAccommod(String accommod_name){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                if(words[0].equals(accommod_name)){
                    return words[7];
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //if accommod does not exist
        return "-";
    }

    /**
     * This method shows for each Provider the following: Username, Reservations in his/her Accommodation,
     * Cancellations in his/her Accommodation and messages by the admin.
     * @param username the username of the user
     */

    public static void showProviderProfileCard(String username,ArrayList <Message> messages){
        int numOfReserv=0, numOfCancel=0;
        System.out.println("----------YOUR PROFILE CARD----------");
        System.out.printf("Your username is:"+username + "%n");

        System.out.println("----------RESERVATIONS----------");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                if(username.equals(getProviderOfThisAccommod(words[0]))) {
                    if (words.length > 1) { //accommodation has reservations
                        int i = 0; //index of words
                        for (String reservation : words) {
                            if (i != 0) {
                                String reserv_details[] = reservation.split(",");
                                System.out.println(words[0] + " Check-in: " + reserv_details[0] + " Check-out: " + reserv_details[1] + " Booker: " + reserv_details[2]);
                                numOfReserv++;
                            }
                            i++;
                        }
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(numOfReserv==0){
            System.out.println("No reservations found");
        }

        System.out.println("----------CANCELLATIONS----------");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/cancellationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                if(username.equals(getProviderOfThisAccommod(words[0]))) {
                    int i = 0; //index of words
                    for (String reservation : words) {
                        if (i != 0) {
                            String reserv_details[] = reservation.split(",");
                            System.out.println(words[0] + " Check-in: " + reserv_details[0] + " Booker: " + reserv_details[1]);
                            numOfCancel++;
                        }
                        i++;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(numOfCancel==0){
            System.out.println("No cancellations found");
        }
        Menu.showMessages(username);
    }

    /**
     * This method shows for each Costumer the following: Username, Reservations in his/her Accommodation,
     * Cancellations in his/her Accommodation and messages by the admin.
     * @param username the username of the user
     */
    public static void showCustomerProfileCard(String username,ArrayList <Message> messages){
        int numOfReserv=0, numOfCancel=0;
        System.out.println("----------RESERVATIONS----------");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/reservationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                if(words.length>1){ //accommodation has reservations
                    int i=0; //index of words
                    for(String reservation: words){
                        if(i!=0) {
                            String reserv_details[] = reservation.split(",");
                            if (username.equals(reserv_details[2])) {
                                System.out.println(words[0] + " Check-in: " + reserv_details[0] + " Check-out: " + reserv_details[1] + " Booker: " + reserv_details[2]);
                                numOfReserv++;
                            }
                        }
                        i++;
                    }
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(numOfReserv==0){
            System.out.println("No reservations by you found");
        }
        System.out.println("----------CANCELLATIONS---------");
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/cancellationslist.txt"));
            String line;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                int i=0; //index of words
                for(String reservation: words){
                    if(i!=0) { //ignore name of accommodation
                        String reserv_details[] = reservation.split(",");
                        if (username.equals(reserv_details[1])) {
                            System.out.println(words[0] + " Check-in: " + reserv_details[0] + " Booker: " + reserv_details[1]);
                            numOfCancel++;
                        }
                    }
                    i++;
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        if(numOfCancel==0){
            System.out.println("No cancellations by you found");
        }
        Menu.showMessages(username);
    }

}
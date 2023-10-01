import java.util.Scanner;
import java.io.*;

class User{
    private String firstname;
    private String lastname;
    private String phone;
    private String mail;
    private String username;
    private String password;
    private String type;
    private boolean approved;

    /**
     * This is a constructor for every new User object. It initializes only the field that keeps data about
     * the status of User created. When each user is first
     * created it is set on false cause user is not approved * yet.
     */
    public User(){
        this.approved=false;
    }

    /**
     * This is another constructor for every new User object. It
     * initializes the fields of this class
     * based on the values of parameters.
     * @param firstname first name of user
     * @param lastname last name of user
     * @param phone phone of user
     * @param mail mail of user
     * @param username username of user
     * @param password password of user
     * @param type type of user
     * @param approved status of user
     */
    public User(String firstname,String lastname,String phone,String mail,String username,String password,String type,boolean approved){
        this.approved = approved;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.mail = mail;
        this.username = username;
        this.password = password;
        if(type.equals("1")){
            this.type="Provider";
        }
        else{
            this.type="Costumer";
        }
    }

    /**
     * This method is used to help Admin search through all  * the Users created on the boooking platform.
     * Admin can search any User by just typing User's
     * usernmame. If the user does not exist the platform
     * states that this username does not exist.
     * The method keeps on going as long as the {@link User}* chooses 1 (Yes) to the question "Do you want to *search more users?"
     * If the answer is 2 this method ends.
     */
    public static void searchusers(){
        String key;
        Scanner in = new Scanner(System.in);

        do{
            System.out.println("Type the username of user you want to search");
            key = in.nextLine();

            boolean found=false;
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/usersdatabase.txt"));
                String line;
                while(((line = br.readLine()) != null) && !found){
                    String[] words = line.split("%");
                    if(key.equals(words[4])){
                        System.out.println("Username: " + words[4] + " Password: " + words[5] + " Full Name: " + words[0] + " " + words[1] + " Phone Number: " + words[2] + " Mail: " + words[3] + " Type of user: " + words[6]);
                        found=true;
                    }
                }
                br.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if(!found){
                System.out.println("No user with such a username exists");
            }
            System.out.println("Do you want to search more users? (Type (1) for Yes or (2) for No)");
            key = in.nextLine();
        } while(key.equals("1"));
    }

    /**
     * This method reads the file "usersdatabase.txt" and checks if the user that tries to log in
     * has an activated account or not.
     * @param username the username of the user
     * @return approved
     */
    public static boolean approved(String username){
        boolean approved=false;
        if(username.equals("admin")){
            approved = true;
        }
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(username.equals(words[4])){
                    approved = Boolean.parseBoolean(words[7]);
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return approved;
    }

    /**
     * This method reads the file "usersdatabse.txt" and changes the field of the line from "false" to "true"
     * for the user that the admin wants to approve.
     * To achieve this, we read the file one line at a time and replace the line as we read the file
     * and store the updated line in StringBuffer.
     * Then, we overwrite the file with the new line.
     * @param username the username of the user the admin wants to approve
     */
    public static void approveUser(String username) {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(username.equals(words[4])) {
                    line = words[0]+"%"+words[1]+"%"+words[2]+"%"+words[3]+"%"+words[4]+"%"+words[5]+"%"+words[6]+"%true"; // replace the line here
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/usersdatabase.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "usersdatabse.txt" and changes the field of the line from "false" to "true"
     * for the users that are not approved.
     * To achieve this, we read the file one line at a time and replace the lines as we read the file
     * and store the updated lines in StringBuffer.
     * Then, we overwrite the file with the new lines.
     */
    public static void approveAllUsers() {
        try {
            // input the (modified) file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                String[] words = line.split("%");
                if(words[7].equals("false")) {
                    line = words[0]+"%"+words[1]+"%"+words[2]+"%"+words[3]+"%"+words[4]+"%"+words[5]+"%"+words[6]+"%true"; // replace the line here
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();

            // write the new string with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream("src/files/usersdatabase.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the file "usersdatabase.txt" and returns "true" if the username exists or "false" is doesn't.
     * @param username the username that we are searhing for
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
     * This method read the file "usersdatabase.txt" and print the users that are waiting for approval.
     * This method also return "true" if there are users that are waiting for approval and "false" if there are
     * no users waiting for approval.
     * @return if there are users waiting for approval or not
     */
    public static boolean printUsersWaitingForApproval(){
        boolean found = false;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(words[7].equals("false")){
                    System.out.println(words[4]);
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
     * This method helps Admin to approve the latest registered users.
     * Firstly, it shows to the Admin the users that are waiting for approval.
     * If there are not any users waiting for approval it shows a message and the method
     * ends. Otherwise, it asks from the Admin to either aprove all users (1) or to approve
     * them one-by-one (2) based on the username that the Admin types.
     */
    public static void approveusers(){
        Scanner in = new Scanner(System.in);

        boolean found = printUsersWaitingForApproval();

        String choice;
        if(!found){
            System.out.println("No users waiting for approval");
            choice = "2";
        }
        else{
            System.out.println("Do you want to approve all of the above users? (Type (1) for Yes (2) for No)");
            choice = in.nextLine();
            if(choice.equals("1")){
                approveAllUsers();
            }
            else{
                do{
                    System.out.println("Type the username of the user you want to approve");
                    choice = in.nextLine();

                    if(usernameExists(choice) && !approved(choice)){
                        approveUser(choice);
                    }
                    else{
                        System.out.println("This username is not in the list!");
                        //choice = in.nextLine();
                    }
                    System.out.println("The following users are waiting for approval:");
                    found = printUsersWaitingForApproval();
                    if(!found){
                        System.out.println("No users waiting for approval");
                        choice = "2";
                    }
                    else{
                        System.out.println("Do you want to approve more users? (Type (1) for Yes or (2) for No)");
                        choice = in.nextLine();
                    }
                }while(choice.equals("1"));
            }
        }
    }

    /**
     * Getters and setters of the private fields of User class.
     */
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setMail(String mail){
        this.mail = mail;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setApproved(boolean approved){
        this.approved = approved;
    }

    public String getFirstName(){
        return this.firstname;
    }
    public String getLastName(){
        return this.lastname;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getMail(){
        return this.mail;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getType(){
        return this.type;
    }
    public boolean getApproved(){
        return this.approved;
    }

    public String showUser(){
        return("First Name: " + firstname +
                " Last Name: " + lastname +
                " Phone Number: " + phone +
                " Mail: " + mail +
                " Type: " + type );
    }

    public String showCard(){
        return("First Name: " + firstname +
                " Last Name: " + lastname +
                " Phone Number: " + phone +
                " Μail: " + mail +
                " Type: " + type );
    }
}
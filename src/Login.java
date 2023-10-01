import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Login{
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
     * This method reads the file "usersdatabase.txt" and returns the password of the user.
     * @param username the username of the user
     * @return         the password of the user
     */
    public static String getPassword(String username){
        String password = "";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(username.equals(words[4])){
                    password = words[5];
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return password;
    }

    /**
     * This method reads the file "usersdatabase.txt" and returns the type of the user. (1 for Provider and 2 for Customer)
     * @param username the username of the user
     * @return         the password of the user
     */
    public static int getType(String username){
        int type = 0;
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/usersdatabase.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("%");
                if(username.equals(words[4])){
                    type = Integer.parseInt(words[6]);
                }
            }
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return type;
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
     * This method is used so each user can log in and navigate through the menu in the platform.
     * Firstly, it asks from the User to enter his/her username and password and if they are right
     * it presents the Menu of actions they are able to do based on the type of User
     * If the username already exists or the password is not longer than 2 characters the method ask the *user to either complete the details again or go back to menu.
     * @return boolean exit
     */
    public static String doLogin(String username,String password){
        String exit;

            if(usernameExists(username) && approved(username)){
                String expected_password = getPassword(username);
                if (password.equals(expected_password)) { //login successful
                    exit = "Login!";
                }
                else{
                    exit="wp";
                }
            }
            else if(usernameExists(username) && !approved(username)){
                exit="na";
            }
            else{ //password or username incorrect
                exit="ukn";
            }
        return exit;
    }
}


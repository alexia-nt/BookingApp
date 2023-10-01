import java.io.*;

class Register{
    /**
     * This method reads the file "usersdatabase.txt" and returns "true" if the username is already taken or "false" is it's not.
     * @param username the username that the user wants to use for their registration
     * @return         if the username is already taken or not
     */
    public static boolean usernameTaken(String username){
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

    public static void addUserToFile(String username, String password,String firstname,String lastname,String phone,String mail,String type) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/usersdatabase.txt",true));
            //String[] registerDetails = Menu.registerDetailsString();
            bw.write(firstname+"%"+lastname+"%"+phone+"%"+mail+"%"+username+"%"+password+"%"+type+"%"+false+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is responsible for Registrations. Firstly, it gets the username and the password
     * that the new user would like to have. Then, if the username does not exist and the password
     * is longer than 2 characters it asks the user to enter more details about him/her and adds it's *information in the rest of the Users that are already registered in the platform.
     * If the username already exists or the password is not longer than 2 characters the method ask the *user to either complete the details again or go back to menu.
     * @return exit
     */
    public static String doRegistration(String username,String password,String firstname,String lastname,String phone,String mail,String type){
        String exit="n";
            if(!usernameTaken(username) && password.length()>2){
                addUserToFile(username, password,firstname,lastname,phone,mail,type);
                exit="reg";
            }
            else {
                if(usernameTaken(username)){
                    exit="ut";
                }
                else if(password.length()<=2){
                    exit="pw2";
                }
            }
        return exit;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Customer extends User{
    /**
     * Default constructor of Customer uses the default constructor of
     * it's super-class User.
     */
    public Customer(){
        super();
    }

    /**
     * This is another constructor for every new Costumer object. It
     * initializes the fields of the super-class User.
     * @param firstname the first name of the customer
     * @param lastname the last name of the customer
     * @param phone the phone number of the customer
     * @param mail the email of the customer
     * @param username the username of the customer
     * @param password the password of the customer
     * @param type the type of the user
     * @param approved checks if the customer is approved
     */
    public Customer(String firstname,String lastname,String phone,String mail,String username,String password,String type,boolean approved){
        super(firstname,lastname,phone,mail,username,password,type,approved);
    }

    /**
     * This method reads the file "accommodationsdatabase.txt" and checks if the accommodation is in the database or not.
     * @param keyname the name of the accommodation we are searching for
     * @return        if the accommodation is in the database or not
     */
    public static boolean accommodationExists(String keyname){
        boolean found = false;
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

}

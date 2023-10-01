import java.util.ArrayList;

class Provider extends User{
    /**
     * Default constructor of Customer uses the default constructor of
     * it's super-class User.
     */
    public Provider(){
        super();
    }

    /**
     * This is another constructor for every new Provider object. It
     * initializes the fields of the super-class User.
     * @param firstname the first name of the provider
     * @param lastname the last name of the provider
     * @param phone the phone number of the provider
     * @param mail the email of the provider
     * @param username the username of the provider
     * @param password the password of the provider
     * @param type the type of the user
     * @param approved checks if the provider is approved
     */
    public Provider(String firstname,String lastname,String phone,String mail,String username,String password,String type,boolean approved){
        super(firstname,lastname,phone,mail,username,password,type,approved);
    }

}

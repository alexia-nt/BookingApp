import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

class Message{
    private boolean read;
    private HashMap<String,String> message=new HashMap<>();

    /**
     * This is another constructor for every new Provider object.
     * @param username contains the username of the receiver
     * @param message contains the message that should be sent
     */
    public Message(String username,String message){
        this.read=false;
        this.message.put(username,message);
    }

    /**
     * This method writes in the file "messages.txt" the name of the receiver and the message
     * that the admin wants to send.
     * @param receiver the username of the receiver
     * @param message  the message that the admin wants to send
     */
    public static void addMessageToFile(String receiver, String message) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("src/files/messages.txt",true));
            bw.write(receiver+"%"+message+"%"+"false\n"); //false because message is not read yet
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is another constructor for every new Provider object.
     */
    public static String sendmessage(String username,String message){
        //String[] messagedetails=Menu.messageDetails();
        String res="nf";
        if(User.usernameExists(username)){
            addMessageToFile(username,message);
            res="ok";
        }
        return res;
        //Menu.MessageSent();
    }

    /**
     * Setters and Getters
     */
    public HashMap<String,String> getMessage(){
        return this.message;
    }

    public boolean isRead(){
        return this.read;
    }

    public void setRead(boolean read){
        this.read=read;
    }
}
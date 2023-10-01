import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProviderProfileCardGUI implements ActionListener {
    public JFrame frame1;
    private JPanel panel11;
    private JLabel usernameLabel;
    private JButton reservationsButton;
    private JButton cancellationsButton;
    private JButton accommodationsButton;
    private JButton messagesButton;
    private JPanel panel12;
    private JPanel Panel1;
    private JScrollPane JScrollPanel1;
    private JTable table1;
    private JButton menuButton;
    private JList unreadmessagesList;
    private JPanel messagePanel;
    private JLabel unreadmesLabel;
    private JLabel readmessageLabel;
    private JList readmessagesList;
    private JButton logOutButton;
    private JTable Accommodations;
    private String owner;

    public void startProfileCardGUI(String username) {
        owner=username;
        frame1 = new JFrame();
        frame1.setSize(1400,800);
        frame1.add(Panel1);
        usernameLabel.setText(username);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reservationsButton.addActionListener(this);
        cancellationsButton.addActionListener(this);
        accommodationsButton.addActionListener(this);
        messagesButton.addActionListener(this);
        logOutButton.addActionListener(this);
        menuButton.addActionListener(this);
        JScrollPanel1.setVisible(false);
        messagePanel.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == accommodationsButton) {
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                String line="";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                    if (owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        noOfLines++;
                    }
                }
                String[][] newdata = new String[noOfLines][7];
                for (int j = 0; j < noOfLines; j++) {
                    for (int k = 0; k < 7; k++) {
                        newdata[j][k] = "";
                    }
                }

                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                BufferedReader br1 = new BufferedReader(
                        new FileReader("src/files/facilities.txt"));
                String line1="";
                int i = 0;
                while ((line = br.readLine()) != null) {
                    String words[] = line.split("%");
                    line1 = br1.readLine();
                    String words1[] = line1.split("%");
                    if (owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        if (words.length > 1) { //accommodation has reservations
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            newdata[i][4] = words[4];
                            newdata[i][5] = words[5];
                            for (String x : words1) {
                                if (!(x.equals(words[0])))
                                    newdata[i][6] = newdata[i][6] + x + " ";
                            }
                          i += 1;
                        }
                    }
                }
                String columns[] = {"Name", "Type", "ID", "Description", "Area", "Price", "Facilities"};
                table1.setModel(new DefaultTableModel(newdata,columns));
                table1.setAutoCreateRowSorter(true);
                table1.setGridColor(Color.black);
                br.close();
                br1.close();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
        }
        else if (source == cancellationsButton) {
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/cancellationslist.txt"));
                String line="";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                    if (owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        if (words.length > 1) {
                            noOfLines++;
                        }
                    }
                }
                String[][] newdata = new String[noOfLines][4];
                for (int j = 0; j < noOfLines; j++) {
                    for (int k = 0; k < 3; k++) {
                        newdata[j][k] = "";
                    }
                }
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/cancellationslist.txt"));
                //String line;
                int i = 0;
                while((line = br.readLine()) != null){
                    String words[] = line.split("%");
                    if(owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        if (words.length > 1) { //accommodation has reservations
                             //index of words
                            /*for (String reservation : words) {
                                if (i != 0) {
                                    String reserv_details[] = reservation.split(",");
                                    System.out.println(words[0] + " Check-in: " + reserv_details[0] + " Check-out: " + reserv_details[1] + " Booker: " + reserv_details[2]);
                                    //numOfReserv++;
                                }
                                i++;*/
                               newdata[i][0]=words[0];
                               newdata[i][1]=words[1];
                               newdata[i][2]=words[2];
                            //}
                           i += 1;
                        }
                    }
                }
                String columns[] = {"Accommodation", "Check-In", "Booker"};
                table1.setModel(new DefaultTableModel(newdata,columns));
                table1.setAutoCreateRowSorter(true);
                table1.setGridColor(Color.black);
                br.close();
            }
            catch (IOException e2){
                e2.printStackTrace();
            }
        }
        else if (source == reservationsButton) {
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/reservationslist.txt"));
                String line = "";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                    if (owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        if (words.length > 1) {
                            noOfLines++;
                        }
                    }
                }
                String[][] newdata = new String[noOfLines][4];
                for (int j = 0; j < noOfLines; j++) {
                    for (int k = 0; k < 4; k++) {
                        newdata[j][k] = "";
                    }
                }
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/reservationslist.txt"));
                //String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    String words[] = line.split("%");
                    if (owner.equals(Menu.getProviderOfThisAccommod(words[0]))) {
                        if (words.length > 1) { //accommodation has reservations
                            //index of words
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            //}
                            i += 1;
                        }
                    }
                }
                String columns[] = {"Accommodation", "Check-In", "Check-Out", "Booker"};
                table1.setModel(new DefaultTableModel(newdata, columns));
                table1.setAutoCreateRowSorter(true);
                table1.setGridColor(Color.black);
                br.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
       else if(source == messagesButton){
            messagePanel.setVisible(true);
            String readmessagesstring[]= {};
            String unreadmessagesstring[]= {};
            String readmes="",unreadmes="";
            int i=0;
            int j=0;
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/messages.txt"));
                String line;
                while((line = br.readLine()) != null){
                    String message_details[] = line.split("%");
                    if(message_details[0].equals(owner) && message_details[2].equals("false")){
                        //System.out.printf("New unread message: " + "\n" + message_details[1] + "\n");
                        unreadmes+="Admin: "+ message_details[1]+ "\n"+"%";
                        Menu.setMessageAsRead(message_details[0], message_details[1]);
                        i++;
                    }
                    else if(message_details[0].equals(owner) && message_details[2].equals("true")){
                        //System.out.printf("Already read message: " + "\n" + message_details[1] + "\n");
                        readmes+="Admin: "+ message_details[1]+"\n"+"%";
                        j++;
                    }
                }
                br.close();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
            unreadmessagesstring=unreadmes.split("%");
            readmessagesstring=readmes.split("%");
            unreadmessagesList.setListData(unreadmessagesstring);
            readmessagesList.setListData(readmessagesstring);
        }
        else if(source == menuButton){
            frame1.setVisible(false);
            ProviderGUI pgui = new ProviderGUI();
            pgui.startGUI(owner);
        }
        else if(source == logOutButton){
            frame1.setVisible(false);
            Home gui1 = new Home();
            gui1.startHomeGUI();
        }
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdminProfileCardGUI implements ActionListener {
    private JPanel Panel1;
    private JFrame frame1;
    private JPanel panel11;
    private JLabel usernameLabel;
    private JPanel panel12;
    private JButton reservationsButton;
    private JButton cancellationsButton;
    private JButton accommodationsButton;
    private JButton messagesButton;
    private JScrollPane JScrollPanel1;
    private JTable table1;
    private JPanel messagePanel;
    private JList unreadmessagesList;
    private JLabel unreadmesLabel;
    private JLabel readmessageLabel;
    private JList readmessagesList;
    private JLabel sendLabel;
    private JTextField sendtotextField;
    private JTextArea messagetextArea;
    private JButton sendButton;
    private JComboBox userscomboBox;
    private JButton approveallButton;
    private JButton approveUserButton;
    private JTextField approveusertextField;
    private JTextField searchtextField;
    private JComboBox searchcomboBox;
    private JButton searchButton;
    private JButton logOutButton;
    private JLabel archiveLabel;
    private JLabel usersLabel;
    private String owner;

    public void startAdminProfileCardGUI(String username) {
        owner=username;
        frame1 = new JFrame();
        frame1.setSize(1400,800);
        Panel1.setSize(1400,800);
        frame1.add(Panel1);
        usernameLabel.setText(username);
        frame1.setVisible(true);
        usernameLabel.setForeground(Color.WHITE);
        unreadmesLabel.setForeground(Color.WHITE);
        readmessageLabel.setForeground(Color.WHITE);
        sendLabel.setForeground(Color.WHITE);
        usersLabel.setForeground(Color.WHITE);
        archiveLabel.setForeground(Color.WHITE);
        reservationsButton.addActionListener(this);
        cancellationsButton.addActionListener(this);
        accommodationsButton.addActionListener(this);
        sendButton.addActionListener(this);
        messagesButton.addActionListener(this);
        userscomboBox.addActionListener(this);
        approveallButton.addActionListener(this);
        approveUserButton.addActionListener(this);
        searchButton.addActionListener(this);
        JScrollPanel1.setVisible(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messagePanel.setVisible(false);
        approveallButton.setVisible(false);
        approveUserButton.setVisible(false);
        logOutButton.addActionListener(this);
        approveusertextField.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == accommodationsButton) {
            approveallButton.setVisible(false);
            approveUserButton.setVisible(false);
            approveusertextField.setVisible(false);
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                String line="";
                while ((line = count.readLine()) != null) {
                    noOfLines++;
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
                        if (words.length > 1) { //accommodation has reservations
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            newdata[i][4] = words[4];
                            newdata[i][5] = words[5];
                            //System.out.println(words[0] + words[1] + words[2] + words[3] + words[4] + words[5]);
                            for (String x : words1) {
                                if (!(x.equals(words[0])))
                                    //System.out.println(x);
                                    newdata[i][6] = newdata[i][6] + x + " ";
                            }
                        }
                    i += 1;
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
            messagePanel.setVisible(false);
            approveallButton.setVisible(false);
            approveUserButton.setVisible(false);
            approveusertextField.setVisible(false);
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/cancellationslist.txt"));
                String line="";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                    if (words.length > 1) {
                        noOfLines++;
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
                        if (words.length > 1) { //accommodation has reservations
                            newdata[i][0]=words[0];
                            newdata[i][1]=words[1];
                            newdata[i][2]=words[2];
                            //}
                            i += 1;
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
            approveallButton.setVisible(false);
            approveUserButton.setVisible(false);
            approveusertextField.setVisible(false);
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/reservationslist.txt"));
                String line = "";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                     if (words.length > 1) {
                            noOfLines++;
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
                    if (words.length > 1) { //accommodation has reservations
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            i += 1;
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
            approveallButton.setVisible(false);
            approveUserButton.setVisible(false);
            approveusertextField.setVisible(false);
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
                    if(message_details[2].equals("false")){
                        //System.out.printf("New unread message: " + "\n" + message_details[1] + "\n");
                        unreadmes+="You sent: "+ message_details[1] + " to: " + message_details[0] + "\n"+"%";
                        i++;
                    }
                    else if(message_details[2].equals("true")){
                        //System.out.printf("Already read message: " + "\n" + message_details[1] + "\n");
                        readmes+="You sent: "+ message_details[1] + " to: " + message_details[0] + "\n"+"%";
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
            //messagesList.setModel(new DefaultTableModel(week));
        }
        else if(source == sendButton){
            approveallButton.setVisible(false);
            approveUserButton.setVisible(false);
            approveusertextField.setVisible(false);
            String username = sendtotextField.getText();
            String message = messagetextArea.getText();
            if(Message.sendmessage(username,message).equals("nf")){
                JOptionPane.showMessageDialog(null, "This user does not exist!Try again!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Message sent!");
            }
            messagesButton.doClick();
        }
        else if(source == userscomboBox){
            JScrollPanel1.setVisible(true);
            try {
                if(((JComboBox)e.getSource()).getSelectedItem().equals("Approved")) {
                    approveallButton.setVisible(false);
                    approveUserButton.setVisible(false);
                    approveusertextField.setVisible(false);
                    int noOfLines= 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    String line = "";
                    while ((line = count.readLine()) != null) {
                        String words[] = line.split("%");
                        if (words[7].equals("true")&&!words[4].equals("admin")) {
                            noOfLines++;
                        }
                    }
                    String[][] newdata = new String[noOfLines][6];
                    for (int j = 0; j < noOfLines; j++) {
                        for (int k = 0; k < 6; k++) {
                            newdata[j][k] = "";
                        }
                    }
                    BufferedReader br = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    //String line;
                    noOfLines = 0;
                    while ((line = br.readLine()) != null) {
                        String[] words = line.split("%");
                        if (!words[4].equals("admin")) {
                            if (words[7].equals("true")) {
                                newdata[noOfLines][0] = words[4];
                                newdata[noOfLines][1] = words[5];
                                newdata[noOfLines][2] = words[0] + " " + words[1];
                                newdata[noOfLines][3] = words[2];
                                newdata[noOfLines][4] = words[3];
                                newdata[noOfLines][5] = words[6];
                                noOfLines++;
                            }
                        }
                    }
                    String columns[] = {"Username", "Password", "Full Name", "Phone Number", "Mail", "Type"};
                    table1.setModel(new DefaultTableModel(newdata, columns));
                    table1.setAutoCreateRowSorter(true);
                    table1.setGridColor(Color.black);
                    br.close();
                }
                else if(((JComboBox)e.getSource()).getSelectedItem().equals("Not Approved")) {
                        approveallButton.setVisible(true);
                        approveUserButton.setVisible(true);
                        approveusertextField.setVisible(true);
                        int noOfLines= 0;
                        BufferedReader count = new BufferedReader(
                                new FileReader("src/files/usersdatabase.txt"));
                        String line = "";
                        while ((line = count.readLine()) != null) {
                            String words[] = line.split("%");
                            if (words[7].equals("false")&&!words[4].equals("admin")) {
                                noOfLines++;
                            }
                        }
                        String[][] newdata = new String[noOfLines][6];
                        for (int j = 0; j < noOfLines; j++) {
                            for (int k = 0; k < 6; k++) {
                                newdata[j][k] = "";
                            }
                        }
                        BufferedReader br = new BufferedReader(
                                new FileReader("src/files/usersdatabase.txt"));
                        //String line;
                        noOfLines = 0;
                        while ((line = br.readLine()) != null) {
                            String[] words = line.split("%");
                            if (!words[4].equals("admin")) {
                                if (words[7].equals("false")) {
                                    newdata[noOfLines][0] = words[4];
                                    newdata[noOfLines][1] = words[5];
                                    newdata[noOfLines][2] = words[0] + " " + words[1];
                                    newdata[noOfLines][3] = words[2];
                                    newdata[noOfLines][4] = words[3];
                                    newdata[noOfLines][5] = words[6];
                                    noOfLines++;
                                    //System.out.println("Username: " + words[4] + " Password: " + words[5] + " Full Name: " + words[0] + " " + words[1] + " Phone Number: " + words[2] + " Mail: " + words[3] + " Type of user: " + words[6]);
                                }
                            }
                        }
                        String columns[] = {"Username", "Password", "Full Name", "Phone Number", "Mail", "Type"};
                        table1.setModel(new DefaultTableModel(newdata, columns));
                        table1.setAutoCreateRowSorter(true);
                        table1.setGridColor(Color.black);
                        br.close();
                }
                else{
                    approveallButton.setVisible(false);
                    approveUserButton.setVisible(false);
                    approveusertextField.setVisible(false);
                    int noOfLines= 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    String line = "";
                    while ((line = count.readLine()) != null) {
                        String words[] = line.split("%");
                        if(!words[4].equals("admin"))
                           noOfLines++;
                    }
                    String[][] newdata = new String[noOfLines][6];
                    for (int j = 0; j < noOfLines; j++) {
                        for (int k = 0; k < 6; k++) {
                            newdata[j][k] = "";
                        }
                    }
                    BufferedReader br = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    //String line;
                    noOfLines = 0;
                    while ((line = br.readLine()) != null) {
                        String[] words = line.split("%");
                        if (!words[4].equals("admin")) {
                                newdata[noOfLines][0] = words[4];
                                newdata[noOfLines][1] = words[5];
                                newdata[noOfLines][2] = words[0] + " " + words[1];
                                newdata[noOfLines][3] = words[2];
                                newdata[noOfLines][4] = words[3];
                                newdata[noOfLines][5] = words[6];
                                noOfLines++;
                                //System.out.println("Username: " + words[4] + " Password: " + words[5] + " Full Name: " + words[0] + " " + words[1] + " Phone Number: " + words[2] + " Mail: " + words[3] + " Type of user: " + words[6]);
                        }
                    }
                    String columns[] = {"Username", "Password", "Full Name", "Phone Number", "Mail", "Type"};
                    table1.setModel(new DefaultTableModel(newdata, columns));
                    table1.setAutoCreateRowSorter(true);
                    table1.setGridColor(Color.black);
                    br.close();
                }
            }
            catch (IOException e2){
                e2.printStackTrace();
            }
        }
        else if(source == approveallButton){
            User.approveAllUsers();
            JOptionPane.showMessageDialog(null, "All Users Approved");
        }
        else if(source == approveUserButton){
            String username = approveusertextField.getText();
            if(User.usernameExists(username) && !User.approved(username)){
                User.approveUser(username);
                JOptionPane.showMessageDialog(null, "User Approved");
            }
            else{
                JOptionPane.showMessageDialog(null, "User does not exist or is already approved");
            }
        }
        else if(source == searchButton){
            JScrollPanel1.setVisible(true);
            String searchkey = searchtextField.getText();
            String searchcategory = (String) searchcomboBox.getSelectedItem();
            if(searchcategory.equals("Accommodations")){
                try {
                    int noOfLines = 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/accommodationsdatabase.txt"));
                    BufferedReader count1 = new BufferedReader(
                            new FileReader("src/files/facilities.txt"));
                    String line="";
                    String line1="";
                    while ((line = count.readLine()) != null) {
                        line1=count1.readLine();
                        if(line.contains(searchkey)||line1.contains(searchkey))
                            noOfLines++;
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
                    int i = 0;
                    while ((line = br.readLine()) != null) {
                        String words[] = line.split("%");
                        line1 = br1.readLine();
                        String words1[] = line1.split("%");
                        if (line.contains(searchkey)||line1.contains(searchkey)) { //accommodation has reservations
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            newdata[i][4] = words[4];
                            newdata[i][5] = words[5];
                            //System.out.println(words[0] + words[1] + words[2] + words[3] + words[4] + words[5]);
                            for (String x : words1) {
                                if (!(x.equals(words[0])))
                                    newdata[i][6] = newdata[i][6] + x + " ";
                            }
                            i += 1;
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
            else if(searchcategory.equals("Users")){
                try{
                    int noOfLines= 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    String line = "";
                    while ((line = count.readLine()) != null) {
                        String words[] = line.split("%");
                        if(!words[4].equals("admin")&&line.contains(searchkey))
                            noOfLines++;
                    }
                    String[][] newdata = new String[noOfLines][6];
                    for (int j = 0; j < noOfLines; j++) {
                        for (int k = 0; k < 6; k++) {
                            newdata[j][k] = "";
                        }
                    }
                    BufferedReader br = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    //String line;
                    noOfLines = 0;
                    while ((line = br.readLine()) != null) {
                        String[] words = line.split("%");
                        if (!words[4].equals("admin")&&line.contains(searchkey)) {
                            newdata[noOfLines][0] = words[4];
                            newdata[noOfLines][1] = words[5];
                            newdata[noOfLines][2] = words[0] + " " + words[1];
                            newdata[noOfLines][3] = words[2];
                            newdata[noOfLines][4] = words[3];
                            newdata[noOfLines][5] = words[6];
                            noOfLines++;
                            //System.out.println("Username: " + words[4] + " Password: " + words[5] + " Full Name: " + words[0] + " " + words[1] + " Phone Number: " + words[2] + " Mail: " + words[3] + " Type of user: " + words[6]);
                        }
                    }
                    String columns[] = {"Username", "Password", "Full Name", "Phone Number", "Mail", "Type"};
                    table1.setModel(new DefaultTableModel(newdata, columns));
                    table1.setAutoCreateRowSorter(true);
                    table1.setGridColor(Color.black);
                    br.close();
                }
                catch (IOException e2){
                   e2.printStackTrace();
                }
              }
              else if(searchcategory.equals("Reservations")){
                try{
                    int noOfLines = 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/reservationslist.txt"));
                    String line = "";
                    while ((line = count.readLine()) != null) {
                        String words[] = line.split("%");
                        if (words.length > 1 && line.contains(searchkey)) {
                            noOfLines++;
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
                        if (words.length > 1 && line.contains(searchkey)) { //accommodation has reservations
                            newdata[i][0] = words[0];
                            newdata[i][1] = words[1];
                            newdata[i][2] = words[2];
                            newdata[i][3] = words[3];
                            i += 1;
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
            else{
                try {
                    int noOfLines = 0;
                    BufferedReader count = new BufferedReader(
                            new FileReader("src/files/cancellationslist.txt"));
                    String line="";
                    while ((line = count.readLine()) != null) {
                        String words[] = line.split("%");
                        if (words.length > 1 && line.contains(searchkey)) {
                            noOfLines++;
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
                        if (words.length > 1 && line.contains(searchkey)) { //accommodation has reservations
                            newdata[i][0]=words[0];
                            newdata[i][1]=words[1];
                            newdata[i][2]=words[2];
                            //}
                            i += 1;
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
        }
        else if(source == logOutButton){
            frame1.setVisible(false);
            Home gui1 = new Home();
            gui1.startHomeGUI();
        }
    }
}

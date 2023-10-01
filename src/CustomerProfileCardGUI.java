import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

public class CustomerProfileCardGUI implements ActionListener {
    private JPanel Panel1;
    private JFrame frame1;
    private JPanel panel11;
    private JLabel usernameLabel;
    private JPanel panel12;
    private JButton reservationsButton;
    private JButton accommodationsButton;
    private JButton messagesButton;
    private JButton logOutButton;
    private JScrollPane JScrollPanel1;
    private JTable table1;
    private JPanel messagePanel;
    private JList unreadmessagesList;
    private JLabel unreadmesLabel;
    private JLabel readmessageLabel;
    private JList readmessagesList;
    private JTextField searchtextField;
    private JButton searchButton;
    private JButton manageButton;
    private JButton removeReservationButton;
    private JFormattedTextField removeformattedTextField;
    private JLabel removeLabel;
    private JButton filtersButton;
    private JSpinner spinner1;
    private JList facilitiesList;
    private JComboBox areacomboBox;
    private JComboBox typecomboBox;
    private JLabel maxLabel;
    private JPanel searchfiltersPanel;
    private JCheckBox priceCheckBox;
    private JCheckBox facilitiesCheckBox;
    private JCheckBox areaCheckBox;
    private JCheckBox typeCheckBox;
    private JLabel accommodationnameLabel;
    private JTextField accnametextField;
    private JPanel reservePanel;
    private JTextField accNametextField;
    private JLabel accNameLabel;
    private JLabel checkINLabel;
    private JFormattedTextField chekINtextfield;
    private JLabel checkoutLabel;
    private JFormattedTextField checkOUTtextfield;
    private JFormattedTextField checkOuttextfield;
    private JLabel checkOutLabel;
    private JButton doneButton;
    private String owner;

    public void startCustomerProfileCardGUI(String username) {
        frame1 = new JFrame();
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel background;
        ImageIcon img = new ImageIcon("background.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,1400,1400);
        frame1.add(background);
        frame1.setVisible(true);
        owner = username;
        usernameLabel.setText(username);
        frame1.setSize(1400, 800);
        Panel1.setSize(1400, 800);
        frame1.add(Panel1);
        frame1.setVisible(true);
        messagesButton.addActionListener(this);
        accommodationsButton.addActionListener(this);
        reservationsButton.addActionListener(this);
        manageButton.addActionListener(this);
        logOutButton.addActionListener(this);
        removeReservationButton.addActionListener(this);
        searchButton.addActionListener(this);
        filtersButton.addActionListener(this);
        messagePanel.setVisible(false);
        removeformattedTextField.setVisible(false);
        removeLabel.setVisible(false);
        removeReservationButton.setVisible(false);
        accnametextField.setVisible(false);
        accommodationnameLabel.setVisible(false);
        searchfiltersPanel.setVisible(false);
        priceCheckBox.addActionListener(this);
        areaCheckBox.addActionListener(this);
        areacomboBox.addActionListener(this);
        typeCheckBox.addActionListener(this);
        typecomboBox.addActionListener(this);
        areacomboBox.addActionListener(this);
        doneButton.addActionListener(this);
        doneButton.setVisible(false);
        reservePanel.setVisible(false);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                searchButton.doClick();
            }
        };
        facilitiesList.addListSelectionListener(lsl);
        facilitiesCheckBox.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == messagesButton) {
            reservePanel.setVisible(false);
            searchfiltersPanel.setVisible(false);
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            removeReservationButton.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            doneButton.setVisible(false);
            messagePanel.setVisible(true);
            manageButton.setVisible(true);
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
        else if(source == accommodationsButton){
            searchfiltersPanel.setVisible(false);
            reservePanel.setVisible(false);
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            removeReservationButton.setVisible(false);
            doneButton.setVisible(false);
            manageButton.setVisible(true);
            try {
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                String line="";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
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
                String line1;
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
                            i += 1;
                        }
                }
                String columns[] = {"Name", "Type", "ID", "Description", "Area", "Price", "Facilities"};
                table1.setModel(new DefaultTableModel(newdata,columns));
                table1.setAutoCreateRowSorter(true);
                table1.setGridColor(Color.black);
                table1.setRowSelectionAllowed(true);
                br.close();
                br1.close();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }
        }
        else if(source == reservationsButton){
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            searchfiltersPanel.setVisible(false);
            removeReservationButton.setVisible(false);
            accommodationsButton.doClick();
            doneButton.setVisible(true);
            reservePanel.setVisible(true);
        }
        else if(source == doneButton){
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            searchfiltersPanel.setVisible(false);
            removeReservationButton.setVisible(false);
            String accname=accNametextField.getText();
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date checkIn=new Date();
            Date checkOut=new Date();
            try {
                checkIn = format.parse(chekINtextfield.getText());
                checkOut = format.parse(checkOUTtextfield.getText());
            }
            catch(ParseException pe){
                JOptionPane.showMessageDialog(null, "Date format should be dd-mm-yyyy");
            }
            String message=Reservation.newReservation(owner,accname,checkIn,checkOut);
            if(message.equals("cbc")){
                JOptionPane.showMessageDialog(null, "Check-Out date should be at least one day after Check-In date! Try again for another period ");
            }
            else if(message.equals("res")){
                JOptionPane.showMessageDialog(null, "Sorry, accommodation is reserved during that period! Try again for another period!");
            }
            else if(message.equals("sr")){
                JOptionPane.showMessageDialog(null, "Successful Reservation!");
            }
        }
        else if(source == manageButton){
            DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
            removeformattedTextField.setVisible(true);
            removeLabel.setVisible(true);
            manageButton.setVisible(true);
            removeReservationButton.setVisible(true);
            accnametextField.setVisible(true);
            accommodationnameLabel.setVisible(true);
            doneButton.setVisible(false);
            reservePanel.setVisible(false);
            try{
                int noOfLines = 0;
                BufferedReader count = new BufferedReader(
                        new FileReader("src/files/reservationslist.txt"));
                String line = "";
                while ((line = count.readLine()) != null) {
                    String words[] = line.split("%");
                    if (words.length > 1 && words[3].equals(owner)) {
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
                    if (words.length > 1  && words[3].equals(owner)) { //accommodation has reservations
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
        else if(source == removeReservationButton){
            manageButton.setVisible(true);
            reservePanel.setVisible(false);
            doneButton.setVisible(false);
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date checkIn=new Date();
            try {
                checkIn = format.parse(removeformattedTextField.getText());
            }
            catch(ParseException pe){
                JOptionPane.showMessageDialog(null, "Date format should be dd-mm-yyyy");
            }
            String accname = accnametextField.getText();
            if(Reservation.removeReservation(owner,accname,checkIn).equals("sc")){
                JOptionPane.showMessageDialog(null, "Successful cancellation!");
                manageButton.doClick();
            }
            else{
                JOptionPane.showMessageDialog(null, "No reservation by you found on that day or accommodation! Try again");
            }
        }
        else if(source == searchButton){
            int checkboxeschecked=0;
            doneButton.setVisible(false);
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            removeReservationButton.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            manageButton.setVisible(true);
            reservePanel.setVisible(false);
            String searchkey = searchtextField.getText();
            if(priceCheckBox.isSelected()||areaCheckBox.isSelected()||facilitiesCheckBox.isSelected()||typeCheckBox.isSelected()){
                int noofLines=0;
                try {
                    BufferedReader br = new BufferedReader(
                            new FileReader("src/files/accommodationsdatabase.txt"));
                    while(br.readLine()!= null){
                          noofLines+=1;
                    }
                    br.close();
                }
                catch (IOException e1){
                    e1.printStackTrace();
                }
                HashSet<String> accommodationsArea = new HashSet<>();
                HashSet<String> accommodationsPrice = new HashSet<>();
                HashSet<String> accommodationsFac = new HashSet<>();
                HashSet<String> accommodationsType = new HashSet<>();
                if(areaCheckBox.isSelected()){
                   checkboxeschecked+=1;
                   //System.out.println(String.valueOf(areacomboBox.getSelectedItem()));
                   String areas[] ={};
                   areas = Accommodation.accommodationsInThisArea(String.valueOf(areacomboBox.getSelectedItem()));
                   for(int i=0; i<areas.length;i++){
                       accommodationsArea.add(areas[i]);
                   }
                }
                else{
                    accommodationsArea.add("-1");
                }
                if(typeCheckBox.isSelected()){
                    checkboxeschecked+=1;
                    String[] types = new String[noofLines];
                    try {
                        BufferedReader br = new BufferedReader(
                                new FileReader("src/files/accommodationsdatabase.txt"));
                        String line;
                        int cnt = 0;
                        while((line = br.readLine()) != null){
                            String[] words = line.split("%");
                            if(words[1].equals(String.valueOf(typecomboBox.getSelectedItem()))){
                                //System.out.println("Found accommodation: " + words[0]);
                                types[cnt] = words[0];
                                cnt++;
                            }
                        }
                        br.close();
                    }
                    catch (IOException e1){
                        e1.printStackTrace();
                    }
                    for(int i=0; i<types.length;i++){
                        accommodationsType.add(types[i]);
                    }
                }
                else{
                    accommodationsType.add("-1");
                }
                if(facilitiesCheckBox.isSelected()){
                    checkboxeschecked+=1;
                    String[] facacc = new String[noofLines];
                    java.util.List<String> facilities = new ArrayList<>();
                    facilities = facilitiesList.getSelectedValuesList();
                    ArrayList<String> facilitieswanted = new ArrayList<>();
                    for(String x:facilities){
                        facilitieswanted.add(x);
                    }
                    try {
                        BufferedReader br = new BufferedReader(
                                new FileReader("src/files/accommodationsdatabase.txt"));
                        String line;
                        int cnt = 0;
                        while((line = br.readLine()) != null){
                            String[] words = line.split("%");
                            if(Accommodation.accommodHasAllTheseFacilities(words[0], facilitieswanted)){
                                //System.out.println("Found accommodation: " + words[0]);
                                facacc[cnt] = words[0];
                                cnt++;
                            }
                        }
                        br.close();
                    }
                    catch (IOException e1){
                        e1.printStackTrace();
                    }
                    for(int i=0; i<facacc.length;i++){
                        accommodationsFac.add(facacc[i]);
                    }

                }
                else{
                    accommodationsFac.add("-1");
                }
                if(priceCheckBox.isSelected()){
                    checkboxeschecked+=1;
                    int cnt = 0;
                    String[] priacc = new String[noofLines];
                    try {
                        BufferedReader br = new BufferedReader(
                                new FileReader("src/files/accommodationsdatabase.txt"));
                        String line;
                        while((line = br.readLine()) != null){
                            String[] words = line.split("%");
                                int price = Integer.parseInt(words[5]);
                                if(price<=Integer.parseInt((spinner1.getValue() + ""))){
                                    //System.out.println("Found accommodation: " + words[0]);
                                    priacc[cnt]=words[0];
                                    cnt++;
                                }
                        }
                        br.close();
                    }
                    catch (IOException e2){
                        e2.printStackTrace();
                    }
                    for(int i=0; i<priacc.length;i++){
                        accommodationsPrice.add(priacc[i]);
                    }
                }
                else{
                    accommodationsPrice.add("-1");
                }
                JScrollPanel1.setVisible(true);
                int noOfLines = 0;

                String[][] data = new String[noofLines][7];
                for(int j=0;j<noofLines;j++){
                    for(int k=0;k<7;k++){
                        data[j][k]="";
                    }
                }
                try {
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                BufferedReader br1 = new BufferedReader(
                        new FileReader("src/files/facilities.txt"));
                String line,line1;
                int i = 0;
                while ((line = br.readLine()) != null) {
                    int found=0;
                    String words[] = line.split("%");
                    line1 = br1.readLine();
                    String words1[] = line1.split("%");
                    if (words.length > 1) { //accommodation has reservations
                        if(accommodationsArea.contains(words[0])) {
                            found += 1;
                            /*System.out.println("Area ok");
                            System.out.println(found);
                            System.out.println(checkboxeschecked);*/
                        }
                        if(accommodationsPrice.contains(words[0])) {
                            found += 1;
                            /*System.out.println("Price ok");
                            System.out.println(found);
                            System.out.println(checkboxeschecked);*/
                        }
                        if(accommodationsType.contains(words[0])) {
                            found += 1;
                            /*System.out.println("Type ok");
                            System.out.println(found);
                            System.out.println(checkboxeschecked);*/
                        }
                        if(accommodationsFac.contains(words[0])) {
                            found += 1;
                            /*System.out.println("Facilities ok");
                            System.out.println(found);
                            System.out.println(checkboxeschecked);*/
                        }
                        if(found==checkboxeschecked) {
                            data[i][0] = words[0];
                            data[i][1] = words[1];
                            data[i][2] = words[2];
                            data[i][3] = words[3];
                            data[i][4] = words[4];
                            data[i][5] = words[5];
                            //System.out.println(words[0] + words[1] + words[2] + words[3] + words[4] + words[5]);
                            for (String x : words1) {
                                if (!(x.equals(words[0]))) {
                                    //System.out.println(x);
                                    data[i][6] = data[i][6] + x + " ";
                                }
                            }
                            i += 1;
                        }
                    }
                }
                int datasize = i;
                String[][] newdata = new String[datasize][7];
                for(int j=0;j<datasize;j++){
                    for(int k=0;k<7;k++){
                        newdata[j][k]=data[j][k];
                    }
                }

                String columns[] = {"Name", "Type", "ID", "Description", "Area", "Price", "Facilities"};
                table1.setModel(new DefaultTableModel(newdata,columns));
                table1.setAutoCreateRowSorter(true);
                table1.setGridColor(Color.black);
                br.close();
                br1.close();
            }
            catch (IOException e3){
                e3.printStackTrace();
            }


            }
            else{
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
                                if (!(x.equals(words[0]))&&x!=null)
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
        }
        else if(source==filtersButton){
            doneButton.setVisible(false);
            removeformattedTextField.setVisible(false);
            removeLabel.setVisible(false);
            removeReservationButton.setVisible(false);
            accnametextField.setVisible(false);
            accommodationnameLabel.setVisible(false);
            searchfiltersPanel.setVisible(true);
            areacomboBox.setEditable(false);
            typecomboBox.setEditable(false);
            manageButton.setVisible(true);
            searchButton.doClick();
            reservePanel.setVisible(false);
            try {
                String line="";
                HashSet<String> facilities = new HashSet<String>();
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/facilities.txt"));
                int i = 0;
                while ((line = br.readLine()) != null) {
                    String words[] = line.split("%");
                    for (String x : words) {
                        if (!(x.equals(words[0])))
                            facilities.add(x);
                    }
                }
                String data[] = new String[facilities.size()];
                for(String facility:facilities){
                    data[i] = facility;
                    i+=1;
                }
                br.close();
                facilitiesList.setListData(data);
                HashSet<String> areas = new HashSet<String>();
                HashSet<String> types = new HashSet<String>();
                BufferedReader br1 = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                i = 0;
                while ((line = br1.readLine()) != null) {
                    String words[] = line.split("%");
                    areas.add(words[4]);
                    types.add(words[1]);
                }
                String dataareas[] = new String[areas.size()];
                String datatypes[] = new String[types.size()];
                for(String area:areas){
                    dataareas[i] = area;
                    i+=1;
                }
                i=0;
                for(String type:types){
                    datatypes[i] = type;
                    i+=1;
                }
                facilitiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                areacomboBox.setModel(new DefaultComboBoxModel(dataareas));
                typecomboBox.setModel(new DefaultComboBoxModel(datatypes));
                //comboBox1.setModel(dataareas);
                br.close();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }


        }
        else if(source == logOutButton){
            frame1.setVisible(false);
            Home gui1 = new Home();
            gui1.startHomeGUI();
        }
        else if(source == areaCheckBox){
            searchButton.doClick();
        }
        else if(source == priceCheckBox){
            searchButton.doClick();
        }
        else if(source == facilitiesCheckBox){
            searchButton.doClick();
        }
        else if(source == typeCheckBox){
            searchButton.doClick();
        }
        else if(source == typecomboBox){
            searchButton.doClick();
        }
        else if(source == areacomboBox){
            searchButton.doClick();
        }
    }
}

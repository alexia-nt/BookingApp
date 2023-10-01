import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProviderGUI implements ActionListener{
    private JTextField NametextField;
    private JTextField AreatextField;
    private JTextField IDtextField;
    private JTextField TypetextField;
    private JTextField PricetextField;
    private JTextField FacilitiestextField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JLabel accnameLabel;
    private JLabel IDLabel;
    private JLabel AccommodationAreaLabel;
    private JLabel AccommodationTypeLabel;
    private JLabel AccommodationDescriptionLabel;
    private JFormattedTextField DescriptionformattedTextField;
    private JLabel priceLabel;
    private JLabel facilitiesLabel;
    private JPanel Panel1;
    private JPanel panel11;
    private JPanel panel12;
    private JPanel panel13;
    private JLabel deleteaccLabel;
    private JTextField deletetextField;
    private JButton removeButton;
    private JButton backButton;
    private JButton finalizeButton;
    private JPanel panel15;
    private JButton editbackButton;
    private JButton profileButton;
    private JTextPane instructionsTextPane;
    private JButton logoutbutton;
    private JPanel panel14;
    private JTable Accommodations;
    private JFrame frame;
    private String owner;


    public void startGUI(String username){
        frame = new JFrame();
        owner = username;
        frame.setSize(1400,800);
        //frame.setLocationRelativeTo(null);
        frame.add(Panel1);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel13.setVisible(false);
        try {
            int noOfLines=0;
            BufferedReader count = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            while (count.readLine() != null) {
                noOfLines++;
            }
            //System.out.println(noOfLines);
            String [][] newdata= new String[noOfLines][7];
            for(int j=0;j<noOfLines;j++){
                for(int k=0;k<7;k++){
                    newdata[j][k]="";
                }
            }

            BufferedReader br = new BufferedReader(
                    new FileReader("src/files/accommodationsdatabase.txt"));
            BufferedReader br1 = new BufferedReader(
                    new FileReader("src/files/facilities.txt"));
            String line,line1;
            int i=0;
            while((line = br.readLine()) != null){
                String words[] = line.split("%");
                line1=br1.readLine();
                String words1[] = line1.split("%");
                if(username.equals(Menu.getProviderOfThisAccommod(words[0]))) {
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
                    i+=1;
                }
            }
            addButton.addActionListener(this);
            deleteButton.addActionListener(this);
            backButton.addActionListener(this);
            removeButton.addActionListener(this);
            editButton.addActionListener(this);
            finalizeButton.addActionListener(this);
            profileButton.addActionListener(this);
            logoutbutton.addActionListener(this);
            String columns[]={"Accommodation Name","Accommodation Type","Accommodation ID","Accommodation Description","Accommodation Area","Accommodation Price/Per Night","Accommodation Facilities"};
            Accommodations = new JTable(newdata,columns);
            Accommodations.setBounds(0,200,200,200);
            panel14 = new JPanel(new BorderLayout());
            panel14.setBounds(0,200,200,200);
            //panel14.add(Accommodations,BorderLayout.SOUTH);
            frame.add(panel14);
            instructionsTextPane.setEditable(false);
            br.close();
            br1.close();
            editbackButton.addActionListener(this);
            editbackButton.setVisible(false);
            finalizeButton.setVisible(false);
        }
        catch (FileNotFoundException p){
            p.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == addButton) {
            editbackButton.setVisible(false);
            finalizeButton.setVisible(false);
            String name = NametextField.getText();
            String type = TypetextField.getText();
            String ID = IDtextField.getText();
            String description = DescriptionformattedTextField.getText();
            String area = AreatextField.getText();
            String price = PricetextField.getText();
            String[] facilities = FacilitiestextField.getText().split(" ");
            if(name.equals("")||type.equals("")||ID.equals("")||description.equals("")||area.equals("")||price.equals("")||facilities.length==0){
                JOptionPane.showMessageDialog(null, "You should fill all the fields");
            }
            else {
                Accommodation.addAccommodation(this.owner, name, type, ID, description, area, price, facilities);
            }
        }
        else if(source == deleteButton){
            editbackButton.setVisible(false);
            finalizeButton.setVisible(false);
            panel11.setVisible(false);
            panel14.setVisible(false);
            panel13.setVisible(true);
        }
        else if(source == backButton){
            panel11.setVisible(true);
            panel14.setVisible(true);
            panel13.setVisible(false);
        }
        else if(source == removeButton){
            editbackButton.setVisible(false);
            finalizeButton.setVisible(false);
            String deletekey = deletetextField.getText();
            //System.out.println(deletekey);
            if(Accommodation.removeaccomodation(this.owner,deletekey).equals("ok")){
                JOptionPane.showMessageDialog(null, "You successfully deleted " + deletekey + " ! Delete more or click Back");
            }
            else{
                JOptionPane.showMessageDialog(null,  deletekey + " does not exist or does not belong to you!Try again or click Back");
            }

        }
        else if(source == editButton){
           String editkey = NametextField.getText();
           String facilities=" ";
           editbackButton.setVisible(true);
           finalizeButton.setVisible(true);
           //NametextField.setText(editkey);
            try {
                BufferedReader br = new BufferedReader(
                        new FileReader("src/files/accommodationsdatabase.txt"));
                BufferedReader br1 = new BufferedReader(
                        new FileReader("src/files/facilities.txt"));
                String line,line1;
                while((line = br.readLine()) != null){
                    String words[] = line.split("%");
                    line1=br1.readLine();
                    String words1[] = line1.split("%");
                    if(owner.equals(Menu.getProviderOfThisAccommod(words[0]))&&editkey.equals(words[0])) {
                            TypetextField.setText(words[1]);
                            IDtextField.setText(words[2]);
                            DescriptionformattedTextField.setText(words[3]);
                            AreatextField.setText(words[4]);
                            PricetextField.setText(words[5]);
                            for (String x : words1) {
                                if (!(x.equals(words[0]))){
                                    //System.out.println(x);
                                   facilities+=x+" ";
                                }
                            }
                            FacilitiestextField.setText(facilities);
                    }
                }
                br.close();
                br1.close();
            }
            catch (IOException e1){
                e1.printStackTrace();
            }

        }
        else if(source == finalizeButton){
            String name = NametextField.getText();
            String type = TypetextField.getText();
            String ID = IDtextField.getText();
            String description = DescriptionformattedTextField.getText();
            String area = AreatextField.getText();
            String price = PricetextField.getText();
            String[] facilities = FacilitiestextField.getText().split(" ");
            for(int i=0;i< facilities.length;i++){
                if(facilities[i].equals(" ")){
                    facilities[i]="%";
                }
            }
            if(Accommodation.editAccommodation(this.owner, name, type, ID, description, area, price, facilities).equals("ok")){
                JOptionPane.showMessageDialog(null, "You successfully edited " + name + " ! Edit it more or click Back");
            }
            else{
                JOptionPane.showMessageDialog(null, "You cannot edit  " + name + " ! It either does not exist or does not belong to you.");
            }
        }
        else if(source == editbackButton){
            editbackButton.setVisible(false);
            finalizeButton.setVisible(false);
        }
        else if(source == profileButton){
            frame.setVisible(false);
            ProviderProfileCardGUI ppcgui = new ProviderProfileCardGUI();
            ppcgui.startProfileCardGUI(owner);
        }
        else if(source == logoutbutton){
            frame.setVisible(false);
            Home gui1 = new Home();
            gui1.startHomeGUI();
        }
    }
}

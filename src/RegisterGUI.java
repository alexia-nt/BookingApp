import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterGUI implements ActionListener {
    private JButton Registerbutton;
    private JTextField usernametextField;
    private JPasswordField passwordField1;
    private JButton homeButton;
    private JTextField FirstNametextField;
    private JTextField LastNametextField;
    private JTextField MailtextField;
    private JComboBox TypecomboBox;
    private JTextField PhonetextField;
    private JPanel Panel1;
    private JPanel panel12;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel fnLabel;
    private JLabel lnLabel;
    private JLabel mailLabel;
    private JLabel phoneLabel;
    private JLabel typeLabel;
    private JFrame frame;
    private JLabel typeBoxchoiceLabel;

    public void startRegisterGUI(){
        frame = new JFrame();
        frame.setSize(1400,800);
        Panel1.setSize(1400,800);
        panel12.setSize(1400,600);
        frame.add(Panel1);
        typeBoxchoiceLabel = new JLabel("Provider");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userLabel.setForeground(Color.WHITE);
        passLabel.setForeground(Color.WHITE);
        fnLabel.setForeground(Color.WHITE);
        lnLabel.setForeground(Color.WHITE);
        mailLabel.setForeground(Color.WHITE);
        phoneLabel.setForeground(Color.WHITE);
        typeLabel.setForeground(Color.WHITE);
        String s1[] = { "Provider", "Customer" };
        TypecomboBox.addActionListener(this);
        Registerbutton.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == Registerbutton) {
            String username = usernametextField.getText();
            String password = passwordField1.getText();
            String firstname = FirstNametextField.getText();
            String lastname = LastNametextField.getText();
            String mail = MailtextField.getText();
            String phone = PhonetextField.getText();
            String type = typeBoxchoiceLabel.getText();
            if(username.equals("")||password.equals("")||firstname.equals("")||lastname.equals("")||mail.equals("")||phone.equals("")){
                JOptionPane.showMessageDialog(null, "You should fill all the fields");
            }
            else if(Register.doRegistration(username,password,firstname,lastname,phone,mail,type).equals("reg")) {
                JOptionPane.showMessageDialog(null, "You just got successfully registered! Wait till your account gets activated");
                frame.setVisible(false);
                homeButton.doClick();
            }
            else if(Register.doRegistration(username,password,firstname,lastname,phone,mail,type).equals("ut")) {
                JOptionPane.showMessageDialog(null, "Username taken. Try again with another username");
            }
            else if(Register.doRegistration(username,password,firstname,lastname,phone,mail,type).equals("pw2")) {
                JOptionPane.showMessageDialog(null, "Password should be longer than 2 characters. Try again with another password");
            }
        }
        else if (source == homeButton) {
            Home gui1 = new Home();
            gui1.startHomeGUI();
            frame.dispose();
        }
        else if (source == TypecomboBox) {
            typeBoxchoiceLabel.setText(((JComboBox)e.getSource()).getSelectedItem()+"");
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoginGUI implements ActionListener{
    private JButton Loginbutton;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton homeButton;
    private JPanel Panel1;
    private JLabel usernameLabel;
    private JLabel PasswordLabel;
    private JCheckBox showPasswordCheckBox;
    private JFrame frame;

    public void startLoginGUI(){
        passwordText.setEchoChar('*');
        frame = new JFrame();
        frame.setSize(1400,800);
        Panel1.setSize(1400,800);
        usernameLabel.setForeground(Color.WHITE);
        PasswordLabel.setForeground(Color.WHITE);
        frame.add(Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Loginbutton.addActionListener(this);
        showPasswordCheckBox.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == Loginbutton) {
            String username = usernameText.getText();
            String password = passwordText.getText();
            //System.out.println(username + " " + password);
            if(username.equals("")||password.equals("")){
                JOptionPane.showMessageDialog(null, "You should fill all the fields");
            }
            else if (Login.doLogin(username, password).equals("Login!")) {
                String type = "";
                JOptionPane.showMessageDialog(null, "Welcome back to our platform!");
                try {
                    BufferedReader br = new BufferedReader(
                            new FileReader("src/files/usersdatabase.txt"));
                    String line;
                    while (((line = br.readLine()) != null)) {
                        String[] words = line.split("%");
                        if (username.equals(words[4])) {
                            type = words[6];
                        }
                    }
                    br.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                if (type.equals("Provider")) {
                    ProviderGUI pgui = new ProviderGUI();
                    pgui.startGUI(username);
                } else if (type.equals("Customer")) {
                    CustomerProfileCardGUI cpcgui = new CustomerProfileCardGUI();
                    cpcgui.startCustomerProfileCardGUI(username);
                } else {
                    AdminProfileCardGUI apcgui = new AdminProfileCardGUI();
                    apcgui.startAdminProfileCardGUI(username);
                }
                frame.dispose();
            } else if (Login.doLogin(username, password).equals("wp") || Login.doLogin(username, password).equals("ukn")) {
                JOptionPane.showMessageDialog(null, "Wrong username or password. Try again!");
            } else if (Login.doLogin(username, password).equals("na")) {
                JOptionPane.showMessageDialog(null, "Account not activated yet!");
                frame.setVisible(false);
                frame.setVisible(true);
            }


        }
        else if (source == homeButton) {
            Home gui1 = new Home();
            gui1.startHomeGUI();
            frame.dispose();
        }
        else if (source == showPasswordCheckBox) {
            if (showPasswordCheckBox.isSelected()) {
                passwordText.setEchoChar((char) 0);
            } else {
                passwordText.setEchoChar('*');
            }
        }
    }
}

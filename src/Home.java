import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Home implements ActionListener {
    private JButton Registerbutton;
    private JButton Loginbutton;
    private JPanel Panel1;
    private JFrame frame;

    public void startHomeGUI(){
        frame = new JFrame();
        frame.setSize(1400,800);
        Registerbutton.setSize(200,200);
        Loginbutton.setSize(200,200);
        frame.add(Panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Loginbutton.addActionListener(this);
        Registerbutton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Message> messages = new ArrayList<>();
        Object source = e.getSource();
        if (source == Loginbutton) {
            LoginGUI lgui = new LoginGUI();
            lgui.startLoginGUI();
            frame.setVisible(false);
        }
        else if(source == Registerbutton){
            RegisterGUI rgui = new RegisterGUI();
            rgui.startRegisterGUI();
            frame.setVisible(false);
        }
    }
}

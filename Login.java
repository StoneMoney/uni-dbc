/***
 * Authors:
 *      Farrell, Stephen
 *      Giancursio, Nick
 *      Gerber, Max
 *      Gao, Fei
 * Class: ISTE-330.02 DatabaseConnectivity and Access
 * Date: 4/26/2021
 * Professor: Habermas, Jim
 * Assignment: ISTE-330.02 Group Project Group 4
 * File: Login.java
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    private JLabel labelUsername = new JLabel("Enter username: ");
    private JLabel labelPassword = new JLabel("Enter password: ");
    private JTextField textUsername = new JTextField(20);
    private JPasswordField fieldPassword = new JPasswordField(20);
    private JButton buttonLogin = new JButton("Login");
    private JLabel labelMessage = new JLabel();


    HashMap<String,String> logininfo = new HashMap<String, String>();

    public Login(HashMap<String, String> loginInfoOriginal) {
        super("RIT Faculty Abstract Project");

        // Login hashmap
        logininfo = loginInfoOriginal;
        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelUsername, constraints);

        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelPassword, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(labelMessage, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);
        buttonLogin.addActionListener(this);

        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == buttonLogin) {
            String userName = textUsername.getText();
            String password = String.valueOf(fieldPassword.getPassword());

            // If the username provided is contained in the hashmap
            if (logininfo.containsKey(userName)) {
                // If the password is equal to the password that maps to the username provided is correct
                if (logininfo.get(userName).equals(password)) {
                    labelMessage.setForeground(Color.green);
                    labelMessage.setText("Login Successful");
                    System.out.println("Login Successful");
                    this.dispose();
                    FrontEnd frontEnd = new FrontEnd(new DataLayer(),userName);
                }
                // If Password is incorrect
                else {
                    labelMessage.setForeground(Color.red);
                    labelMessage.setText("Invalid Password");
                    System.out.println("Invalid Password");
                }
            } else {
                labelMessage.setForeground(Color.red);
                labelMessage.setText("Username not found!");
                System.out.println("Username not found!");
            }
        }
    }
   public static void launch() {
         IDandPasswords iDandPasswords = new IDandPasswords();

        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login(iDandPasswords.getLoginInfo()).setVisible(true);
            }
        });
   }
    public static void main(String[] args) {
        launch();
    }
}


import java.awt.*;

import javax.swing.*;

class FEPanel extends JPanel {
   public FEPanel(boolean editable) {
      add(new JLabel("Test!"));
   }
}

public class FrontEnd extends JFrame {
   public FrontEnd() {
      super("University Database Controller");
      JPanel mainLayoutPanel = new JPanel(new BorderLayout());
      // SIDEBAR
      JPanel databaseSelectorPanel = new JPanel();
      databaseSelectorPanel.setLayout(new BoxLayout(databaseSelectorPanel, BoxLayout.Y_AXIS));
      JButton facultyButton = new JButton("Faculty");
      JButton studentsButton = new JButton("Students");
      JButton degreesButton = new JButton("Degrees");
      JButton interestsButton = new JButton("Interests");
      databaseSelectorPanel.add(facultyButton);
      databaseSelectorPanel.add(studentsButton);
      databaseSelectorPanel.add(degreesButton);
      databaseSelectorPanel.add(interestsButton);
      mainLayoutPanel.add(databaseSelectorPanel, BorderLayout.EAST);
      // SEARCH BAR
      JPanel searchBar = new JPanel(new FlowLayout());
      JButton searchButton = new JButton("Search");
      JTextField searchBox = new JTextField(40);
      searchBar.add(searchBox);
      searchBar.add(searchButton);
      mainLayoutPanel.add(searchBar, BorderLayout.NORTH);
      // MAIN AREA
      JPanel mainArea = new JPanel(new FlowLayout());
      FEPanel samplePanel = new FEPanel(true);
      JScrollPane scrollableMainArea = new JScrollPane(mainArea);
      mainLayoutPanel.add(scrollableMainArea, BorderLayout.CENTER);
      // MENU BAR
      JMenuBar infoBar = new JMenuBar();
      JMenuItem login = new JMenuItem("Logged in as BLANK");
      login.setEnabled(false);
      JMenuItem logout = new JMenuItem("Log Out");
      infoBar.add(login);
      infoBar.add(logout);
      setJMenuBar(infoBar);
      // VISIBILITY AND FRAMING
      add(mainLayoutPanel);
      setVisible(true);
      setSize(500,600);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   public static void main(String[] args) {
      FrontEnd fe = new FrontEnd();
   }
}
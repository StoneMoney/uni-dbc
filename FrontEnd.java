/***
 * Authors:
 *      Farrell, Stephen
 *      Giancursio, Nick
 *      Gerber, Max
 *      Gao, Fei
 * Class: ISTE-330.02 DatabaseConnectivity and Access
 * Date: 4/17/2021
 * Professor: Habermas, Jim
 * Assignment: ISTE-330.02 Group Project Group 4
 * File: FrontEnd.java
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.util.ArrayList;
 
class FEPanel extends JPanel {
   public FEPanel(boolean editable) {
      add(new JLabel("Test!"));
   }
}

public class FrontEnd extends JFrame {
   DataLayer backend;
   JComboBox searchFor;
   JComboBox searchUsing;
   JTextField searchBox;
   JButton searchButton;
   JButton viewButton;
   JList resultsList;
   int[] allIds;
   String currentTable;
   public FrontEnd(DataLayer backend) {
      super("University Database Controller");
      this.backend = backend;
      backend.connect();
      JPanel mainLayoutPanel = new JPanel(new BorderLayout());
      // SIDEBAR
      JPanel databaseSelectorPanel = new JPanel();
      databaseSelectorPanel.setLayout(new BoxLayout(databaseSelectorPanel, BoxLayout.Y_AXIS));
      JButton facultyButton = new JButton("Faculty");
      JButton studentsButton = new JButton("Student");
      JButton degreesButton = new JButton("Degree");
      JButton interestsButton = new JButton("Interests");
      databaseSelectorPanel.add(facultyButton);
      databaseSelectorPanel.add(studentsButton);
      databaseSelectorPanel.add(degreesButton);
      databaseSelectorPanel.add(interestsButton);
      mainLayoutPanel.add(databaseSelectorPanel, BorderLayout.EAST);
      // SEARCH BAR
      JPanel searchBar = new JPanel(new GridLayout(1,3));
         //SEARCH SELECTOR
         JPanel searchSelectors = new JPanel(new GridLayout(1,2));
         JPanel searchForPanel = new JPanel(new BorderLayout());
         JLabel searchForLabel = new JLabel("Search for:");
         String[] searchForTerms = {"Faculty","Student","Interests","Degree","Department"};
         searchFor = new JComboBox(searchForTerms);
         searchForPanel.add(searchForLabel, BorderLayout.NORTH);
         searchForPanel.add(searchFor, BorderLayout.CENTER);
         searchSelectors.add(searchForPanel);
         JPanel searchUsingPanel = new JPanel(new BorderLayout());
         JLabel searchUsingLabel = new JLabel("Using term:");
         searchUsing = new JComboBox();
         searchUsingPanel.add(searchUsingLabel, BorderLayout.NORTH);
         searchUsingPanel.add(searchUsing, BorderLayout.CENTER);
         searchSelectors.add(searchUsingPanel);
      searchButton = new JButton("Search");
      searchBox = new JTextField(40);
      searchBar.add(searchSelectors);
      searchBar.add(searchBox);
      searchBar.add(searchButton);
      mainLayoutPanel.add(searchBar, BorderLayout.NORTH);
      // MAIN AREA
      JPanel mainArea = new JPanel(new BorderLayout());
      resultsList = new JList();
      viewButton = new JButton("View");
      viewButton.setEnabled(false);
      mainArea.add(resultsList,BorderLayout.CENTER);
      mainArea.add(viewButton,BorderLayout.SOUTH);
      JScrollPane scrollableMainArea = new JScrollPane(mainArea);
      mainLayoutPanel.add(scrollableMainArea, BorderLayout.CENTER);
      // MENU BAR
      JMenuBar infoBar = new JMenuBar();
      JMenuItem login = new JMenuItem("Logged in as "+backend.getUsername());
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
      getList("Faculty");
      //ACTIONS
      searchFor.addActionListener(new ActionListener () {
         public void actionPerformed(ActionEvent e) {
            searchUsing.removeAllItems();
            String[] usingTermsList = updateSearchUsing((String)searchFor.getSelectedItem());
            for (String s : usingTermsList) {
               searchUsing.addItem(s);
            }
         }
      });
      facultyButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getList("Faculty");
         }
      });
      studentsButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getList("Student");
         }
      });
      degreesButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getList("Degree");
         }
      });
      interestsButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            getList("Interests");
         }
      });
      viewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(currentTable.length() > 0) {
               viewInfo();
            }
         }
      });
      searchButton.addActionListener(new ActionListener () {
         public void actionPerformed(ActionEvent e) {
            search((String)searchBox.getText());  
         }
      });
   }
   
   public void clearList() {
      resultsList.clearSelection();
      resultsList.setListData(new String[0]);
      viewButton.setEnabled(false);
   }
   
   public void getList(String table) {
      ArrayList<MatchingData> matching = backend.getList(table);
      resultsList.clearSelection();
      currentTable = table;
      String[] allStrings = new String[matching.size()];
      allIds = new int[matching.size()];
      for(int i = 0; i < matching.size(); i++) {
         MatchingData md = matching.get(i);
         allStrings[i] = md.getDisplayString();
         allIds[i] = md.getId();
      }
      viewButton.setEnabled(true);
      resultsList.setListData(allStrings);
   }
   
   public void viewInfo() {
       int index = resultsList.getSelectedIndex();
       String output = backend.get(currentTable, allIds[index]);
       JTextArea jta = new JTextArea(output, 10, 50);
       JScrollPane scrollableArea = new JScrollPane(jta);
       JOptionPane.showMessageDialog(null, scrollableArea, "Information", JOptionPane.PLAIN_MESSAGE);
   }
   
   public void search(String searchTerm) {
      //TABLE, the table in the db to be looking through
      //String table = (String)searchFor.getSelected();
      //TERM, the category/data field to be looking at
      //String term = (String)searchUsing.getSelected();
      //SEARCH TERM, the actual text to search for
      // (declaired as a param)
      
   }
   public String[] updateSearchUsing(String selection) {
      switch (selection) {
         case "Faculty":
            String[] terms = {"First Name","Last Name","Department","Email","Phone Number","Interests","Office Number","Abstracts"};
            return terms;
         case "Student":
            String[] terms1 = {"First Name","Last Name","Interests","Degree","Email","Phone Number"};
            return terms1;
         case "Interests":
            String[] terms2 = {"Description"};
            return terms2;
         case "Degree":
            String[] terms3 = {"Name"};
            return terms3;
         case "Department":
            String[] terms4 = {"Name"};
            return terms4;
      }
      return null;
   }
   public static void main(String[] args) {
      FrontEnd fe = new FrontEnd(new DataLayer("root","student"));
   }
}
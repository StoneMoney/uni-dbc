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
 
public class FrontEnd extends JFrame {
   private DataLayer backend;
   private JComboBox searchFor;
   private JComboBox searchUsing;
   private JTextField searchBox;
   private JButton searchButton;
   private JButton viewButton;
   private JButton editButton;
   private JButton dropButton;
   private JButton insertButton;
   private JMenuItem tableInfo;
   private JList resultsList;
   private int[] allIds;
   private String currentTable;
   private boolean authenticated; 
   public FrontEnd(DataLayer backend,String userName, boolean authenticated) {
      super("University Database Controller");
      JFrame me = this;
      this.backend = backend;
      this.authenticated = authenticated;
      backend.connect();
      currentTable = "null";
      JPanel mainLayoutPanel = new JPanel(new BorderLayout());
      // SIDEBAR
      JPanel databaseSelectorPanel = new JPanel();
      databaseSelectorPanel.setLayout(new BoxLayout(databaseSelectorPanel, BoxLayout.Y_AXIS));
      JButton facultyButton = new JButton("Faculty");
      JButton studentsButton = new JButton("Student");
      JButton degreesButton = new JButton("Degree");
      JButton interestsButton = new JButton("Interest");
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
         String[] searchForTerms = {"Faculty","Student","Interest","Degree","Department"};
         searchFor = new JComboBox(searchForTerms);
         searchForPanel.add(searchForLabel, BorderLayout.NORTH);
         searchForPanel.add(searchFor, BorderLayout.CENTER);
         searchSelectors.add(searchForPanel);
         JPanel searchUsingPanel = new JPanel(new BorderLayout());
         JLabel searchUsingLabel = new JLabel("Using term:");
         searchUsing = new JComboBox(getTermsFor("Faculty"));
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
      JPanel buttonRow;
      if(authenticated) {
         buttonRow = new JPanel(new GridLayout(1,4));
         editButton = new JButton("Edit");
         dropButton = new JButton("Drop");
         insertButton = new JButton("Insert");
         buttonRow.add(viewButton);
         buttonRow.add(editButton);
         buttonRow.add(dropButton);
         buttonRow.add(insertButton);
      } else {
         buttonRow = new JPanel();
         buttonRow.add(viewButton);
      }
      setButtonRow(false);
      mainArea.add(resultsList,BorderLayout.CENTER);
      JScrollPane scrollableMainArea = new JScrollPane(mainArea);
      mainLayoutPanel.add(scrollableMainArea, BorderLayout.CENTER);
      mainLayoutPanel.add(buttonRow,BorderLayout.SOUTH);
      // MENU BAR
      JMenuBar infoBar = new JMenuBar();
      JMenuItem login = new JMenuItem("Logged in as "+userName);
      login.setEnabled(false);
      tableInfo = new JMenuItem("Viewing "+currentTable+" table");
      tableInfo.setEnabled(false);
      JMenuItem logout = new JMenuItem("Log Out");
      infoBar.add(login);
      infoBar.add(tableInfo);
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
            String[] usingTermsList = getTermsFor((String)searchFor.getSelectedItem());
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
            getList("Interest");
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
            search();  
         }
      });
      logout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            logout();
         }
      });
      if(authenticated) {
         dropButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(resultsList.getSelectedIndex() == -1) {
                  JOptionPane.showMessageDialog(me,"You don't have a row selected!","Drop Error",JOptionPane.ERROR_MESSAGE);
               } else {
                  int paneRes = JOptionPane.showConfirmDialog(me,"Are you sure you want to drop this selection?","Drop Row?",JOptionPane.WARNING_MESSAGE);
                  if(paneRes==JOptionPane.YES_OPTION){  
                     drop();
                  }
               }
            }
         });
         editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               if(resultsList.getSelectedIndex() == -1) {
                  JOptionPane.showMessageDialog(me,"You don't have a row selected!","Edit Error",JOptionPane.ERROR_MESSAGE);
               } else {
                  EditorPanel ep = new EditorPanel(getEditableTermsFor(currentTable),allIds[resultsList.getSelectedIndex()],currentTable,backend);
                  ep.addWindowListener(new java.awt.event.WindowAdapter() {
                     public void windowClosing(java.awt.event.WindowEvent e) {
                         getList(currentTable);
                     }
                 });
               }
            }
         });
         insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               EditorPanel ep = new EditorPanel(getEditableTermsFor(currentTable),currentTable,backend);
            }
         });
      }
   }
   
   public void setButtonRow(boolean state) {
      viewButton.setEnabled(state);
      if(authenticated) {
         editButton.setEnabled(state);
         insertButton.setEnabled(state);
         dropButton.setEnabled(state);
      }
   }
   
   public void clearList() {
      resultsList.clearSelection();
      resultsList.setListData(new String[0]);
      setButtonRow(false);
   }
   
   public void getList(String table) {
      ArrayList<MatchingData> matching = backend.getList(table);
      currentTable = table;
      tableInfo.setText("Viewing "+currentTable+" table");
      loadList(matching);
   }
   
   public void loadList(ArrayList<MatchingData> matching) {
      resultsList.clearSelection();
      String[] allStrings = new String[matching.size()];
      allIds = new int[matching.size()];
      for(int i = 0; i < matching.size(); i++) {
         MatchingData md = matching.get(i);
         allStrings[i] = md.getDisplayString();
         allIds[i] = md.getId();
      }
      setButtonRow(true);
      resultsList.setListData(allStrings);
   }
   
   public void viewInfo() {
       int index = resultsList.getSelectedIndex();
       String output = backend.get(currentTable, allIds[index]);
       JTextArea jta = new JTextArea(output, 10, 50);
       JScrollPane scrollableArea = new JScrollPane(jta);
       JOptionPane.showMessageDialog(null, scrollableArea, "Information", JOptionPane.PLAIN_MESSAGE);
   }
   
   public void drop() {
      int index = resultsList.getSelectedIndex();
      boolean result = backend.drop(currentTable, allIds[index]);
      if(!result) {
         JOptionPane.showMessageDialog(this,"Unable to drop this row","Drop Error",JOptionPane.ERROR_MESSAGE);
      }
      getList(currentTable);
   }
   
   public void search() {
      //TABLE, the table in the db to be looking through
      String table = (String)searchFor.getSelectedItem();
      //TERM, the category/data field to be looking at
      String term = (String)searchUsing.getSelectedItem();
      //SEARCH TERM, the actual text to search for
      // (declaired as a param)
      currentTable = table;
      tableInfo.setText("Viewing "+currentTable+" table");
      String searchTerm = (String)searchBox.getText();
      loadList(backend.search(table,term,searchTerm));
   }
   public String[] getTermsFor(String selection) {
      switch (selection) {
         case "Faculty":
            String[] terms = {"First Name","Last Name","Department","Email","Phone Number","Interest","Office Number","Abstracts"};
            return terms;
         case "Student":
            String[] terms1 = {"First Name","Last Name","Interest","Degree","Email","Phone Number"};
            return terms1;
         case "Interest":
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
   
   public String[] getEditableTermsFor(String selection) {
      switch (selection) {
         case "Faculty":
            String[] terms = {"First Name","Last Name","Email","Phone Number","Office Number","Abstracts"};
            return terms;
         case "Student":
            String[] terms1 = {"First Name","Last Name","Email","Phone Number"};
            return terms1;
         case "Interest":
            String[] terms2 = {"Interest Description"};
            return terms2;
         case "Degree":
            String[] terms3 = {"Degree Name"};
            return terms3;
         case "Department":
            String[] terms4 = {"Department Name"};
            return terms4;
      }
      return null;
   }
   
   public void logout() {
      this.dispose();
      backend.close();
      Login.launch();
   }
   
   public static void main(String[] args) {
      FrontEnd fe = new FrontEnd(new DataLayer(),"Test Launch", true);
   }
}
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.util.ArrayList;
 
public class EditorPanel extends JFrame {
   private JTextField[] fields;
   private DataLayer backend;
   EditorPanel(String[] editableTerms, int id, String table, DataLayer backend) {
      super("Editor Panel");
      JFrame me = this;
      setLayout(new BorderLayout());
      
      this.backend = backend;

      int reps = editableTerms.length;
      JPanel grid = new JPanel(new GridLayout(reps+1,2));
      fields = new JTextField[reps];
      String[] currentTerms = backend.getCurrentTerms(table, id, editableTerms);
      for(int i = 0; i < reps; i++) {
         JTextField t = new JTextField(currentTerms[i]);
         fields[i] = t;
         grid.add(new JLabel(editableTerms[i]));
         grid.add(t);
      }
      JButton submit = new JButton("Update");
      JButton cancel = new JButton("Cancel");
      grid.add(cancel);
      grid.add(submit);
      
      add(grid,BorderLayout.CENTER);
      add(new JPanel(),BorderLayout.NORTH);
      add(new JPanel(),BorderLayout.SOUTH);
      add(new JPanel(),BorderLayout.EAST);
      add(new JPanel(),BorderLayout.WEST);
      
      setVisible(true);
      setSize(500,25*(reps+1)+50);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            me.dispose();
         }
      });
      submit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] values = new String[reps];
            for(int i = 0; i < reps; i++) {
               values[i] = fields[i].getText();
            }
            backend.modify(table, id, editableTerms, values);
            me.dispose();
         }
      });
   }
   EditorPanel(String[] editableTerms, String table, DataLayer backend) {
      super("Insert Panel");
      JFrame me = this;
      setLayout(new BorderLayout());
      
      this.backend = backend;

      int reps = editableTerms.length;
      JPanel grid = new JPanel(new GridLayout(reps+1,2));
      fields = new JTextField[reps];
      for(int i = 0; i < reps; i++) {
         JTextField t = new JTextField();
         fields[i] = t;
         grid.add(new JLabel(editableTerms[i]));
         grid.add(t);
      }
      JButton submit = new JButton("Insert");
      JButton cancel = new JButton("Cancel");
      grid.add(cancel);
      grid.add(submit);
      
      add(grid,BorderLayout.CENTER);
      add(new JPanel(),BorderLayout.NORTH);
      add(new JPanel(),BorderLayout.SOUTH);
      add(new JPanel(),BorderLayout.EAST);
      add(new JPanel(),BorderLayout.WEST);
      
      setVisible(true);
      setSize(500,25*(reps+1)+50);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            me.dispose();
         }
      });
      submit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String[] values = new String[reps];
            for(int i = 0; i < reps; i++) {
               values[i] = fields[i].getText();
            }
            backend.insert(table, editableTerms, values);
            me.dispose();
         }
      });
   }
}
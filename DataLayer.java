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
 * File: DataLayer.java
 */


import java.sql.*;
import java.util.*;

public class DataLayer {

    // Connection
    private Connection conn = null;
    private ResultSet rs;
    private Statement stmt;
    private String sql;
    private String username;
    private String password;
    final String DEFAULT_Driver = "com.mysql.cj.jdbc.Driver";

    // Default Constructor
    public DataLayer(String username, String password){
      this.username = username;
      this.password = password;
    }

    /***
     * @return returns a database connection object.
     */
    public Connection connect(){
        conn = null;
        Scanner kb = new Scanner(System.in);

        String url = "jdbc:mysql://localhost/university";

        url = url + "?serverTimeZone=UTC";

        try{
            Class.forName(DEFAULT_Driver);
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException cnfe){
            System.out.println("ERROR, CAN NOT CONNECT TO DATABASE!!!");
            System.out.println("Class");
            System.out.println("ERROR MEESSAGE -> " + cnfe);
            System.exit(0);
        }// End of
        catch (SQLException sqle){
            System.out.println("ERROR, SQL EXCEPTION in connect method");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
        }// End of SQLExecption

        return conn;
    }// End of Connect Method


    /***
     * close checks to see if the connection is open and if it is, it closes it.
     * @return returns true if the connection, statement, and resultset are closed
     */
    public boolean close(){
        try{
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        catch (SQLException sqle){
            System.out.println("ERROR IN close()");
            System.out.println("ERROR MESSAGE -> " + sqle);
            sqle.printStackTrace();
            return false;
        }// End of catch

        return true;
    }// End of close()

   public String get(String table, int id) {
      String ret = "";
      try {
         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+table+" WHERE "+table+"ID = ?",ResultSet.TYPE_SCROLL_INSENSITIVE);
         stmt.setInt(1,id);
         ResultSet rs = stmt.executeQuery();
         ResultSetMetaData rsmd = rs.getMetaData();
         int colCount = rsmd.getColumnCount();
         rs.next();
         for(int i = 1; i < colCount; i++) {
            ret+=rsmd.getColumnName(i)+": "+rs.getObject(i)+"\n";
         }
      } catch (SQLException sqle){
         sqle.printStackTrace();
      }
      if(ret.length() == 0) {
         ret = "Could not find type "+table+" of id "+id;
      }
      return ret;
   }
   
   public ArrayList<MatchingData> getList(String table) {
      String sql = "SELECT * FROM "+table.toLowerCase()+" LIMIT 10;";
      ArrayList<MatchingData> list = new ArrayList<MatchingData>();
      try {
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         ResultSetMetaData rsmd = rs.getMetaData();
         int colCount = rsmd.getColumnCount();
         while(rs.next()) {
            //Column 1 is ALWAYS the id
            int id = rs.getInt(1);
            String displayString = "";
            for(int i = 0; i < colCount; i++) {
               switch(table) {
                  case "Student":
                  case "Faculty":
                     displayString = rs.getString(2) + " " + rs.getString(3);
                     break;
                  case "Degree":
                  case "Interest":
                  case "Department":
                     displayString = rs.getString(2);
                     break;
               }
            }
            list.add(new MatchingData(id,displayString));
         }
      } catch (SQLException sqle){
         sqle.printStackTrace();
      }
      return list;
   }
   
   boolean addAttribute(String table, String attribute, int tableId, int thingId) {
      String sql = "INSERT INTO "+table+"_"+attribute+" VALUES ("+tableId+","+thingId+");";
      int changed = 0;
      try {
         Statement stmt = conn.createStatement();
         changed = stmt.executeUpdate(sql);
      } catch (SQLException sqle) {
         System.out.println("Could not modify "+table+"_"+attribute+" to add value pair "+tableId+" "+thingId);
         sqle.printStackTrace();
      }
      return (changed > 0);
   }
   
//    public ArrayList<MatchingData> search(String table, String attribute, String search) {
//       
//    }
    // /***
//      * facultySearchForStudentWithInterest will perform a query and return an arrayList
//      * @param interestID integer that refers to the unique identifier in the interests table
//      * @return returns an arrayList of <user> type with Students who have a particular interest
//      */
//     public ArrayList<user> facultySearchForStudentWithInterest(int interestID){
//         ArrayList<user> listOfStudentsWithInterest = new ArrayList<user>();
// 
//         String sql = "SELECT student.studentID, student.firstName, student.lastName, student.phoneNumber, student.email, interests.interestDescription" +
//                 " FROM student" +
//                 " JOIN student_interests" +
//                 " ON student.studentID = student_interests.studentID" +
//                 " JOIN interests" +
//                 " ON interests.interestID = student_interests.interestID" +
//                 " WHERE interests.interestID = " + interestID;
// 
//         try{
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql);
// 
// 
//             while (rs.next()) {
//                 int studentID = rs.getInt(1);
//                 String studentFirstName = rs.getString(2);
//                 String studentLastName = rs.getString(3);
//                 String studentPhoneNumber = rs.getString(4);
//                 String studentEmail = rs.getString(5);
//                 String studentInterestsDescription = rs.getString(6);
// 
//                 // Store the values in an array list
//                 user Student = new user(studentID, studentFirstName, studentLastName, studentPhoneNumber, studentEmail);
//                 listOfStudentsWithInterest.add(Student);
//             }
//         }
//         catch (SQLException sqle){
//             sqle.printStackTrace();
//         }
//         return listOfStudentsWithInterest;
//     }// End of facultySearchForStudentWithInterest
// 
// 
//     /***
//      * facultySearchForFacultyWithInterest will perform a query and return an arrayList
//      * @param interestID integer that refers to the unique identifier in the interests table
//      * @return returns an arrayList of <user> type with Faculty who have a particular interest
//      */
//     public ArrayList<user> facultySearchForFacultyWithInterest(int interestID){
//         ArrayList<user> listOfFacultyWithInterest = new ArrayList<user>();
// 
//         String sql = "SELECT faculty.facultyID, faculty.firstName, faculty.lastName, faculty.phoneNumber, faculty.email, interests.interestDescription" +
//                 " FROM faculty" +
//                 " JOIN faculty_interests" +
//                 " ON faculty.facultyID = faculty_interests.facultyID" +
//                 " JOIN interests" +
//                 " ON interests.interestID = faculty_interests.interestID" +
//                 " WHERE interests.interestID = " + interestID;
// 
//         try{
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql);
// 
//             while(rs.next()){
//                 int facultyID = rs.getInt(1);
//                 String facultyFirstName = rs.getString(2);
//                 String facultyLastName = rs.getString(3);
//                 String facultyPhoneNumber = rs.getString(4);
//                 String facultyEmail = rs.getString(5);
//                 String facultyInterestsDescription = rs.getString(6);
// 
//                 // Store the values in an array list
//                 user Faculty = new user(facultyID, facultyFirstName, facultyLastName, facultyPhoneNumber, facultyEmail);
//                 listOfFacultyWithInterest.add(Faculty);
//             }// End of WhileLoop
//         }// End of Try Block
//         catch(SQLException sqle){
//             System.out.println("ERROR MESSAGE -> " + sqle);
//             System.out.println("ERROR SQLException in facultySearchForFacultyWithInterest()");
//             sqle.printStackTrace();
//         }// End of catch block
// 
//         return listOfFacultyWithInterest;
//     }// End of facultySearchForFacultyWithInterest
// 
// 
//     /***
//      * facultySearchForStudentwithDegree will perform a query and return an array list
//      * @param degreeID integer that refers to the unique identifier in the degree table
//      * @return returns an arrayList of <user> type with Student who have a particular degree
//      */
//     public ArrayList<user> facultySearchForStudentWithDegree(int degreeID){
//         ArrayList<user> listOfStudentsWithDegree = new ArrayList<>();
// 
//         String sql = "SELECT student.studentID, student.firstName, student.lastName, student.phoneNumber, student.email, degree.degreeName" +
//                 " FROM student" +
//                 " JOIN student_degree" +
//                 " ON student.studentID = student_degree.studentID" +
//                 " JOIN degree" +
//                 " ON degree.degreeID = student_degree.degreeID" +
//                 " WHERE degree.degreeID = " + degreeID;
// 
//         try{
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(sql);
// 
//             while(rs.next()){
//                 int studentID = rs.getInt(1);
//                 String studentFirstName = rs.getString(2);
//                 String studentLastName = rs.getString(3);
//                 String studentPhoneNumber = rs.getString(4);
//                 String studentEmail = rs.getString(5);
//                 String studentDegree = rs.getString(6);
// 
//                 // Store the values in an array List
//                 user Student = new user(studentID, studentFirstName, studentLastName, studentPhoneNumber,studentEmail);
//                 listOfStudentsWithDegree.add(Student);
//             }// End of While Loop
// 
//         }// End of Try Block
//         catch(SQLException sqle){
//             System.out.println("ERROR MESSAGE -> " + sqle);
//             System.out.println("ERROR SQLException in facultySearchForStudentWithDegree()");
//             sqle.printStackTrace();
//         }// End of Catch Block
// 
//         return listOfStudentsWithDegree;
//     }// End of facultySearchForStudentWithDegree
// 
     /***
     * Deletes student entry from student table
     * @param studentID integer referring to unique studentID
     * @return returns true if student is deleted, false if something goes wrong.
     */
    public boolean deleteStudent(int studentID){
        // Local Variables
        String sql = "DELETE FROM student where studentID = " + studentID;
        int number_rows = 0;

        try{
            Statement stmt = conn.createStatement();
            number_rows = stmt.executeUpdate(sql);

            return true;
        }// End of Try Block
        catch(SQLException sqle){
            System.out.println("ERROR MESSAGE -> " + sqle);
            System.out.println("ERROR SQLException in deleteStudent()");
            sqle.printStackTrace();

            return false;
        }// End of Catch block

    } // End of deleteStudent


    /***
     * Deletes faculty entry from faculty table
     * @param facultyID integer referring to unique facultyID
     * @return returns true if faculty is deleted, false if something goes wrong.
     */
    public boolean deleteFaculty(int facultyID){
        // Local Variables
        String sql = "DELETE FROM faculty WHERE facultyID = " + facultyID;
        int number_rows = 0;

        try{
            Statement stmt = conn.createStatement();
            number_rows = stmt.executeUpdate(sql);

            return true;
        }// End of Try Block
        catch(SQLException sqle){
            System.out.println("ERROR MESSAGE -> " + sqle);
            System.out.println("ERROR SQLException in deleteFaculty()");
            sqle.printStackTrace();

            return false;
        }// End of Catch block

    }// End of deleteFaculty


    /***
     * Uses a preparedStatement to update the Faculty table ALL data must be provided
     * @param facultyID integer referring to unique faculty identifier
     * @param firstName String, First name of faculty member
     * @param lastName  String, Last name of faculty member
     * @param abstracts String, The abstract a faculty has listed
     * @param officeNumber String, The office Number of the faculty member
     * @param phoneNumber String, The phone number of the faculty member
     * @param email String, The email address of the faculty member
     * @return returns true if the update statement works, false if something went wrong.
     */
    public boolean updateFaculty(int facultyID, String firstName, String lastName, String abstracts, String officeNumber,
                                 String phoneNumber, String email){


        try{
            PreparedStatement updateStmt;

            updateStmt = conn.prepareStatement("UPDATE faculty SET firstName = ?," +
                    "lastName = ?, " +
                    "abstracts = ?, " +
                    "officeNumber = ?, " +
                    "phoneNumber = ?, " +
                    "email = ?" +
                    "WHERE facultyID = ?");

            // Bind the values passed in to the prepared statement
            updateStmt.setString(1, firstName);
            updateStmt.setString(2, lastName);
            updateStmt.setString(3, abstracts);
            updateStmt.setString(4, officeNumber);
            updateStmt.setString(5, phoneNumber);
            updateStmt.setString(6, email);
            updateStmt.setInt(7, facultyID);

            // Execute the prepared statement
            updateStmt.executeUpdate();

            return true;
        }// End of Try Block
        catch (SQLException sqle){
            System.out.println("ERROR MESSAGE -> " + sqle);
            System.out.println("ERROR SQLException in updateFaculty()");

            return false;
        }// End of Catch block
    }// End of updateFaculty


    /***
     * insertFaculty uses a preparedStatement to insert a faculty member
     * @param firstName String, First name of faculty member
     * @param lastName  String, Last name of faculty member
     * @param abstracts String, The abstract a faculty has listed
     * @param officeNumber String, The office Number of the faculty member
     * @param phoneNumber String, The phone number of the faculty member
     * @param email String, The email address of the faculty member
     * @return true if the insert statement works, false if something went wrong.
     */
    public boolean insertFaculty(String firstName, String lastName, String abstracts, String officeNumber,
                                 String phoneNumber, String email){

        // Local Variables
        String sql = "INSERT INTO faculty(firstName, lastName, abstracts, officeNumber, phoneNumber, email)" +
                " values (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement insertStmt;

            // Bind the values passed in to the prepared statement
            insertStmt = conn.prepareStatement(sql);
            insertStmt.setString(1, firstName);
            insertStmt.setString(2, lastName);
            insertStmt.setString(3, abstracts);
            insertStmt.setString(4, officeNumber);
            insertStmt.setString(5, phoneNumber);
            insertStmt.setString(6, email);

            // Execute the prepared statement
            insertStmt.execute();
            return true;
        }// End of Try Block
        catch (SQLException sqle){
            System.out.println("ERROR MESSAGE -> " + sqle);
            System.out.println("ERROR SQLException in insertFaculty()");

            return false;
        }// End of Catch block
    }// End of insertFaculty


    /***
     * insertStudent uses a prepared statement
     * @param firstName String, First name of the student
     * @param lastName String, Last name of the student
     * @param phoneNumber String, The phoneNumber associated with the student
     * @param email String, The email address of the student
     * @return returns true if the insert statement works, false if something went wrong
     */
    public boolean insertStudent(String firstName, String lastName, String phoneNumber, String email){
        // Local Variables
        String sql = "INSERT INTO student(firstName, lastName, phoneNumber, email)" +
                " values (?, ?, ?, ?)";

        try{
            PreparedStatement insertStmt;

            // Bind the values passed into the prepared statement
            insertStmt = conn.prepareStatement(sql);
            insertStmt.setString(1, firstName);
            insertStmt.setString(2, lastName);
            insertStmt.setString(3, phoneNumber);
            insertStmt.setString(4, email);

            // Execute the prepared statement
            insertStmt.execute();
            return true;
        }// End of Try Block
        catch(SQLException sqle){
            System.out.println("ERROR MESSAGE -> " + sqle);
            System.out.println("ERROR SQLException in insertFaculty()");

            return false;
        }// End of catch Block
    }// end of insertStudent


   public String getUsername() {
      return username;
   }

    public static void main(String[] args) {
        DataLayer dataLayer = new DataLayer("root","student");
        dataLayer.connect();
        System.out.println("Hello!");
    }


}

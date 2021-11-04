/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static universistant.controllers.LoginController.CurrentUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Add_marksController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Add_Marks;
    @FXML
    private Button Add_Attendance;
    @FXML
    private Button Exit;
    @FXML
    private AnchorPane background;
    @FXML
    private ComboBox<String> SelectCID;
    @FXML
    private Text Assign_TeacherText;
    @FXML
    private ComboBox<String> selectSTD;
    @FXML
    private Button submit;
    @FXML
    private Button load;
    @FXML
    private ChoiceBox<String> selectExam;
    @FXML
    private TextField Marks;
    
    int sem=0;
    String cs;
    ObservableList<String> list = FXCollections.observableArrayList(" ");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectExam.setValue("Select Exam Type");
        selectSTD.setValue("Select Student");
        SelectCID.setValue("Select Course");
        
        selectExam.getItems().addAll("Quiz", "Mid", "Final");
        
        Connection con = null; 
        try {        
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        } catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultSet = null;
        
        
        try {
            resultSet = st.executeQuery("Select * from courseteacher");
        } catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
               
        try {
            while (resultSet.next()) {  // loop
                
                String a= resultSet.getString("teacher");
                if(a.equals(CurrentUser))
                {
                    //list.add(resultSet.getString("name"));
                    SelectCID.getItems().addAll(resultSet.getString("course"));
                    
                }
                    
            }} catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void clickHomeViaTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/teacher_dashboard.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickExit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/exit.fxml"));
        Scene scene = new Scene(root, 404,218);
        Stage stage = new Stage();
        //stage.setTitle("Exit");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void clickAddMarks(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_marks.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickAddAttendance(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_attendance.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void ClickCID(ActionEvent event) {
    }

    @FXML
    private void clickSubmit(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        
        Connection con;
        
        String a= selectSTD.getValue();
        String b= cs;
        String c= selectExam.getValue();
        String d= Marks.getText();
        
        int m= Integer.parseInt(d);
        
        
        while(a=="Select Student" || m==0)
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
            continue;
        }
        
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "insert into marks (course, examType, stdName, semester, mark)"
              + " values (?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, b);
        preparedStmt.setString (2, c);
        preparedStmt.setString (3, a);
        preparedStmt.setInt (4, sem);
        preparedStmt.setInt (5, m);
   
        preparedStmt.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/teacher_dashboard.fxml"));
        background.getChildren().setAll(pane); 
    }

    @FXML
    private void clickLoad(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        cs=SelectCID.getValue();
        
        SelectCID.getItems().clear();
        selectSTD.getItems().clear();
        
        String x= cs;
        sem=0;
        int dept=0;
        
        Connection con;
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        Statement st = con.createStatement();
        Statement st2 = con.createStatement();
        ResultSet resultSet = null;
        ResultSet rs2 = null;
        
        resultSet = st.executeQuery("Select * from course");
        rs2 = st2.executeQuery("Select * from student");
               
        while (resultSet.next()) {  // loop

            if(x.equals(resultSet.getString("name")))
            {
                sem= resultSet.getInt("Semester");
                dept= resultSet.getInt("Department");
            }     
       }
        
       System.out.println(sem);
       System.out.println(dept);
       resultSet.close();
       
       
       while (rs2.next()) {  
           
           int a= rs2.getInt("CurrentSemester");
           int b= rs2.getInt("Department");

           if(sem==a && dept==b)
                selectSTD.getItems().addAll(rs2.getString("name")); 
       }
       
       rs2.close();
       
       con.close();
    }

}

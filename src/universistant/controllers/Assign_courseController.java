/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant.controllers;

import java.sql.*;
//import com.mysql.jdbc.PreparedStatement;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Assign_courseController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Add_Student;
    @FXML
    private Button Add_Teacher;
    @FXML
    private Button Add_Course;
    @FXML
    private Button Exit;
    @FXML
    private AnchorPane background;
    @FXML
    private Text Assign_TeacherText;
    @FXML
    private Text DepartmentText;
    @FXML
    private Button Register;
    @FXML
    private ChoiceBox<String> SelectDept;
    @FXML
    private ComboBox<String> SelectCID;
    @FXML
    private ComboBox<String> SelectT;
    @FXML
    private Button loadButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        
        SelectDept.setValue("Select Department");
        SelectCID.setValue("Select Department first");
        SelectT.setValue("Select Department First");
        
        SelectDept.getItems().addAll("CSE", "MPE", "EEE", "CEE", "BTM", "TVE");
    }    
    
    public void fillComboBox() throws SQLException, ClassNotFoundException
    {
        String c= SelectDept.getValue();
        
        int x=0;
        Connection con;
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        Statement st = con.createStatement();
        Statement st2 = con.createStatement();
        ResultSet resultSet = null;
        ResultSet rs2 = null;
        
        //resultSet = st.executeQuery("Select name from teacher where department='4'");
        
        
        if(c=="CSE")
        {
            x=4;
            resultSet = st.executeQuery("Select name from teacher where department='4'");
            rs2 = st2.executeQuery("Select name from course where department='4'");
        }
        else if (c=="MPE")
        {
            x=1;
            resultSet = st.executeQuery("Select name from teacher where department='1'");
            rs2 = st2.executeQuery("Select name from course where department='1'");
        }
        else if (c=="CEE")
        {
            x=3;
            resultSet = st.executeQuery("Select name from teacher where department='3'");
            rs2 = st2.executeQuery("Select name from course where department='3'");
        }
        else if (c=="EEE")
        {
            x=2;
            resultSet = st.executeQuery("Select name from teacher where department='2'");
            rs2 = st2.executeQuery("Select name from course where department='2'");
        }
        else if (c=="BTM")
        {
            x=5;
            resultSet = st.executeQuery("Select name from teacher where department='5'");
            rs2 = st2.executeQuery("Select name from course where department='5'");
        }
        else if (c=="TVE")
        {
            x=6;
            resultSet = st.executeQuery("Select name from teacher where department='6'");
            rs2 = st2.executeQuery("Select name from course where department='6'");
        }
      
               
        while (resultSet.next()) {  // loop

           // Now add the comboBox addAll statement 
            SelectT.getItems().addAll(resultSet.getString("name")); 
       }
        
       resultSet.close();
       
       while (rs2.next()) {  // loop

           // Now add the comboBox addAll statement 
            SelectCID.getItems().addAll(rs2.getString("name")); 
       }
       
       rs2.close();
       
       con.close();
       
    }
    

    @FXML
    private void clickHome(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
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
    private void clickAddTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_teacher.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickAddCourse(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_course.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickAddStudent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_student.fxml"));
        background.getChildren().setAll(pane);
    }

    private void clickAssignTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/assign_course.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void click_Register(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        int sem=0;
        
        String a= SelectDept.getValue();
        String b= SelectCID.getValue();
        String c= SelectT.getValue();
        
        while(b=="Select Course ID" || c=="Select Teacher")
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
            continue;
        }
        
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM  course");
        
        while(res.next())
        {
            sem= res.getInt("Semester");
        }
        
        res.close();
        
        String query = "insert into courseteacher (Course, Teacher, Semester)"
              + " values (?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, b);
        preparedStmt.setString (2, c);
        preparedStmt.setInt (3, sem);
  
        preparedStmt.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
        background.getChildren().setAll(pane); 
           
        
    }

    @FXML
    private void ClickCID(ActionEvent event) throws ClassNotFoundException, SQLException {
        
    }

    @FXML
    private void ClickSelectT(ActionEvent event) {
    }

    @FXML
    private void clickLoad(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        SelectCID.getItems().clear();
        SelectT.getItems().clear();
        
        fillComboBox();
        SelectCID.setValue("Select Course ID");
        SelectT.setValue("Select Teacher");
      
    }
}

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Add_courseController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Add_Student;
    @FXML
    private Button Add_Teacher;
    @FXML
    private Button Assign_Teacher;
    @FXML
    private Button Exit;
    @FXML
    private AnchorPane background;
    @FXML
    private TextField Username_TextField;
    @FXML
    private Button Register;
    @FXML
    private ChoiceBox<String> SelectDept;
    @FXML
    private ChoiceBox<String> SelectType;
    @FXML
    private ChoiceBox<String> SelectSemester;
    @FXML
    private ChoiceBox<Float> SelectCredit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        SelectDept.getItems().addAll("CSE", "MPE", "EEE", "CEE", "BTM", "TVE");
        SelectType.getItems().addAll("Theory", "Lab");
        SelectSemester.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8");
        SelectCredit.getItems().addAll((float) 0.5,(float) 0.75,(float) 1.0,(float) 1.5,(float) 2.0,(float) 3.0,(float) 4.0);
       
        
        SelectDept.setValue("Select Department");
        SelectType.setValue("Select Course Type");
        SelectSemester.setValue("Select Semester");
        // TODO
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

    private void clickAddCourse(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_course.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickAddStudent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/add_student.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickAssignTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/assign_course.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void click_Register(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        Connection con;
        Float d=(float) 100.0;
        
        String a= Username_TextField.getText();
        String b= SelectDept.getValue();
        String c= SelectSemester.getValue();
        d= SelectCredit.getValue();
        String e= SelectType.getValue();
        
        int x=0, k = 0;
        
        if(c=="Select Session" || d==100.0)
            k=0;
        else
            k=Integer.parseInt(c);  
        
        if(b=="CSE")
            x=4;
        else if (b=="MPE")
            x=1;
        else if (b=="CEE")
            x=3;
        else if (b=="EEE")
            x=2;
        else if (b=="BTM")
            x=5;
        else if (b=="TVE")
            x=6;
        
        
        while(a==" " || x==0 || k==0)
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
            continue;
        }
        
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "insert into course (Name, Department, Semester, Type, Credit)"
              + " values (?, ?, ?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, a);
        preparedStmt.setInt (2, x);
        preparedStmt.setInt (3, k);
        preparedStmt.setString (4, e);
        preparedStmt.setFloat (5, d);
   
        preparedStmt.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
        background.getChildren().setAll(pane); 
    }
    
}

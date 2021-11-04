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
public class Add_teacherController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Add_Student;
    @FXML
    private Button Add_Course;
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
    private ChoiceBox<String> SelectPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        SelectDept.setValue("Select Department");
        SelectPost.setValue("Select Post");
        
        SelectDept.getItems().addAll("CSE", "MPE", "EEE", "CEE", "BTM", "TVE");
        SelectPost.getItems().addAll("Professor", "Assistant Prof", "Associate Prof", "Lecturer");
        
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

    @FXML
    private void clickAssignTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/assign_course.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void click_Register(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        int[][] counter2 = new int[6][1];
        for(int i=0;i<6;i++)
                counter2[i][0]=0;
        
        int id=0;
        
        Connection con;
        
        String a= Username_TextField.getText();
        String c= SelectDept.getValue();
        String b= SelectPost.getValue();
        
        int x = 0, y = 0;  
        
        if(c=="CSE")
            x=4;
        else if (c=="MPE")
            x=1;
        else if (c=="CEE")
            x=3;
        else if (c=="EEE")
            x=2;
        else if (c=="BTM")
            x=5;
        else if (c=="TVE")
            x=6;
        
        if(b=="Professor")
            y=1;
        else if(b=="Assistant Prof")
            y=2;
        else if(b=="Associate Prof")
            y=3;
        else
            y=4;
        
        if(a==" " || x==0 || y==0)
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
        }
        
        else
        {
           
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "insert into teacher (ID, Name, Department, Post, Password)"
              + " values (?, ?, ?, ?, ?)";
        
        String query2 = "insert into login (ID, Name, Password, Role)"
              + " values (?, ?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt (1, ++counter2[x-1][0]);
        preparedStmt.setString (2, a);
        preparedStmt.setInt (3, x);
        preparedStmt.setInt (4, y);
        preparedStmt.setString (5, a);
   
        preparedStmt.execute();
        
        PreparedStatement preparedStmt2 = con.prepareStatement(query2);
        preparedStmt2.setInt (1, counter2[x-1][0]);
        preparedStmt2.setString (2, a);
        preparedStmt2.setString (3, a);
        preparedStmt2.setInt (4, 2);
        
        preparedStmt2.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
        background.getChildren().setAll(pane); 
        
        
        }  
    }
}
    


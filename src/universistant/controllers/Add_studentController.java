/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.RadioButton;
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
public class Add_studentController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button Add_Teacher;
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
    private ChoiceBox<String> SelectSession;
    @FXML
    private ChoiceBox<String> SelectDept;
    @FXML
    private ChoiceBox<String> SelectProg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        SelectDept.setValue("Select Department");
        SelectSession.setValue("Select Session");
        SelectProg.setValue("Select Program");
        
        SelectDept.getItems().addAll("CSE", "MPE", "EEE", "CEE", "BTM", "TVE");
        SelectSession.getItems().addAll("16", "17", "18", "19", "20");
        SelectProg.getItems().addAll("B.Sc", "M.Sc", "P.hD");
        
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
        
        int[][][] counter= new int[6][3][1];
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++)
                counter[i][j][0]=0;
        
        int id=0;
        
        Connection con;
        
        String a= Username_TextField.getText();
        String b= SelectSession.getValue();
        String c= SelectDept.getValue();
        String d= SelectProg.getValue();
        
        int x = 0, y = 0, k = 0;
        
        if(b=="Select Session")
            k=0;
        else
            k=Integer.parseInt(b);  
        
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
        
        if(d=="B.Sc")
            y=1;
        else if(d=="M.Sc")
            y=2;
        else
            y=3;
        
        if(a==" " || x==0 || y==0 || k==0)
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
        }
        
        else
        {
           
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "insert into student (ID, Name, Department, Program, Password, Role, Session)"
              + " values (?, ?, ?, ?, ?, ?, ?)";
        
        String query2 = "insert into login (ID, Name, Password, Role)"
              + " values (?, ?, ?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt (1, ++counter[x-1][y-1][0]);
        preparedStmt.setString (2, a);
        preparedStmt.setInt (3, x);
        preparedStmt.setInt (4, y);
        preparedStmt.setString (5, a);
        preparedStmt.setInt (6, 3);
        preparedStmt.setInt (7, k);
   
        preparedStmt.execute();
        
        PreparedStatement preparedStmt2 = con.prepareStatement(query2);
        preparedStmt2.setInt (1, counter[x-1][y-1][0]);
        preparedStmt2.setString (2, a);
        preparedStmt2.setString (3, a);
        preparedStmt2.setInt (4, 3);
        
        preparedStmt2.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
        background.getChildren().setAll(pane); 
        
        
        }  
    }

    
}

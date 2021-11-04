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

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Add_attendanceController implements Initializable {

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
    private ComboBox<String> SelectCID;
    private ChoiceBox<String> Selectstatus;
    @FXML
    private Button set;
    @FXML
    private TextField Username_TextField;
    @FXML
    private TextField Username_TextField1;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        SelectCID.setValue("Select Course");
        Selectstatus.setValue("Select Status");
        
        Selectstatus.getItems().addAll("Off", "On");
        
        Connection con = null;
        try {        
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        } catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultSet = null;
       
        
        try {
            resultSet = st.executeQuery("Select * from courseteacher");
            //System.out.println(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            while (resultSet.next()) {
                
                String t= resultSet.getString("Teacher");
                System.out.println(t);
                
                if(LoginController.CurrentUser.equals(t))
                {
                    System.out.println(resultSet.getString("Course"));
                    SelectCID.getItems().addAll(resultSet.getString("Course")); 
                }
                
            }} catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            con.close();
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(Add_attendanceController.class.getName()).log(Level.SEVERE, null, ex);
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


    private void clickSet(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        
        String a= SelectCID.getValue();
        String b= Selectstatus.getValue();
        
        if (a=="Select Course" || b=="Select Status")
        {
            JOptionPane.showMessageDialog(null, "Please Provide All The Information");
        }
        else
        {
        
        Connection con;
        
        int x=0, k = 100;
        
        if(b=="On")
            k=1;
        else
            k=0;  
      
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "insert into attendancestatus (Course, Status)"
              + " values (?, ?)";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setString (1, a);
        preparedStmt.setInt (2, k);
   
        preparedStmt.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/tecaher_dashboard.fxml"));
        background.getChildren().setAll(pane); 
       }
        
    }

    @FXML
    private void clickset(ActionEvent event) {
    }

    

    
}

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
public class Course_registrationController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button View_Marks;
    @FXML
    private Button View_Attendance;
    @FXML
    private Button Course_Reg;
    @FXML
    private Button Exit;
    @FXML
    private AnchorPane background;
    @FXML
    private Button set;
    @FXML
    private ChoiceBox<String> selectSem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        selectSem.setValue("Select Semester");
        selectSem.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8");
    }    

    @FXML
    private void clickHomeViaStudent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/home.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickViewMarks(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/view_marks.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickViewAttendance(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/view_attendance.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickCourseReg(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/course_registration.fxml"));
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
    private void clickset(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        String a= selectSem.getValue();
        
        int x=0;
        
        if(a!= "Select Semester")
            x= Integer.parseInt(a);
        else
            x=100;
        
        Connection con;
        
        if(x==100)
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
        }
        
        else
        {
           
        JOptionPane.showMessageDialog(null, "Information added successfully");
        
        Class.forName("com.mysql.jdbc.Driver");        
        con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
        String query = "update student set currentsemester = ? where name = ?";
        
        PreparedStatement preparedStmt = con.prepareStatement(query);
        preparedStmt.setInt (1, x);
        preparedStmt.setString (2, CurrentUser);
     
        preparedStmt.execute();
        
        con.close();
   
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/student_dashboard.fxml"));
        background.getChildren().setAll(pane); 
        
        
        }      
    }


   
    
}

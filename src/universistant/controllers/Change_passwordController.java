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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import static universistant.controllers.LoginController.CurrentUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Change_passwordController implements Initializable {

    @FXML
    private AnchorPane background;
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
    private TextField Username_TextField;
    @FXML
    private Button set;
    @FXML
    private TextField Username_TextField1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

        
    }

    @FXML
    private void clickHomeViaStudent(ActionEvent event) {
    }

    @FXML
    private void clickViewMarks(ActionEvent event) {
    }

    @FXML
    private void clickViewAttendance(ActionEvent event) {
    }

    @FXML
    private void clickCourseReg(ActionEvent event) {
    }

    @FXML
    private void clickExit(ActionEvent event) {
    }

    @FXML
    private void clickset(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
        Connection con;
        
        String a= Username_TextField.getText();
        String b= Username_TextField1.getText();
        
        if(a==" " || b==" ")
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
        }
        
        else
        {
            boolean flag=false;
            
            JOptionPane.showMessageDialog(null, "Information added successfully");
        
            Class.forName("com.mysql.jdbc.Driver");        
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
            String query = "update login set password = ? where name = ?";
            String query2 = "update student set password = ? where name = ?";
        
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM  login");
        
            while(res.next())
            {
                String passw=res.getString("Password");
           
                if(passw.equals(CurrentUser))
                    flag=true;   
            }
            
            res.close();
            
            if(flag)
            {
        
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString (1, b);
                preparedStmt.setString (2, CurrentUser);
       
                preparedStmt.execute();
                
                PreparedStatement preparedStmt2 = con.prepareStatement(query2);
                preparedStmt2.setString (1, b);
                preparedStmt2.setString (2, CurrentUser);
       
                preparedStmt2.execute();
                con.close();
            }
            
            else
            {
                JOptionPane.showMessageDialog(null, "Password Did Not Match");
            }
   
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/student_dashboard.fxml"));
            background.getChildren().setAll(pane);
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

    @FXML
    private Text Universistant;
    @FXML
    private ImageView Logo;
    @FXML
    private TextField Username_TextField;
    @FXML
    private PasswordField Password_TextField;
    @FXML
    private Button SignIn_Button;
    @FXML
    private AnchorPane background;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_SignIn(ActionEvent event) throws IOException {
        
        String uname = Username_TextField.getText();
        String pass = Password_TextField.getText();
        
        if(uname.equals("") && pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Username or Password blank");
            //flagx=true;
            
        }
        else if(uname.equals("student") && pass.equals("student"))
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/student_dashboard.fxml"));
            background.getChildren().setAll(pane);
            //JOptionPane.showMessageDialog(null, "Username or Password blank");
            //flagx=true;            
        }
        else if(uname.equals("teacher") && pass.equals("teacher"))
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/teacher_dashboard.fxml"));
            background.getChildren().setAll(pane);
            //JOptionPane.showMessageDialog(null, "Username or Password blank");
            //flagx=true;            
        }  
        else if(uname.equals("admin") && pass.equals("admin"))
        {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
            background.getChildren().setAll(pane);
            //JOptionPane.showMessageDialog(null, "Username or Password blank");
            //flagx=true;            
        }  
        else
        {
            JOptionPane.showMessageDialog(null, "Wrong Username or Password");
        }
             
    }
    
}

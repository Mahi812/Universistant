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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    
    Connection con;
    PreparedStatement pst;

    public static String CurrentUser= " ";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click_SignIn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        
        boolean flag=false;
        boolean flagx=false;
        
        String uname = Username_TextField.getText();
        String pass = Password_TextField.getText();
        
        if(uname.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Username or Password blank");
            flagx=true;
        }
        
        if(!flagx)
        {
        
            Class.forName("com.mysql.jdbc.Driver");        
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM  login");
        
            while(res.next())
            {
                String name=res.getString("Name");
                String passw=res.getString("Password");
                int role=res.getInt("Role");
           
                if(name.equals(uname) && passw.equals(pass))
                {
                    
                    flag=true;
                    CurrentUser=uname;
                
                    if(role==1)
                    {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/admin_dashboard.fxml"));
                        background.getChildren().setAll(pane);
                    }
                
                    else if(role==2)
                    {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/teacher_dashboard.fxml"));
                        background.getChildren().setAll(pane);
                    }
                
                    else
                    {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/student_dashboard.fxml"));
                        background.getChildren().setAll(pane); 
                    }
                
                }
             
            }
        }
        
            if(!flag && !flagx)
            {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password");
            }
             
    }
       
}

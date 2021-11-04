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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static universistant.controllers.LoginController.CurrentUser;
import universistant.marks;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class View_marksController implements Initializable {

    @FXML
    private Button Home;
    @FXML
    private Button View_Marks;
    @FXML
    private Button Course_Reg;
    @FXML
    private AnchorPane background;
    @FXML
    private Button Register;
    @FXML
    private ComboBox<String> selectCID;
    @FXML
    private TableColumn<marks, String> col1;
    @FXML
    private TableColumn<marks, String> col2;
    
    @FXML
    private TableView<marks> table;
    
    public ObservableList<marks> list=FXCollections.observableArrayList();
    int sem=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
        selectCID.setValue("Select Course");
        
        Connection con = null;         
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Statement st = null;
        Statement st2 = null;
      
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st2 = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        
        
        
        try {
            resultSet = st.executeQuery("Select * from student");
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
        try {
            while (resultSet.next()) {  // loop
                
                String a= resultSet.getString("name");
                if(a.equals(CurrentUser))
                {
                    sem= resultSet.getInt("CurrentSemester");   
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
        try {
            resultSet2 = st2.executeQuery("Select * from course");
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
      
        try {
            while (resultSet2.next()) {  // loop
                
                int pqr= resultSet2.getInt("semester");
                if(sem==pqr)
                {
                    //list.add(resultSet.getString("name"));
                    selectCID.getItems().addAll(resultSet2.getString("name"));
                    
                }
            }
                
                
                
               } catch (SQLException ex) {    
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            resultSet2.close();
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(View_marksController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickHomeViaStudent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/student_dashboard.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickViewMarks(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/view_marks.fxml"));
        background.getChildren().setAll(pane);
    }

    private void clickViewAttendance(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/view_attendance.fxml"));
        background.getChildren().setAll(pane);
    }

    @FXML
    private void clickCourseReg(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/fxml/course_registration.fxml"));
        background.getChildren().setAll(pane);
    }

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
    private void clickCID(ActionEvent event) {
    }

    @FXML
    private void click_Register(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        Connection con;
        
        String a= selectCID.getValue();
       
        
        if(a=="Select Course")
        {
            JOptionPane.showMessageDialog(null, "Please fill up all fields");
        }
        
        else
        {
            //boolean flag=false;
            
            //JOptionPane.showMessageDialog(null, "Information added successfully");
        
            Class.forName("com.mysql.jdbc.Driver");        
            con = DriverManager.getConnection("jdbc:mysql://localhost/universistantdb", "root","root");
        
            Statement st = (Statement) con.createStatement();
            ResultSet res = st.executeQuery("Select* from marks");
        
        
        while(res.next())
        {  
            String type = res.getString("examType");
            int m= res.getInt("mark");
            String name= res.getString("stdname");
            
            String mx= Integer.toString(m);
            
            if(CurrentUser.equals(name))
            {
                System.out.println(type);
                System.out.println(mx);
                list.add(new marks(type,mx));
                table.setItems(list);
            }
        
            col1.setCellValueFactory(new PropertyValueFactory<>("type"));
            col2.setCellValueFactory(new PropertyValueFactory<>("mark"));
            
            //col1.setCellValueFactory(cellData -> cellData.getValue().stringProperty());
         
            table.setItems(list); 
        }
        
        con.close();        
    }
        
}

    
    
}

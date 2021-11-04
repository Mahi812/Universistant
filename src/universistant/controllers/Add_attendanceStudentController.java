/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Add_attendanceStudentController implements Initializable {

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
    private Text Assign_TeacherText;
    @FXML
    private ComboBox<String> SelectCID;
    @FXML
    private ChoiceBox<String> SelectAttdstatus;
    @FXML
    private Button set;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Connection con;
        
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
    private void ClickCID(ActionEvent event) {
    }

    @FXML
    private void clickSet(ActionEvent event) {
    }
    
}

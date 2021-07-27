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

/**
 * FXML Controller class
 *
 * @author ocean
 */
public class DashboardController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickHome(ActionEvent event) {
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
    
}

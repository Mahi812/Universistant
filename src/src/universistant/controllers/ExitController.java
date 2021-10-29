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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ExitController implements Initializable {

    @FXML
    private Button YesButton;
    @FXML
    private Button NoButton;
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
    private void clickExitYes(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void clickExitNo(ActionEvent event) {
        Stage stage = (Stage) NoButton.getScene().getWindow();
        stage.close();
    }
    
}

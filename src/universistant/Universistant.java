/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universistant;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ocean
 */
public class Universistant extends Application{
    

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage stage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet.");
        Parent root = FXMLLoader.load(getClass().getResource("/resources/fxml/login.fxml"));
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    
}

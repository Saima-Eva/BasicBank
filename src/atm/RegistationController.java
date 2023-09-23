/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shashiki
 */
public class RegistationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField name1;
    @FXML
    private TextField password1;
    @FXML
    private TextField username1;
    @FXML
    private TextField amount1;
    
   private String varName;
   private String varPassword;
   private String varUsername;
   private Double varAmount;
   
   public LoginModel loginModel =new LoginModel();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

public void sginUpReg(ActionEvent event) throws SQLException{
    
    varName = name1.getText();
    varUsername = username1.getText();
    varPassword = password1.getText();
    String tempAmo = amount1.getText();
    varAmount = Double.parseDouble(tempAmo);
    Statement stms;
    try {
         
             stms = loginModel.connection.createStatement();
             String sql = "insert into login (username,password,balance,name) values ('"+varUsername+"','"+varPassword+"','"+varAmount+"','"+varName+"')";
             stms.executeUpdate(sql);
             stms.close();
             loginModel.connection.close();
             
             ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("FXMLDocument.fxml").openStream());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
             
             
    } catch (Exception e) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
        
    }
   
}
    
    


}
    


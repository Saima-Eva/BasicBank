/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Shashiki
 */
public class FXMLDocumentController implements Initializable {
    
  public LoginModel loginModel =new LoginModel();
  
  @FXML
  private Label isConnected;
  @FXML
  private TextField txtUser;
  @FXML
  private TextField txtPass;
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(loginModel.isDbConnected()){
        isConnected.setText("ATM Login");
        
        }else{
        isConnected.setText("Not Connected");
        }
        // TODO
    }

  public void Login(ActionEvent event) throws IOException{
      try {
            if(loginModel.isLogin(txtUser.getText(),txtPass.getText())){
                isConnected.setText("Username And Password is Correct");
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("UserFXML.fxml").openStream());
                UserFXMLController userFXMLController = (UserFXMLController)loader.getController();
                userFXMLController.GetUser(txtUser.getText());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("WELCOME TO ATM");
                stage.show();
  }else {
                if (txtUser.getText().toString().compareTo("") == 0 || txtPass.getText().toString().compareTo("") == 0) {
                    isConnected.setText("Please Enter Your Username & Password");
                }else{
                    isConnected.setText("Username And Password is NOT Correct");
                }           
            }
      } catch (SQLException e) {
          isConnected.setText("Username And PasCorrect");
          e.printStackTrace();
          
      }

  }
  public void sginUp(ActionEvent event) throws IOException{
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("Registation.fxml").openStream());
              //  UserFXMLController userFXMLController = (UserFXMLController)loader.getController();
             //   userFXMLController.GetUser(txtUser.getText());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Registaion");
                stage.setResizable(false);
                stage.show();
  }
  
    
}

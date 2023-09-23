/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shashiki
 */
public class UserFXMLController implements Initializable {
    
      

    @FXML
    private Label userLb;
    @FXML
    private Label baLabel;
    @FXML
    private TextField txtBal;
    
    private double bal=0;
    private double tempBal;
    private String tempString;
    private String userString;
    public LoginModel loginModel =new LoginModel();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(loginModel.isDbConnected()){
           
       baLabel.setText("Welcome To ATM 2.0");
        
        }else{
        baLabel.setText("Not Connected");
        }
        // TODO
    }    
    public void GetUser(String user) {
        userString = user;
        userLb.setText("Welcome "+user);
        
        // TODO
    }
    
   
    public  void SginOut(ActionEvent event) throws IOException{
      ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("FXMLDocument.fxml").openStream());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                
                stage.show(); 
                
                
    }
    public void getData(){
        try {
              Statement stms;
             stms = loginModel.connection.createStatement();
             ResultSet rs =stms.executeQuery("select balance from login where username='"+userString+"'");
             bal=rs.getDouble("balance"); 
             rs.close();
             stms.close();
             
        } catch (Exception e) {
            System.err.println(e.getClass().getName()+":"+e.getMessage());
          
        }
  
    }
    
    public void dataUpdate(){
        try {
            Statement stms;
             stms = loginModel.connection.createStatement();
             String sqlString = "update login set balance="+bal+" where username='"+userString+"'";
             stms.executeUpdate(sqlString);
             stms.close();
             
        } catch (Exception e) {
            System.out.println(e.getClass().getName());
        }
    
    }
    
     public void Balance(){
         getData();
         String temp = Double.toString(bal);
          baLabel.setText("Your Balance Is Rs."+temp);
          dataUpdate();
    }
    public void Withdraw(){
        getData();
        
            if(txtBal.getText().toString().compareTo("")==0){
                baLabel.setText("Please Enter Your Amount");
            }else{
            tempString = txtBal.getText();
         tempBal = Double.parseDouble(tempString);
          
         if (bal<=0) {
             baLabel.setText("Your Account is Empty");
            
        }else{
             bal=bal-tempBal;
             String temp = Double.toString(bal);
             baLabel.setText("Your Balance Is Rs."+temp);
             txtBal.clear();
          
         }
            }
   dataUpdate();
    
    }
    public void Deposite(){
        getData();
        if(txtBal.getText().toString().compareTo("")==0){
        baLabel.setText("Please Enter Your Amount");
        }else{
        tempString = txtBal.getText();
        tempBal = Double.parseDouble(tempString);
         bal=bal+tempBal;
         String temp = Double.toString(bal);
         baLabel.setText("Your Balance Is Rs."+temp);
         txtBal.clear();
        }
       dataUpdate();
    }
}

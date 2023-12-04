package com.example.javafxloginvideo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_sign_up;

    @FXML
    private Button button_log_in;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private PasswordField pf_confirmpassword;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_contactno;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(! tf_username.getText().trim().isEmpty() && ! pf_password.getText().trim().isEmpty() && ! pf_confirmpassword.getText().trim().isEmpty() && ! tf_email.getText().trim().isEmpty() && ! tf_contactno.getText().trim().isEmpty()){
                    DBUtils.signUpUser(event,tf_username.getText(),pf_password.getText(),pf_confirmpassword.getText(),tf_email.getText(), Integer.parseInt(tf_contactno.getText()));

                }else{
                    System.out.println("Please fill in all information!");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml","Log in!",null);
            }
        });


    }
}

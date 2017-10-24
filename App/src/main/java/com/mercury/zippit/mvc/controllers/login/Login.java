package com.mercury.zippit.mvc.controllers.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/10/17
 */
public class Login implements Initializable {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private CheckBox remember;

	@FXML
	private Button login, register, settings;

	@FXML
	private Text recovery;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//TODO
	}

}

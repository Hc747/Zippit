package com.mercury.zippit.mvc.controllers.login;

import com.mercury.zippit.configuration.Version;
import com.mercury.zippit.net.codec.service.LoginServiceRequest;
import com.mercury.zippit.net.codec.service.RegistrationServiceRequest;
import io.netty.channel.Channel;
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
public class LoginController implements Initializable {

	private Version version;
	private Channel channel;//TODO: use proper data structure

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

	@FXML
	private void login() {
		login.setDisable(true);

		channel.writeAndFlush(new LoginServiceRequest(version, username.getText(), password.getText())).addListener(y -> login.setDisable(false));
	}

	@FXML
	private void register() {
		register.setDisable(true);

		channel.writeAndFlush(new RegistrationServiceRequest(version, username.getText(), password.getText())).addListener(y -> register.setDisable(false));
	}

	public void init(Version version, Channel channel) { //TODO: temp
		this.version = version;
		this.channel = channel;
	}

}

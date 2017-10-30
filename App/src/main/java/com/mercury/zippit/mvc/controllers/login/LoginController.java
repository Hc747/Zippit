package com.mercury.zippit.mvc.controllers.login;

import com.mercury.zippit.configuration.Version;
import com.mercury.zippit.net.codec.handshake.HandshakeRequest;
import com.mercury.zippit.net.codec.handshake.HandshakeRequestEndpoint;
import com.mercury.zippit.net.codec.login.LoginRequest;
import com.mercury.zippit.net.codec.registration.RegistrationRequest;
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

		HandshakeRequest handshake = new HandshakeRequest(version, HandshakeRequestEndpoint.LOGIN);
		channel.writeAndFlush(handshake);
		try { Thread.sleep(5000); } catch (Exception e) {
			e.printStackTrace();
		}
		LoginRequest request = new LoginRequest(username.getText(), password.getText());
		channel.writeAndFlush(request).addListener(y -> login.setDisable(false));
	}

	@FXML
	private void register() {
		register.setDisable(true);

		HandshakeRequest handshake = new HandshakeRequest(version, HandshakeRequestEndpoint.REGISTRATION);
		channel.writeAndFlush(handshake);
		try { Thread.sleep(5000); } catch (Exception e) {
			e.printStackTrace();
		}
		RegistrationRequest request = new RegistrationRequest(username.getText(), password.getText());
		channel.writeAndFlush(request).addListener(y -> register.setDisable(false));
	}

	public void init(Version version, Channel channel) { //TODO: temp
		this.version = version;
		this.channel = channel;
	}

}

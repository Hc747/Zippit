package com.mercury.zippit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/10/17
 */
public final class Zippit extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		show(Zippit.class.getResource("mvc/views/login/Login.fxml"), stage, "Mercury", false);

		show(Zippit.class.getResource("mvc/views/auth/TwoFactorAuth.fxml"), new Stage(), "2FA", false);
	}

	@Override
	public void stop() {
		//TODO
	}

	public static void launch(String... args) {
		Application.launch(Zippit.class, args);
	}

	public static Parent show(URL location, Stage stage, String title, boolean resizable) throws IOException {
		Parent root = FXMLLoader.load(location);

		stage.setTitle(title);
		stage.setResizable(resizable);

		Scene view = new Scene(root);
		stage.setScene(view);

		stage.show();

		return root;
	}

}

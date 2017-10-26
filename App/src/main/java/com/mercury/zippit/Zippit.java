package com.mercury.zippit;

import com.mercury.zippit.mvc.controllers.login.LoginController;
import com.mercury.zippit.net.ZippitChannelInitialiser;
import com.mercury.zippit.net.ZippitHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 18/10/17
 */
public final class Zippit extends Application {

	private final Bootstrap bootstrap = new Bootstrap();
	private final EventLoopGroup group = new NioEventLoopGroup();

	private Channel channel;

	@Override
	public void init() {
		bootstrap.group(group);
		bootstrap.channel(NioSocketChannel.class);

		bootstrap.handler(new ZippitChannelInitialiser(new ZippitHandler()));
		bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
		bootstrap.option(ChannelOption.TCP_NODELAY, true);

		ChannelFuture connection = bootstrap.connect("127.0.0.1", 43595).syncUninterruptibly();

		channel = connection.channel();
		//TODO: use constants
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Zippit.class.getResource("mvc/views/login/Login.fxml"));

		Parent root = loader.load();

		stage.setTitle("Mercury");
		stage.setResizable(false);

		Scene view = new Scene(root);
		stage.setScene(view);

		LoginController controller = loader.getController();
		controller.initChannel(channel);

		stage.show();
	}

	@Override
	public void stop() {
		group.shutdownGracefully();
	}

	public static void launch(String... args) {
		Application.launch(Zippit.class, args);
	}

}

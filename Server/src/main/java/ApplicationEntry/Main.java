package ApplicationEntry;

import com.mercury.zippit.Zippit;
import com.mercury.zippit.configuration.ZippitConfiguration;
import com.mercury.zippit.configuration.ZippitConfigurationBuilder;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class Main {

	private Main() {}

	public static void main(String[] args) {
		ZippitConfigurationBuilder builder = ZippitConfiguration.builder();

		//TODO: dynamic population of com.mercury.zippit.configuration

		ZippitConfiguration configuration = builder.create();

		new Zippit(configuration).run();
	}

}

package ApplicationEntry;

import com.mercury.zippit.Zippit;
import com.mercury.zippit.configuration.ZippitConfiguration;

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
public final class Main {

	private Main() {}

	public static void main(String[] args) {
		ZippitConfiguration.ZippitConfigurationBuilder builder = ZippitConfiguration.builder();

		ZippitConfiguration configuration = builder.build();

		Zippit zippit = new Zippit(configuration);
	}

}

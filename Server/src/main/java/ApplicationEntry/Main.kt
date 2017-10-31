package ApplicationEntry

import com.mercury.zippit.Zippit
import com.mercury.zippit.configuration.Datasource
import com.mercury.zippit.configuration.ZippitConfiguration

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 24/10/17
 */
object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val builder = ZippitConfiguration.builder()

        //TODO: use constants
        builder.datasource(Datasource("./Server/config/database/mercury.properties"))

        val configuration = builder.create()

        Zippit(configuration).run()
    }

}

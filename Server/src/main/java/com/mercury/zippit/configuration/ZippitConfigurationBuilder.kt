package com.mercury.zippit.configuration

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 27/10/17
 */
data class Version(val major: Int, val minor: Int)

data class ZippitConfiguration internal constructor(val port: Int, val version: Version) {
    companion object {

        @JvmStatic
        fun builder(): ZippitConfigurationBuilder {
            return ZippitConfigurationBuilder()
        }

    }
}

class ZippitConfigurationBuilder internal constructor() {

    private var port = ZippitConstants.DEFAULT_PORT
    private var version = Version(ZippitConstants.VERSION_MAJOR, ZippitConstants.VERSION_MINOR)

    fun port(port: Int): ZippitConfigurationBuilder {
        this.port = port
        return this
    }

    fun version(version: Version): ZippitConfigurationBuilder {
        this.version = version
        return this
    }

    fun create(): ZippitConfiguration {
        return ZippitConfiguration(port, version)
    }

}
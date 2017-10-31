package com.mercury.zippit.persistence.sql

import com.mercury.zippit.configuration.Datasource
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.ResultSet

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 31/10/17
 */
class Database(datasource: Datasource) : AutoCloseable {

    private val source = HikariDataSource(HikariConfig(datasource.propertiesLocation))

    suspend fun getConnection(): Connection = source.getConnectionAsync().await()

    suspend fun <T> executeAndTransform(query: String, transform: (ResultSet) -> T?): T? {
        getConnection().use {
            it.getStatement().use {
                it.getResults(query).use {
                    return transform(it)
                }
            }
        }
    }

    override fun close() = source.close()

}






/*
companion object {

    @JvmStatic
    fun main(args: Array<String>) {
        val builder = ZippitConfiguration.builder()

        builder.datasource(Datasource("./Server/config/database/mercury.properties"))

        val configuration = builder.create()

        val database = Database(configuration.datasource!!)

        launch {

            println("Connecting...")

            val connection = database.getConnection()

            println("...Connected")

            connection.use {

                println("Creating statement...")

                val statement = connection.getStatement()

                println("...Statement created")

                println("Executing query...")

                val results: ResultSet = statement.getResults("SELECT * FROM users")

                println("...Query executed")

                results.use {

                    while (results.next())
                        println("id: ${results.getInt(1)}$ username: ${results.getString(2)} password: ${results.getString(3)}")

                }


            }
        }


        println("Sleeping...")
        Thread.sleep(10_000)

        database.close()

        println("Goodbye!")
    }

}*/
package com.mercury.zippit.persistence.sql

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.experimental.async
import java.sql.Connection
import java.sql.Statement

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 31/10/17
 */
internal fun HikariDataSource.getConnectionAsync() = async {
    connection
}

private fun Connection.createStatementAsync() = async {
    createStatement()
}

suspend fun Connection.getStatement() = createStatementAsync().await()

private fun Statement.executeQueryAsync(query: String) = async {
    executeQuery(query)
}

suspend fun Statement.getResults(query: String) = executeQueryAsync(query).await()
package com.mercury.zippit.persistence.sql

import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.experimental.async
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Statement

/**
 * @author Harrison, Alias: Hc747, Contact: harrisoncole05@gmail.com
 * @version 1.0
 * @since 31/10/17
 */
internal fun HikariDataSource.getConnectionAsync() = async { connection }

private fun Connection.createStatementAsync() = async { createStatement() }

private fun Connection.createPreparedStatementAsync(query: String) = async { prepareStatement(query) }

private fun Statement.executeQueryAsync(query: String) = async { executeQuery(query) }

private fun PreparedStatement.executeQueryAsync() = async { executeQuery() }

suspend fun Connection.getStatement() = createStatementAsync().await()

suspend fun Connection.getPreparedStatement(query: String) = createPreparedStatementAsync(query).await()

suspend fun Statement.getResults(query: String) = executeQueryAsync(query).await()

suspend fun PreparedStatement.getResults() = executeQueryAsync().await()
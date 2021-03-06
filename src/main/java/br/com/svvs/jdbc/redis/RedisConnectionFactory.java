package br.com.svvs.jdbc.redis;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class RedisConnectionFactory {

	public static Connection getConnection(String host, int port,
			int dbnb, Properties info) throws SQLException {
		
		//TODO: Add support for others RedisIOs
		RedisIO io;
		try {
			io = new RedisSocketIO(host, port);
		} catch (UnknownHostException e) {
			throw new SQLException("Can't find host: " + host);
		} catch (IOException e) {
			throw new SQLException("Couldn't connect ("+ e.getMessage() + ")");
		}

		//设置当前DB
		Connection conn = new RedisConnection(io, info);
		Statement statement = conn.createStatement();
		statement.execute(RedisCommand.SELECT.toString() + " " + dbnb);

		return conn;
	}

}

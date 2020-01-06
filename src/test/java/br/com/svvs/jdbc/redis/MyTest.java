package br.com.svvs.jdbc.redis;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author freezesoul
 * @date 2020/1/6
 * <p>
 * MyTest
 */
public class MyTest {

    @Test
    public void get() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:redis://localhost");
        Statement statement = connection.createStatement();

        statement.execute("SET my_first_key my_first_value");
        statement.execute("GET my_first_key");

        ResultSet r = statement.getResultSet();
        while (r.next()) {
            System.out.println(">" + r.getString(0) + "<");
        }

        QueryRunner run = new QueryRunner();


        List<String> result = run.query(connection, "GET my_first_key",new ColumnListHandler<String>());
        System.out.print(result);
        connection.commit();
        connection.close();
    }
}

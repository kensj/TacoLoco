/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tacoloco.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBInitializeConfig {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize(){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS TacoMenu");
            statement.executeUpdate(
                "CREATE TABLE TacoMenu(" +
                "id INTEGER Primary key, " +
                "name varchar(30) not null," +
                "price DOUBLE not null,"
                );
            statement.executeUpdate(
                "INSERT INTO TacoMenu " +
                "(name,price) " +
                "VALUES " + "('Veggie Taco',2.50)"
                );
            statement.executeUpdate(
                "INSERT INTO TacoMenu " +
                "(name,price) " +
                "VALUES " + "('Chicken Taco',3.00)"
                );
            statement.executeUpdate(
                "INSERT INTO TacoMenu " +
                "(name,price) " +
                "VALUES " + "('Beef Taco',3.00)"
                );
            statement.executeUpdate(
                "INSERT INTO TacoMenu " +
                "(name,price) " +
                "VALUES " + "('Chorizo Taco',3.50)"
                );
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
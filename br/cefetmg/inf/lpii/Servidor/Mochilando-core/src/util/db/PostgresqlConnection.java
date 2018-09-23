/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Juliana
 */
public class PostgresqlConnection extends DadosAutenticarConexao implements ConnectionFactory {
    
   private final static String bdDriver = "org.postgresql.Driver";

    public PostgresqlConnection() {
    }
    
    @Override
    public Connection getConexao() throws ClassNotFoundException, SQLException {
        Class.forName(bdDriver);
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceConnection implements ConnectionFactory {

    private final static String dsName = "jdbc/Mochilando";

    private DataSource ds;
    
    public DataSourceConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("");
            this.ds = (DataSource) envContext.lookup(dsName);
        } catch (NamingException ex) {
            Logger.getLogger(DataSourceConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Connection getConexao() throws ClassNotFoundException, SQLException {
        return ds.getConnection();
    }
}

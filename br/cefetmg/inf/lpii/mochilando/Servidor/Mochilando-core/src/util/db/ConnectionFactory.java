/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Juliana
 */
public interface ConnectionFactory {
    
    public Connection getConexao() throws ClassNotFoundException, SQLException;
}

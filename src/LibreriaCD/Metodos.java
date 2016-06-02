/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibreriaCD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author propa
 */
public class Metodos {

    private Connection connection = null;
    private ResultSet rs = null;

    /**
     * conecta con la base, con el driver y parámetros
     *
     * @param url recoge el nombre de la base
     * @param user recoge el nombre de usuario
     * @param pass recoge la contraseña del usuario
     */
    public void conectar(String url, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/" + url, user, pass);
            System.out.println("conectado");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * mete en la tabla los valores dados por el usuario
     *
     * @param tabla recoge el nombre de la tabla
     * @param valores recoge los valores dados a la tabla
     */
    public void insert(String tabla, String valores) {
        PreparedStatement i;
        try {
            i = connection.prepareStatement("insert into " + tabla + " values(" + valores + ")");
            i.execute();
            System.out.println("insertado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String tabla, int primaryKey) {
        PreparedStatement d;
        try {
            d = connection.prepareStatement("delete from " + tabla + " where id=" + primaryKey);
            d.execute();
            System.out.println("eliminado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * borra de la tabla filas, según la clave primaria
     *
     * @param tabla recoge el nombre de la tabla
     * @param primaryKey recoge el numero de la fila
     */
    public void update(String tabla, String campo, String dato, int primaryKey) {
        PreparedStatement u;
        try {
            u = connection.prepareStatement("update " + tabla + " set " + campo + "=" + dato + " where .id=" + primaryKey);
            u.execute();
            System.out.println("actualizado");
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet select(String tabla, String campos) {
        Statement s;
        try {
            s = connection.createStatement();
            rs = s.executeQuery("select " + campos + " from " + tabla);
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void apagar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

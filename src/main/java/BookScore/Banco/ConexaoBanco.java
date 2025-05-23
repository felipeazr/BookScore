/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookScore.Banco;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco {
    private static String usuario = "root";
    private static String senha = "0000";
    private static String host = "127.0.0.1";
    private static String porta = "3306";
    private static String bd = "bookscore";
    
    public static Connection obtemConexao(){
        try {
            Connection c = DriverManager.getConnection(
            "jdbc:mysql://" + host + ":" + porta + "/" + bd + "?user=" + usuario + "&password=" + senha + "&useTimezone=true&serverTimezone=America/Sao_Paulo");
            return c;
        }
        catch (Exception e) {
            e.printStackTrace();
            return (Connection) e;
        }
    }
}


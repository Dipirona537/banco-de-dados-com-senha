package sqlite_db;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Conexao {
    public static Connection getConexao(boolean teste) {
        try {
            File f = new File(Main.local);
            if (f.exists())
            {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:"+Main.local);
                if (teste) System.out.println("Conectado ao banco de dados!");
                return conn;
            }else{
                System.out.println("Arquivo de banco de dados nao encontrado!");
                try {
                    Main.main(null);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao conectar ao banco de dados");
            throw new RuntimeException(ex);
        }
        return null;
    }
}

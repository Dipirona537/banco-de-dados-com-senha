package sqlite_db;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static boolean executado = true;
    public static String local = "";
    public static final String padrao = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\db_sqlite\\pessoa";
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        boolean sair = false;
        if (local.equals(""))
        {
            System.out.println("Digite o local onde esta o banco de dados (use \\\\ em vez de \\)\nDigite padrao para = C:\\Users\\"+System.getProperty("user.name")+"\\db_sqlite\\pessoa");
            local = sc.nextLine();
        }
        if (local.equals("padrao"))
        {
            local = padrao;
        }
        if (Conexao.getConexao(executado) != null){
            while (!sair)
            {
                System.out.println("listar, inserir, retornar, login, deletar, alterar, sair");
                String escolha = sc.nextLine();
                escolha = escolha.toLowerCase();
                switch (escolha)
                {
                    case "listar":
                        Operacoesdb.listar();
                        break;
                    case "inserir":
                        Operacoesdb.inserir();
                        break;
                    case "retornar":
                        Operacoesdb.retornar();
                        break;
                    case "login":
                        Operacoesdb.login();
                        break;
                    case "deletar":
                        Operacoesdb.deletar();
                        break;
                    case "alterar":
                        Operacoesdb.alterar();
                        break;
                    case "sair":
                        sair = true;
                        break;
                    default:
                        executado = false;
                        main(null);
                }
            }
        }
    }
}

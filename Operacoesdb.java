package sqlite_db;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Operacoesdb {

    public static void login() throws SQLException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pessoa;");
        System.out.println("Digite seu usuario");
        String user = sc.nextLine();
        System.out.println("Digite sua senha");
        String senha = sc.nextLine();
        boolean teste = false;
        while (rs.next())
        {
            String nome = rs.getString("nome");
            String senha_hash = rs.getString("senha_hash");
            String salt = rs.getString("salt");
            if (user.equals(nome) && Hash.comp_hash(senha,senha_hash, salt))
            {
                System.out.println("Voce esta logado!");
                /*int idade = rs.getInt("idade");
                String senha_h = rs.getString("senha_hash");
                String salt = rs.getString("salt");
                System.out.println(nome+"\n"+idade+"\n"+senha_h+"\n"+salt);
                */
                teste = true;
                break;
            }
        }
        if (!teste) {
            System.out.println("Usuario ou senha invalido!");
            login();
        }
        st.close();
    }

    public static void deletar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        System.out.println("Digite o nome da pessoa a ser deletada");
        String pesquisa = sc.nextLine();
        st.execute("DELETE FROM pessoa WHERE nome='"+pesquisa+"'");
        st.close();
    }

    public static void alterar() throws SQLException {
        boolean teste = false;
        Scanner sc = new Scanner(System.in);
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        System.out.println("Digite o nome q deseja alterar");
        String pesquisa = sc.nextLine();
        System.out.println("Digite o novo nome");
        String novo = sc.nextLine();
        ResultSet rs = st.executeQuery("SELECT nome FROM pessoa");
        while (rs.next())
        {
            String nome = rs.getString("nome");
            if (pesquisa.equals(nome))
            {
                teste = true;
                st.execute("UPDATE pessoa SET nome = '"+novo+"' WHERE nome = '"+pesquisa+"'");
                break;
            }
        }
        if (!teste) System.out.println("Nome nao encontrado!");
        st.close();
    }

    public static void retornar() throws SQLException {
        Scanner sc = new Scanner(System.in);
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pessoa;");
        System.out.println("Digite um nome para pesquisar sua idade");
        String pesquisa = sc.nextLine();
        boolean teste = false;
        while (rs.next())
        {
            String nome = rs.getString("nome");
            if (pesquisa.equals(nome))
            {
                int idade = rs.getInt("idade");
                String senha_h = rs.getString("senha_hash");
                String salt = rs.getString("salt");
                System.out.println(nome+"\n"+idade+"\n"+senha_h+"\n"+salt);
                teste = true;
                break;
            }
        }
        if (!teste) System.out.println("Nome não encontrado");
        st.close();
    }

    public static void listar() throws SQLException {
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM pessoa;");
        while (rs.next())
        {
            int idade = rs.getInt("idade");
            String nome = rs.getString("nome");
            String senha_h = rs.getString("senha_hash");
            String salt = rs.getString("salt");
            System.out.println(nome+"\n"+idade+"\n"+senha_h+"\n"+salt);
        }
        st.close();
    }

    public static String ler(String str)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(str);
        return sc.nextLine();
    }

    public static void inserir() throws SQLException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        Statement st = Objects.requireNonNull(Conexao.getConexao(false)).createStatement();
        String saida;
        while (true)
        {
            System.out.println("Sair?");
            saida = sc.nextLine();
            if (saida.equalsIgnoreCase("s")) break;
            String sql = " INSERT INTO pessoa (nome, idade, senha_hash, salt) VALUES ('"+ler("Digite o nome da pessoa")+"','"+ler("Digite a idade da pessoa")+"','"+Hash.hash(Hash.senha(), "")+"','"+Hash.fsalt+"')";
            st.execute(sql);
        }
        st.close();
    }
}

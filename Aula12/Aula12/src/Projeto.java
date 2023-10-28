
import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Projeto
{
    public static void listarAutor()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors");
            System.out.println("------------ LISTAR AUTOR ------------\n");
            while (rs.next()) {
                System.out.println("Código autor: " + rs.getInt("authorsID"));
                System.out.println("Nome autor: " + rs.getString("firstName"));
                System.out.println("Sobrenome autor: " + rs.getString("lastName"));
                System.out.println("______________________________________");
            }
        }catch (SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
            // ex.setStackTrace();
        }
    }

    public static void listarTitulos()
    {
        //titulos
        Connection conne = null;
        Statement stm = null;
        ResultSet ts = null;
        try
        {
            conne = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stm = conne.createStatement();
            ts = stm.executeQuery("SELECT * FROM Titles");
            System.out.println("------------ LISTAR TITULO ------------\n");
            while (ts.next()) {
                System.out.println("ISBN do livro: " + ts.getInt("ISBN"));
                System.out.println("Título do livro: " + ts.getString("title"));
                System.out.println("Número da edição: " + ts.getString("EditionNumber"));
                System.out.println("Copyright: " + ts.getString("copyright"));
                System.out.println("");
            }

        }catch (SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
            // ex.setStackTrace();
        }

    }

    public static void cadastrarAutor()
    {
        //autores
        Connection conn = null;
        Statement stmt = null;
     //   ResultSet rs = null;
        PreparedStatement ps;
        //SCANER
        Scanner teclado = new Scanner(System.in);

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM authors");

            System.out.println("------------ CADASTRAR AUTOR ------------");
            System.out.println("\nDigite o primeiro nome do autor: ");
            String nome = teclado.next();
            System.out.println("Digite o sobrenome do autor:");
            String sobrenome = teclado.next();

            int retorno;
            ps = conn.prepareStatement("INSERT INTO authors(firstName, lastName) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,nome);
            ps.setString(2,sobrenome);
            retorno = ps.executeUpdate();
            if(retorno > 0)
            {
                System.out.println("\nNovo registro realizado!" + nome + " - " + sobrenome);
            }
            else
            {
                System.out.println("\nErro ao registrar!");
            }

        }catch(SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
        }
    }

    public static void cadastrarTitulo()
    {
        //titulos
        Connection conne = null;
        Statement stm = null;
        ResultSet ts = null;
        PreparedStatement ps = null;
        //SCANER
        Scanner teclado = new Scanner(System.in);

        try
        {
            conne = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stm = conne.createStatement();
            ts = stm.executeQuery("SELECT * FROM Titles");

            System.out.println("------------ CADASTRAR TITULO ------------");
            System.out.println("\nDigite o código ISBN a ser cadastrado: ");
            int isbn = teclado.nextInt();
            System.out.println("Digite o nome do Título: ");
            String nomeT = teclado.next();
            System.out.println("Digite o número da edição: ");
            int edNum = teclado.nextInt();
            System.out.println("Digite o copyright: ");
            String copyR = teclado.next();

            int retorno;
            ps = conne.prepareStatement("INSERT INTO Titles(ISBN, title, EditionNumber, copyright) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, isbn);
            ps.setString(2,nomeT);
            ps.setInt(3,edNum);
            ps.setString(4,copyR);
            retorno = ps.executeUpdate();
            if(retorno > 0)
            {
                System.out.println("\nNovo registro realizado!" + isbn + " - " + nomeT + " - " + edNum + " - " + copyR);
            }
            else
            {
                System.out.println("\nErro ao registrar!");
            }


        }catch(SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
        }

    }

    public static void alterarAutor()
    {
        //autores
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner teclado = new Scanner(System.in);
        PreparedStatement ps = null;
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors");
            // Alterar autores
            System.out.println("\n------------ ALTERAR AUTOR ------------\n");
            System.out.println("Digite o código do autor que deseja alterar: ");
            int codigo = teclado.nextInt();
            System.out.println("Digite o nome para qual vai ser alterado: ");
            String nome = teclado.next();

            // Utilizando PrepareStatement
            int retorno;
            ps = conn.prepareStatement("UPDATE authors SET firstName = ? WHERE authorsID = ?");
            ps.setString(1, nome);
            ps.setInt(2, codigo);
            retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Novo registro:" + codigo + " - " + nome);
            } else {
                System.out.println("Erro ao registrar!");
            }

        }catch (SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
            // ex.setStackTrace();
        }

    }

    public static void alterarTitulo()
    {
        //titulos
        Connection conne = null;
        Statement stm = null;
        ResultSet ts = null;

        //SCANER
        Scanner teclado = new Scanner(System.in);
        PreparedStatement ps = null;

        try
        {
            conne = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stm = conne.createStatement();
            ts = stm.executeQuery("SELECT * FROM Titles");

            // Alterar autores
            System.out.println("\n------------ ALTERAR TITULOS ------------\n");
            System.out.println("Digite o código ISBN do titulo que deseja alterar: ");
            int codi = teclado.nextInt();
            System.out.println("Digite o nome do titulo para qual vai ser alterado: ");
            String nome = teclado.next();
            System.out.println("Deseja alterar o numero edição também? \n 1 - sim | 2 - nao");
            int res = teclado.nextInt();
            if(res == 1)
            {
                System.out.println("Digite o numero da edicao: ");
                int edi = teclado.nextInt();
                ps = conne.prepareStatement("UPDATE Titles SET EditionNumber = ? WHERE ISBN = ?");
                ps.setInt(1, edi);
                ps.setInt(2, codi);
            }
            else
            {
                System.out.println("Ok!");
            }
            System.out.println("Deseja alterar o copyright? \n 1 - sim | 2 - nao");
            int copy = teclado.nextInt();
            if(copy == 1)
            {
                System.out.println("Digite o copyright: ");
                String right = teclado.next();
                ps = conne.prepareStatement("UPDATE Titles SET copyright = ? WHERE ISBN = ?");
                ps.setString(1, right);
                ps.setInt(2, codi);

            }
            else
            {
                System.out.println("Ok!");
            }

            // Utilizando PrepareStatement
            int retorno;
            ps = conne.prepareStatement("UPDATE Titles SET title = ? WHERE ISBN = ?");
            ps.setString(1, nome);
            ps.setInt(2, codi);
            retorno = ps.executeUpdate();


            if (retorno > 0) {
                System.out.println("Novo registro:" + codi + " - " + nome);
            } else {
                System.out.println("Deu ruim!");
            }

        }catch(SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
        }
    }

    public static void deletarAutor()
    {
        //autores
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //SCANER
        Scanner teclado = new Scanner(System.in);
        PreparedStatement ps = null;

        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM authors");


            // Deletar autores
            int codigo = teclado.nextInt();
            int retorno;


            System.out.println("\n------------ DELETAR AUTOR ------------\n");
            rs = stmt.executeQuery("SELECT * FROM authors");
            listarAutor();
            System.out.println("Digite o código que deseja excluir: ");
            codigo = teclado.nextInt();

            ps = conn.prepareStatement("DELETE FROM authors WHERE authorsID = ?");
            ps.setInt(1, codigo);
            retorno = ps.executeUpdate();

            if (retorno > 0)
            {
                System.out.println("Deu certo!");
            } else
            {
                System.out.println("Falhou!");
            }

        }catch (SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
            // ex.setStackTrace();
        }
    }

    public static void deletarTitulo()
    {
        //titulos
        Connection conne = null;
        Statement stm = null;
        ResultSet ts = null;
        PreparedStatement ps = null;
        //SCANER
        Scanner teclado = new Scanner(System.in);
        try
        {
            conne = DriverManager.getConnection("jdbc:mysql://localhost/authors",
                    "root",
                    "adsl1357");
            stm = conne.createStatement();
            ts = stm.executeQuery("SELECT * FROM Titles");
            int codigo = teclado.nextInt();
            int retorno;

            System.out.println("\n------------ DELETAR TITULO ------------");

            ts = stm.executeQuery("SELECT * FROM Titles");
            listarTitulos();
            System.out.println("Digite o código ISBN do titulo que deseja excluir: ");
            codigo = teclado.nextInt();

            ps = conne.prepareStatement("DELETE FROM Titles WHERE ISBN = ?");
            ps.setInt(1, codigo);
            retorno = ps.executeUpdate();

            if (retorno > 0)
            {
                System.out.println("Deu certo!");
            } else
            {
                System.out.println("Falhou!");
            }

        }catch(SQLException ex)
        {
            System.out.println("Erro ao conectar com o banco de dados!" + ex);
            // ex.setStackTrace();
        }

    }

    public static void main(String[] args) {
        //SCANER
        Scanner teclado = new Scanner(System.in);

        int cod;
        do {
            System.out.println("----------------Menu----------------");
            System.out.println("1 - Mostrar lista de autores");
            System.out.println("2 - Mostrar lista de livros");
            System.out.println("3 - Cadastrar autor");
            System.out.println("4 - Cadastrar livro");
            System.out.println("5 - Alterar cadastro autor");
            System.out.println("6 - Alterar cadastro livro");
            System.out.println("7 - Excluir autor");
            System.out.println("8 - Excluir livro");
            System.out.println("0 - Sair");
            cod = teclado.nextInt();
            switch (cod) {
                case 0 -> {
                    System.out.println("Ate breve!");
                    break;
                }
                case 1 -> {
                    listarAutor();
                    break;
                }
                case 2 -> {
                    listarTitulos();
                    break;
                }
                case 3 -> {
                    cadastrarAutor();
                    break;
                }
                case 4 -> {
                    cadastrarTitulo();
                    break;
                }
                case 5 -> {
                    alterarAutor();
                    break;
                }
                case 6 -> {
                    alterarTitulo();
                    break;
                }
                case 7 -> {
                    deletarAutor();
                    break;
                }
                case 8 -> {
                    deletarTitulo();
                    break;
                }
                default -> {
                    System.out.println("Codigo invalido!");
                    break;
                }
            }

        } while (cod != 0);
    }
}

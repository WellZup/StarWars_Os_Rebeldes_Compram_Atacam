package service;

import connection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddRebelde {
    static Statement statement;
    static Connection connection = Conexao.getConnection();

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void addRebelde () {
        Scanner entrada = new Scanner(System.in);
        String nome;
        String genero;
        String localizacao;
        int idade;

        System.out.println("Nome: ");
        nome = entrada.nextLine();
        System.out.println("Idade: ");
        idade = entrada.nextInt();
        entrada.nextLine(); // Usei para limpar cache que impedia a entrada do "Gênero"
        System.out.println("Gênero: ");
        genero = entrada.nextLine();
        System.out.println("Localização: ");
        localizacao = entrada.nextLine();


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into rebelde (nome, idade, genero, localizacao) values (?,?,?,?)");
                    preparedStatement.setString(1, nome);
                    preparedStatement.setInt(2, idade);
                    preparedStatement.setString(3, genero);
                    preparedStatement.setString(4, localizacao);

                    preparedStatement.executeUpdate();
            System.out.println("Rebelde add com sucesso");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar rebelde: " + e.getMessage());
        }
    }
}

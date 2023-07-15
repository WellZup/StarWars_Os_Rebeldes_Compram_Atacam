package service;

import connection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AtualizarLocalizacao {
        static Connection connection = Conexao.getConnection();
        static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizarLocalizacao () {
        Scanner entrada = new Scanner(System.in);
        String sql, novaLocalizacao;
        int id;

        System.out.print("ID do Rebelde: ");
        id = entrada.nextInt();
        System.out.print("Nova Localização: ");
        novaLocalizacao = entrada.next();

        try {
            Connection connection = Conexao.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE rebelde SET localizacao = ? WHERE id = ?");
            preparedStatement.setString(1, novaLocalizacao);
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Localização do rebelde atualizada com sucesso!");
            } else {
                System.out.println("Não foi encontrado nenhum rebelde com o ID informado.");
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar a localização do rebelde: " + e.getMessage());
        }
    }
}

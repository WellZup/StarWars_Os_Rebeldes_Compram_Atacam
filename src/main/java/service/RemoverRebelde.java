package service;

import connection.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoverRebelde {
    public static void removerRebelde () {
        Scanner entrada = new Scanner(System.in);
        int id;


        System.out.println("ID do rebelde para ser removido: ");
        id = entrada.nextInt();

        try {
            Connection connection = Conexao.getConnection();

            //Verificando exostencia de rebelde na lista
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM rebelde WHERE id = ?");
            selectStatement.setInt(1, id);

            //Se encontrado remover ou não
            if (selectStatement.executeQuery().next()) {
                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM rebelde WHERE id = ?");
                deleteStatement.setInt(1, id);
                deleteStatement.executeQuery().next();
                System.out.println("Rebelde removido da lista.");
                deleteStatement.close();
            } else {
                System.out.println("Rebelde não encontrado na base de dados.");
            }


        } catch (SQLException e) {
            System.out.println("Erro ao remover o rebelde: "+ e.getMessage());
        }
    }
}

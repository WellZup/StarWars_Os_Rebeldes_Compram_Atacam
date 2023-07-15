package service;

import connection.Conexao;

import java.sql.*;
import java.util.Scanner;

public class ReportarTraidor {
    static Connection connection = Conexao.getConnection();
    static void reportarTraidor() {
        int id;
        Scanner entrada = new Scanner(System.in);

        System.out.println("ID do Rebelde: ");
        id = entrada.nextInt();

        //Verificar existência do rebelde
        try {
            PreparedStatement selectStatment = connection.prepareStatement("SELECT * FROM rebelde WHERE id= ?");
            selectStatment.setInt(1, id);
            ResultSet resultSet = selectStatment.executeQuery();

            if (resultSet.next()) {
                int reportar = resultSet.getInt("reportar");
                reportar++;

                //Atualizar número de reportes
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE rebelde SET traidor = true WHERE id=?");
                updateStatement.setInt(1, reportar);
                updateStatement.setInt(1, id);
                updateStatement.executeUpdate();

                //Marcar rebelde como traidor
                if (reportar >= 3) {
                    PreparedStatement markTraidorStatement = connection.prepareStatement("UPDATE rebelde set traidor = true WHERE id=?");
                    markTraidorStatement.setInt(1, id);
                    markTraidorStatement.executeUpdate();
                    System.out.println("Rebelde marcado como traidor!");

                } else {
                    System.out.println("Traidor Reportado.");
                }
            } else {
                System.out.println("Nenhum rebelde com este ID foi encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao reportar o rebelde: " + e.getMessage());
        }
    }
}

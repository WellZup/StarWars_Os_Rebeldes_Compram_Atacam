package service;

import connection.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExibirPorcentagemTraidor {
    static Connection connection = Conexao.getConnection();

    static void exibirPorcentagemTraidor() {
        try {
            // Consulta para obter os rebeldes
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ROUND(COUNT(CASE WHEN traidor = TRUE THEN id END) " +
                    " * 100.0 / COUNT(*),2) AS traidor FROM rebelde");
            if (resultSet.next()) {
                double traidor = resultSet.getDouble("traidor");
                System.out.println("Porcentagem de traidores: " + traidor + "%");
            }
            // Fechando a conexão com o banco de dados
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Erro ao calcular a porcentagem de traidores: " + e.getMessage());
        }
    }
}

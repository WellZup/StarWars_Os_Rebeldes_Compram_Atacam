package service;

import connection.Conexao;
import model.Rebelde;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class ListarTodosRebeldes {

    public static void listarTodosRebeldes () {
        try {
            Connection connection = Conexao.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rebelde");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String genero = resultSet.getString("genero");
                String localizacao = resultSet.getString("localizacao");
                boolean traidor = resultSet.getBoolean("traidor");
                int reportar = resultSet.getInt("reportar");

                Rebelde rebelde = new Rebelde(id, nome, idade, genero, localizacao, traidor, reportar);


                System.out.println(rebelde.getId());
                System.out.println(rebelde.getNome());
                System.out.println(rebelde.getIdade());
                System.out.println(rebelde.getGenero());
                System.out.println(rebelde.getLocalizacao());
                System.out.println("Traidor? True or False? \n" + rebelde.isTraidor());
                System.out.println("==================================");


            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os rebeldes: " + e.getMessage());
        }

    }
}

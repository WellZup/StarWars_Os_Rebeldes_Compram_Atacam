package service;

import connection.Conexao;
import model.Rebelde;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseDeCompras {
    private static Rebelde rebelde;


    public static void realizarCompra () {

            Scanner entrada = new Scanner(System.in);

            // Exibir os recursos disponíveis para compra
            List<Recurso> recursosDisponiveis = listarRecursosDisponiveis();
            exibirRecursosDisponiveis(recursosDisponiveis);

            System.out.print("Selecione o ID do recurso que deseja comprar: ");
            int idRecurso = entrada.nextInt();
            entrada.nextLine(); // Limpar o buffer do scanner

            // Verificar se o recurso selecionado está disponível para compra
            Recurso recursoSelecionado = buscarRecursoPorId(recursosDisponiveis, idRecurso);
            if (recursoSelecionado == null) {
                System.out.println("Recurso não encontrado ou não disponível para compra.");
                return;
            }

            System.out.print("Quantidade desejada: ");
            int quantidade = entrada.nextInt();

            // Calcular o valor total da compra
            int valorTotal = recursoSelecionado.getValue() * quantidade;

            // Verificar se o rebelde é um traidor
            if (rebelde.isTraidor()) {
                System.out.println("Rebeldes traidores não podem realizar compras.");
                return;
            }

            // Verificar se o rebelde possui saldo suficiente
            if (rebelde.getReportar() < valorTotal) {
                System.out.println("Saldo insuficiente para realizar a compra.");
                return;
            }

            try {
                Connection connection = Conexao.getConnection();

                // Verificar se o recurso está disponível em quantidade suficiente
                if (verificarEstoqueSuficiente(connection, idRecurso, quantidade)) {
                    // Atualizar o saldo do rebelde
                    atualizarSaldoRebelde(connection, rebelde.getId(), valorTotal);

                    // Atualizar o estoque do recurso
                    atualizarEstoqueRecurso(connection, idRecurso, quantidade);

                    System.out.println("Compra realizada com sucesso!");
                } else {
                    System.out.println("Estoque insuficiente para o recurso selecionado.");
                }

                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao realizar a compra: " + e.getMessage());
            }
        }

        private static List<Recurso> listarRecursosDisponiveis() {
            List<Recurso> recursosDisponiveis = new ArrayList<>();

            // Adicionar os recursos disponíveis para compra
            recursosDisponiveis.add(new Recurso(1, "Arma", 100));
            recursosDisponiveis.add(new Recurso(2, "Munição", 30));
            recursosDisponiveis.add(new Recurso(3, "Água", 5));
            recursosDisponiveis.add(new Recurso(4, "Comida", 10));

            return recursosDisponiveis;
        }

        private static void exibirRecursosDisponiveis(List<Recurso> recursos) {
            System.out.println("--- Recursos Disponíveis ---");
            for (Recurso recurso : recursos) {
                System.out.println("ID: " + recurso.getId() +
                        ", Nome: " + recurso.getNome() +
                        ", Valor: " + recurso.getValue());
            }
            System.out.println();
        }

        private static Recurso buscarRecursoPorId(List<Recurso> recursos, int id) {
            for (Recurso recurso : recursos) {
                if (recurso.getId() == id) {
                    return recurso;
                }
            }
            return null;
        }

        private static boolean verificarEstoqueSuficiente(Connection connection, int idRecurso, int quantidade) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("SELECT quantidade FROM inventario WHERE recursos_id = ?");
            statement.setInt(1, idRecurso);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int estoqueAtual = resultSet.getInt("quantidade");
                return estoqueAtual >= quantidade;
            }

            return false;
        }

        private static void atualizarSaldoRebelde(Connection connection, int idRebelde, int valorTotal) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("UPDATE rebelde SET reportar = reportar - ? WHERE id = ?");
            statement.setInt(1, valorTotal);
            statement.setInt(2, idRebelde);
            statement.executeUpdate();
        }

        private static void atualizarEstoqueRecurso(Connection connection, int idRecurso, int quantidade) throws SQLException {
            PreparedStatement statement = connection.prepareStatement("UPDATE inventario SET quantidade = quantidade - ? WHERE recursos_id = ?");
            statement.setInt(1, quantidade);
            statement.setInt(2, idRecurso);
            statement.executeUpdate();
        }


}
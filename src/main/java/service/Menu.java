package service;

import connection.Conexao;
import model.Rebelde;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {

    static Connection connection = Conexao.getConnection();
    public static void exibirMenu() {
        Scanner entrada = new Scanner(System.in);

        while (true) {
            System.out.println("--- MENU ---");
            System.out.println("1. Adicionar Rebelde");
            System.out.println("2. Atualizar Localização do Rebelde");
            System.out.println("3. Reportar Rebelde como Traidor");
            System.out.println("4. Relatório: Porcentagem de Traidores");
            System.out.println("5. Relatório: Porcentagem de Rebeldes");
            System.out.println("6. Exibir todos rebeldes");
            System.out.println("7. Remover rebelde");
            System.out.println("8. Base de Compras \n" +"   Em construção!");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            int opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    AddRebelde.addRebelde();
                    break;
                case 2:
                    AtualizarLocalizacao.atualizarLocalizacao();
                    break;
                case 3:
                    ReportarTraidor.reportarTraidor();
                    break;
                case 4:
                    ExibirPorcentagemTraidor.exibirPorcentagemTraidor();
                    break;
                case 5:
                   ExibirPorcentagemRebelde.exibirPorcentagemRebelde();
                    break;
                case 6:
                    ListarTodosRebeldes.listarTodosRebeldes();
                    break;
                case 7:
                    RemoverRebelde.removerRebelde();
                    break;
                case 8:
                    BaseDeCompras.realizarCompra();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();
        }
    }
}

package EstacioFullStack_Mundo3_Nivel1_Procedimento2;

import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.PessoaFisica;
import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.PessoaJuridica;
import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.gerenciadores.PessoaFisicaRepo;
import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.gerenciadores.PessoaJuridicaRepo;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {

        PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();

        Scanner scanner = new Scanner(System.in);
        String opcaoSelecionada;

        do {
            System.out.println("==============================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==============================");

            opcaoSelecionada = scanner.next();

            switch (opcaoSelecionada) {

                // INSERIR PESSOA
                case "1":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Incluir Pessoa Física");
                        System.out.println("J - Incluir Pessoa Jurídica");
                        System.out.println("Q - Voltar ao Menu Principal");
                        System.out.println("==============================");

                        opcaoSelecionada = scanner.next();
                        scanner.nextLine();

                        switch (opcaoSelecionada.toUpperCase()) {

                            // Inserir pessoa fisica
                            case "F":
                                System.out.println("Insira o nome da pessoa física: ");
                                String nome = scanner.nextLine();
                                System.out.println("Insira o cpf da pessoa física: ");
                                String cpf = scanner.nextLine();
                                System.out.println("Insira a idade da pessoa física: ");
                                int idade = scanner.nextInt();

                                int pessoaFisicaTamanhoRepo = pessoaFisicaRepo.obterTodos().size();
                                int pessoaFisicaIdSerie = pessoaFisicaTamanhoRepo > 0 ? pessoaFisicaRepo.obterTodos()
                                        .get(pessoaFisicaTamanhoRepo - 1).getId() + 1 : 1;

                                PessoaFisica pessoaFisica = new PessoaFisica(pessoaFisicaIdSerie, nome, cpf, idade);

                                pessoaFisicaRepo.inserir(pessoaFisica);

                                System.out.println("Pessoa física inserida com sucesso!");
                                pessoaFisica.exibir();
                                break;

                            // Inserir pessoa juridica
                            case "J":
                                System.out.println("Insira o nome da pessoa juridica: ");
                                nome = scanner.nextLine();
                                System.out.println("Insira o cnpj da pessoa juridica: ");
                                String cnpj = scanner.nextLine();

                                int pessoaJuridicaTamanhoRepo = pessoaJuridicaRepo.obterTodos().size();
                                int pessoaJuridicaIdSerie = pessoaJuridicaTamanhoRepo > 0 ? pessoaJuridicaRepo.obterTodos()
                                        .get(pessoaJuridicaTamanhoRepo - 1).getId() + 1 : 1;

                                PessoaJuridica pessoaJuridica = new PessoaJuridica(pessoaJuridicaIdSerie, nome, cnpj);

                                pessoaJuridicaRepo.inserir(pessoaJuridica);

                                System.out.println("Pessoa juridica inserida com sucesso!");
                                pessoaJuridica.exibir();
                                break;

                            // Opcao invalida
                            default:
                                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                                break;
                        }

                        // Voltar para o menu principal
                    } while (!opcaoSelecionada.equalsIgnoreCase("Q"));
                    break;

                // ALTERAR PESSOA
                case "2":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Alterar pessoa física por ID");
                        System.out.println("J - Alterar pessoa juridica por ID");
                        System.out.println("Q - Voltar ao Menu Principal");
                        System.out.println("==============================");

                        opcaoSelecionada = scanner.next();
                        scanner.nextLine();

                        switch (opcaoSelecionada.toUpperCase()) {

                            // Alterando pessoa fisica
                            case "F":
                                System.out.println("Insira o ID da pessoa física: ");
                                int idPessoaFisica = scanner.nextInt();
                                scanner.nextLine();

                                PessoaFisica pessoaFisicaObtida = pessoaFisicaRepo.obter(idPessoaFisica);

                                if (pessoaFisicaObtida != null) {
                                    pessoaFisicaObtida.exibir();

                                    System.out.println("Nome atual da pessoa física: " + pessoaFisicaObtida.getNome());
                                    System.out.println("Insira um novo nome: ");
                                    String novoNome = scanner.nextLine();

                                    System.out.println("CPF atual da pessoa física: " + pessoaFisicaObtida.getCpf());
                                    System.out.println("Insira um novo CPF: ");
                                    String novoCPF = scanner.nextLine();

                                    System.out.println("Idade atual da pessoa física: " + pessoaFisicaObtida.getCpf());
                                    System.out.println("Insira uma nova Idade: ");
                                    int novaIdade = scanner.nextInt();

                                    pessoaFisicaRepo.alterar(pessoaFisicaObtida, novoNome, novoCPF, novaIdade);

                                    System.out.println("Pessoa fìsica alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa física com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Alterando pessoa juridica
                            case "J":
                                System.out.println("Insira o ID da pessoa juridica: ");
                                int idPessoaJuridica = scanner.nextInt();
                                scanner.nextLine();

                                PessoaJuridica pessoaJuridicaObtida = pessoaJuridicaRepo.obter(idPessoaJuridica);

                                if (pessoaJuridicaObtida != null) {
                                    pessoaJuridicaObtida.exibir();

                                    System.out.println("Nome atual da pessoa juridica: " + pessoaJuridicaObtida.getNome());
                                    System.out.println("Insira um novo nome: ");
                                    String novoNome = scanner.nextLine();

                                    System.out.println("CNPJ atual da pessoa juridica: " + pessoaJuridicaObtida.getCnpj());
                                    System.out.println("Insira um novo CNPJ: ");
                                    String novoCNPJ = scanner.nextLine();

                                    pessoaJuridicaRepo.alterar(pessoaJuridicaObtida, novoNome, novoCNPJ);

                                    System.out.println("Pessoa juridica alterada com sucesso!");
                                } else
                                    System.out.println("Pessoa juridica com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Opcao invalida
                            default:
                                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                                break;
                        }

                        // Voltar para o menu principal
                    } while (!opcaoSelecionada.equalsIgnoreCase("Q"));
                    break;

                // EXCLUIR PESSOA
                case "3":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Excluir pessoa física por ID");
                        System.out.println("J - Excluir pessoa juridica por ID");
                        System.out.println("Q - Voltar ao Menu Principal");
                        System.out.println("==============================");

                        opcaoSelecionada = scanner.next();
                        scanner.nextLine();

                        switch (opcaoSelecionada.toUpperCase()) {

                            // Excluindo pessoa fisica
                            case "F":
                                System.out.println("Insira o ID da pessoa física: ");
                                int idPessoaFisica = scanner.nextInt();

                                PessoaFisica pessoaFisicaObtida = pessoaFisicaRepo.obter(idPessoaFisica);

                                if (pessoaFisicaObtida != null) {
                                    pessoaFisicaObtida.exibir();

                                    pessoaFisicaRepo.excluir(idPessoaFisica);

                                    System.out.println("Pessoa fìsica excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa física com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Excluindo pessoa juridica
                            case "J":
                                System.out.println("Insira o ID da pessoa juridica: ");
                                int idPessoaJuridica = scanner.nextInt();

                                PessoaJuridica pessoaJuridicaObtida = pessoaJuridicaRepo.obter(idPessoaJuridica);

                                if (pessoaJuridicaObtida != null) {
                                    pessoaJuridicaObtida.exibir();

                                    pessoaJuridicaRepo.excluir(idPessoaJuridica);

                                    System.out.println("Pessoa fìsica excluida com sucesso!");
                                } else
                                    System.out.println("Pessoa juridica com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Opcao invalida
                            default:
                                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                                break;
                        }

                        // Voltar para o menu principal
                    } while (!opcaoSelecionada.equalsIgnoreCase("Q"));
                    break;

                // BUSCAR PESSOA PELO ID
                case "4":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Obter dados da Pessoa física por ID");
                        System.out.println("J - Obter dados da pessoa juridica por ID");
                        System.out.println("Q - Voltar ao Menu Principal");
                        System.out.println("==============================");

                        opcaoSelecionada = scanner.next();
                        scanner.nextLine();

                        switch (opcaoSelecionada.toUpperCase()) {

                            // Buscando pessoa fisica pelo id
                            case "F":
                                System.out.println("Insira o ID da pessoa física: ");
                                int idPessoaFisica = scanner.nextInt();

                                PessoaFisica pessoaFisicaObtida = pessoaFisicaRepo.obter(idPessoaFisica);

                                if (pessoaFisicaObtida != null) {
                                    System.out.println("Pessoa fìsica encontrada!");

                                    pessoaFisicaObtida.exibir();
                                } else
                                    System.out.println("Pessoa física com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Buscandi pessoa juridica pelo id
                            case "J":
                                System.out.println("Insira o ID da pessoa juridica: ");
                                int idPessoaJuridica = scanner.nextInt();

                                PessoaJuridica pessoaJuridicaObtida = pessoaJuridicaRepo.obter(idPessoaJuridica);

                                if (pessoaJuridicaObtida != null) {
                                    System.out.println("Pessoa juridica encontrada!");

                                    pessoaJuridicaObtida.exibir();
                                } else
                                    System.out.println("Pessoa juridica com este ID não foi encontrada! Tente novamente.");
                                break;

                            // Opcao invalida
                            default:
                                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                                break;
                        }

                        // Voltar para o menu principal
                    } while (!opcaoSelecionada.equalsIgnoreCase("Q"));
                    break;

                //BUSCAR TODAS AS PESSOAS
                case "5":
                    do {
                        System.out.println("==============================");
                        System.out.println("F - Obter lista de Pessoas Físicas");
                        System.out.println("J - Obter lista de Pessoas Jurídicas");
                        System.out.println("Q - Voltar ao Menu Principal");
                        System.out.println("==============================");

                        opcaoSelecionada = scanner.next();
                        scanner.nextLine();

                        switch (opcaoSelecionada.toUpperCase()) {

                            // Buscar todas as pessoa fisicas
                            case "F":
                                System.out.println("Lista de pessoas fìsicas: ");
                                pessoaFisicaRepo.obterTodos()
                                        .forEach(pessoaFisica -> {
                                            pessoaFisica.exibir();
                                            System.out.println();
                                        });
                                break;

                            // Buscar todas as pessoas juridicas
                            case "J":
                                System.out.println("Lista de pessoas juridicas: ");
                                pessoaJuridicaRepo.obterTodos()
                                        .forEach(pessoaJuridica -> {
                                            pessoaJuridica.exibir();
                                            System.out.println();
                                        });
                                break;

                            // Opcao invalida
                            default:
                                System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                                break;
                        }

                        // Voltar para o menu principal
                    } while (!opcaoSelecionada.equalsIgnoreCase("Q"));
                    break;

                // PERSISTIR OS DADOS
                case "6":
                    try {
                        pessoaFisicaRepo.persistir("pessoas_fisicas.dat");
                        pessoaJuridicaRepo.persistir("pessoas_juridicas.dat");
                    } catch (IOException erro) {
                        System.out.println("Erro ao persistir ou recuperar os dados: " + erro.getMessage());
                    }
                    break;

                //RECUPERAR OS DADOS
                case "7":
                    try {
                        pessoaFisicaRepo.recuperar("pessoas_fisicas.dat");
                        pessoaJuridicaRepo.recuperar("pessoas_juridicas.dat");
                    } catch (ClassNotFoundException | IOException erro) {
                        System.out.println("Erro ao persistir ou recuperar os dados: " + erro.getMessage());
                    }
                    break;

                // Opcao invalida
                default:
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                    break;
            }

            // Fechar a aplicacao
        } while (!opcaoSelecionada.equals("0"));

        scanner.close();
    }


}


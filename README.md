![img_1.png](img_1.png)

## Centro Universitário Estácio do Ceará - Campus Centro

### Curso: Desenvolvimento Full Stack

#### Disciplina: Nível 1: Iniciando o Caminho Pelo Java

#### Número da Turma: RPG0014

#### Semestre Letivo: 3

#### Integrantes: Fernando Rocha Fonteles Filho

#### Repositorio Git: https://github.com/fernandorff/EstacioFullStack_Mundo3_Nivel1_Procedimento2

##

### Título da Prática: 2º Procedimento | Criação do Cadastro em Modo Texto

#### Objetivos da Prática:

- Utilizar herança e polimorfismo na definição de entidades.
- Utilizar persistência de objetos em arquivos binários.
- Implementar uma interface cadastral em modo texto.
- Utilizar o controle de exceções da plataforma Java.
- No final do projeto, o aluno terá implementado um sistema cadastral em Java,
  utilizando os recursos da programação orientada a objetos e a persistência em
  arquivos binários.

#### Códigos solicitados neste roteiro de aula:

###

- Classe Application (Aplicação)

```
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
```

- Resultado da execução do códodigo

```
==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
7
Dados da pessoa física recuperados.

Dados da pessoa jurídica recuperados.

==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
1
==============================
F - Incluir Pessoa Física
J - Incluir Pessoa Jurídica
Q - Voltar ao Menu Principal
==============================
f
Insira o nome da pessoa física: 
Fernando Rocha
Insira o cpf da pessoa física: 
12312312312
Insira a idade da pessoa física: 
31
Pessoa física inserida com sucesso!
ID: 2
Nome: Fernando Rocha
CPF: 12312312312
Idade: 31
==============================
F - Incluir Pessoa Física
J - Incluir Pessoa Jurídica
Q - Voltar ao Menu Principal
==============================
q
Opção inválida. Por favor, selecione uma opção válida.
==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
6
Dados da pessoa física armazenados.

Dados da pessoa jurídica armazenados.

==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
5
==============================
F - Obter lista de Pessoas Físicas
J - Obter lista de Pessoas Jurídicas
Q - Voltar ao Menu Principal
==============================
f
Lista de pessoas fìsicas: 
ID: 1
Nome: Fernando Rocha
CPF: 12312312312
Idade: 31

ID: 2
Nome: Fernando Rocha
CPF: 12312312312
Idade: 31

==============================
F - Obter lista de Pessoas Físicas
J - Obter lista de Pessoas Jurídicas
Q - Voltar ao Menu Principal
==============================
q
Opção inválida. Por favor, selecione uma opção válida.
==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
3
==============================
F - Excluir pessoa física por ID
J - Excluir pessoa juridica por ID
Q - Voltar ao Menu Principal
==============================
f
Insira o ID da pessoa física: 
2
ID: 2
Nome: Fernando Rocha
CPF: 12312312312
Idade: 31
Pessoa fìsica excluida com sucesso!
==============================
F - Excluir pessoa física por ID
J - Excluir pessoa juridica por ID
Q - Voltar ao Menu Principal
==============================
q
Opção inválida. Por favor, selecione uma opção válida.
==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================
5
==============================
F - Obter lista de Pessoas Físicas
J - Obter lista de Pessoas Jurídicas
Q - Voltar ao Menu Principal
==============================
f
Lista de pessoas fìsicas: 
ID: 1
Nome: Fernando Rocha
CPF: 12312312312
Idade: 31

==============================
F - Obter lista de Pessoas Físicas
J - Obter lista de Pessoas Jurídicas
Q - Voltar ao Menu Principal
==============================
q
Opção inválida. Por favor, selecione uma opção válida.
==============================
1 - Incluir Pessoa
2 - Alterar Pessoa
3 - Excluir Pessoa
4 - Buscar pelo Id
5 - Exibir Todos
6 - Persistir Dados
7 - Recuperar Dados
0 - Finalizar Programa
==============================

```

##

### Análise e Conclusão

###

#### 1. O que são elementos estáticos e qual o motivo para o método main adotar esse modificador?

Os elementos estáticos em Java pertencem à classe em si e não a uma instância específica da classe. O método main é
declarado como estático porque ele precisa ser chamado diretamente pela JVM (Java Virtual Machine) sem a necessidade de
criar um objeto da classe que contém o método. Essa abordagem permite que o método main seja o ponto de entrada do
programa Java, permitindo que ele seja executado sem a necessidade de instanciar a classe.

###

#### 2. Para que serve a classe Scanner?

A classe Scanner em Java é utilizada para ler entradas do usuário a partir do teclado. Ela fornece métodos para ler
diferentes tipos de dados, como inteiros, números de ponto flutuante, strings, entre outros.

###

#### 3. Como o uso de classes de repositório impactou na organização do código?

As classes de repositório permitem isolar a lógica de acesso a dados seguindo o princípio de responsabilidade única,
onde cada classe tem uma única responsabilidade bem definida. Além disso, a separação da lógica de acesso a dados também
permite uma melhor reutilização de código, pois outras partes do programa podem utilizar a mesma classe de repositório
para acessar os dados de forma consistente.

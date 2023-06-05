package EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.gerenciadores;

import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.PessoaFisica;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica, String novoNome, String novoCpf, int novaIdade) {
        pessoaFisica.setNome(novoNome);
        pessoaFisica.setCpf(novoCpf);
        pessoaFisica.setIdade(novaIdade);
    }

    public void excluir(int id) {
        pessoasFisicas.remove(obter(id));
    }

    public PessoaFisica obter(int id) throws NoSuchElementException {
        Optional<PessoaFisica> pessoaFisicaEncontrada = pessoasFisicas.stream().
                filter(pessoaFisica -> pessoaFisica.getId() == id)
                .findFirst();

        if (pessoaFisicaEncontrada.isPresent()) {
            return pessoaFisicaEncontrada.get();
        } else {
            throw new NoSuchElementException("Pessoa física com ID " + id + " não encontrada.");
        }
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(pessoasFisicas);
        outputStream.close();
        System.out.println("Dados da pessoa física armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa física recuperados.");
        System.out.println();
    }
}
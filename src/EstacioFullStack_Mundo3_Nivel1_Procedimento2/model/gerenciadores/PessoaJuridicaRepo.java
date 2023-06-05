package EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.gerenciadores;

import EstacioFullStack_Mundo3_Nivel1_Procedimento2.model.PessoaJuridica;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica, String novoNome, String novoCpf) {
        pessoaJuridica.setNome(novoNome);
        pessoaJuridica.setCnpj(novoCpf);
    }

    public void excluir(int id) {
        pessoasJuridicas.remove(obter(id));
    }

    public PessoaJuridica obter(int id) throws NoSuchElementException {
        Optional<PessoaJuridica> pessoaJuridicaEncontrada = pessoasJuridicas.stream().
                filter(pessoaJuridica -> pessoaJuridica.getId() == id)
                .findFirst();

        if (pessoaJuridicaEncontrada.isPresent()) {
            return pessoaJuridicaEncontrada.get();
        } else {
            throw new NoSuchElementException("Pessoa jurídica com ID " + id + " não encontrada.");
        }
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(pessoasJuridicas);
        outputStream.close();
        System.out.println("Dados da pessoa jurídica armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa jurídica recuperados.");
        System.out.println();
    }
}
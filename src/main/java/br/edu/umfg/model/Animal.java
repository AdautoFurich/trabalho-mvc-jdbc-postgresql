package br.edu.umfg.model;

public class Animal {

    private int id;
    private String nome;
    private String especie;
    private String raca;
    private int idTutor;
    private String nomeTutor;

    public Animal() {
    }

    public Animal(int id, String nome, String especie, String raca, int idTutor) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idTutor = idTutor;
    }

    public Animal(String nome, String especie, String raca, int idTutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idTutor = idTutor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getNomeTutor() {
        return nomeTutor;
    }

    public void setNomeTutor(String nomeTutor) {
        this.nomeTutor = nomeTutor;
    }
}

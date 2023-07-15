package service;

public class Recurso {
    private int id;
    private String nome;
    private int value;

    public Recurso(int id, String nome, int value) {
        this.id = id;
        this.nome = nome;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

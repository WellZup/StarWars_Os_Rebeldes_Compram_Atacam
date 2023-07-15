package model;


public class Rebelde {
    private int id;
    private String nome;
    private int idade;
    private String genero;
    private String localizacao;
    private boolean traidor;
    private int reportar;

    public Rebelde(int id, String nome, int idade, String genero, String localizacao, boolean traidor, int reportar) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
        this.traidor = traidor;
        this.reportar = reportar;
    }

    // Métodos getter e setter

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }


    public void setTraidor(boolean traidor) {
        this.traidor = traidor;
    }

    public boolean isTraidor() {
        return traidor;
    }

    public int getReportar() {
        return reportar;
    }

    public void setReportar(int reportar) {
        this.reportar = reportar;
    }
}

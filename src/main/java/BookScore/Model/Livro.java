package BookScore.Model;

public class Livro {

    private int id;
    private String nome;
    private String autor;
    private String tipo;
    private int nota;

    public Livro(int id, String nome, String autor, String tipo) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.tipo = tipo;
    }

    public Livro(String nome, String autor, String tipo, int nota) {
        this.nome = nome;
        this.autor = autor;
        this.tipo = tipo;
        this.nota = nota;
    }

    public Livro(String nome, String autor, int nota) {
        this.nome = nome;
        this.autor = autor;
        this.tipo = tipo;
        this.nota = nota;
    }

    public Livro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
     public String toString() {
        return this.getNome();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}

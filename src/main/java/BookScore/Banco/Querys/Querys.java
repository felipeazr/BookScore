/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookScore.Banco.Querys;

public class Querys {

    public String insertUsuarios() {

        String query = "INSERT INTO tb_usuarios (login, nome, idade, sexo, senha, tipoLivro1, tipoLivro2, ativo)"
                + "VALUES (?,?,?,?,?,?,?,?);";

        return query;
    }

    public String selectLogin() {

        String query = "SELECT * FROM tb_usuarios WHERE login = ? and senha = ?";

        return query;
    }

    public String selectUsuarios() {

        String query = "SELECT * FROM tb_usuarios";

        return query;
    }

    public String selectUsuariosId() {

        String query = "SELECT * FROM tb_usuarios WHERE login = ? AND senha = ?";

        return query;
    }

    public String insertNotas() {

        String query = "INSERT INTO tb_notasLivros (idLivro, nota, idUsuario) VALUES (?, ?, ?)";

        return query;
    }

    public String insertLivros() {

        String query = "INSERT INTO tb_livros (titulo, autor, tipo) VALUES (?,?,?)";

        return query;
    }

    public String selectLivrosFiltrados() {

        String query = "SELECT * FROM tb_livros WHERE titulo = ?";

        return query;
    }

    public String selectLivros() {

        String query = "SELECT " +
                 "    tb_livros.titulo, " +
                 "    tb_livros.autor, " +
                 "    LEAST(ROUND(AVG(CAST(tb_notasLivros.nota AS DECIMAL(5,2))), 1), 10) AS media_notas " +
                 "FROM " +
                 "    tb_livros " +
                 "LEFT JOIN tb_notasLivros ON tb_livros.id = tb_notasLivros.idLivro " +
                 "GROUP BY " +
                 "    tb_livros.id, tb_livros.titulo, tb_livros.autor, tb_livros.tipo " +
                 "ORDER BY " +
                 "    media_notas DESC, COUNT(tb_notasLivros.idUsuario) DESC, tb_livros.titulo";

        return query;
    }
    
    public String selectListaLivros() {
        
        String query = "SELECT * FROM tb_livros";
        
        return query;
    }
    
    
    public String selectExisteLivro() {
        
        String query = "SELECT * FROM tb_livros WHERE titulo = ?";
        
        return query;
    }
}

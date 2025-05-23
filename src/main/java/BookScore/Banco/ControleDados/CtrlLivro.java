/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookScore.Banco.ControleDados;

import BookScore.Banco.ConexaoBanco;
import BookScore.Banco.Querys.Querys;
import BookScore.Model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;

public class CtrlLivro {

    Querys _Query = new Querys();

    public boolean novoLivro(String nomeLivro, String nomeAutor, String tipo) {

        String sql = _Query.insertLivros();

        try (Connection c = ConexaoBanco.obtemConexao()) {

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, nomeLivro);
            ps.setString(2, nomeAutor);
            ps.setString(3, tipo);

            int linhasAfetadas = ps.executeUpdate();

            return linhasAfetadas > 0;

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar livros... Por favor tente novamente", "ERRO INTERNO", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }

    public int pegarIdLivro(String nomeLivro) {

        String sql = _Query.selectLivrosFiltrados();

        int idLivro = 0;

        try (Connection c = ConexaoBanco.obtemConexao()) {

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeLivro);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                idLivro = resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erro ao tentar retornar livros... Por favor tente novamente", "ERRO INTERNO", JOptionPane.ERROR_MESSAGE);

        }
        return idLivro;
    }

    public List<Livro> selectLivros() {

        String sql = _Query.selectLivros();
        List<Livro> listaLivros = new ArrayList<>();

        try (Connection c = ConexaoBanco.obtemConexao()) {

            PreparedStatement ps = c.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                String nome = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                double nota = resultSet.getDouble("media_notas");

                Livro livro = new Livro(nome, autor, (int) nota);
                listaLivros.add(livro);
            }
        } catch (Exception e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erro ao tentar retornar livros... Por favor tente novamente", "ERRO INTERNO", JOptionPane.ERROR_MESSAGE);

        }

        // Ordena a lista de livros por nota (do maior para o menor)
        Collections.sort(listaLivros, Comparator.comparingInt(Livro::getNota).reversed());

        return listaLivros;
    }

    public boolean existeLivro(String nomeLivro) throws Exception {

        String sql = _Query.selectExisteLivro();

        try (Connection conn = ConexaoBanco.obtemConexao(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomeLivro);

            try (ResultSet rs = ps.executeQuery()) {

                return rs.next();
            }
        }
    }

    public Livro[] pegaListaLivros() throws Exception {

        String sql = _Query.selectListaLivros();

        try (Connection conn = ConexaoBanco.obtemConexao(); PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = ps.executeQuery()) {

            int totalLivros = 0;

            if (rs.last()) {
                totalLivros = rs.getRow();
            } else {
                totalLivros = 0;
            }

            Livro[] livros = new Livro[totalLivros];

            //voltar ao topo do rs(pq o rs leu a tabela do BD até o fim pra ver quantas linhas tinha)
            rs.beforeFirst();

            int cont = 0;
            while (rs.next()) {

                int idLivro = rs.getInt("id");
                String nomeLivro = rs.getString("titulo");
                String nomeAutor = rs.getString("autor");
                String tipoLivro = rs.getString("tipo");

                Livro l = new Livro(idLivro, nomeLivro, nomeAutor, tipoLivro);
                livros[cont] = l;
                cont++;

            }
            return livros;
        }
    }
}


package BookScore.Banco.ControleDados;

import BookScore.Banco.ConexaoBanco;
import BookScore.Banco.Querys.Querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class CtrlNota {
    
    Querys _Query = new Querys();
    
    public boolean registrarNota(int valorNota, int idUsuario, int idLivro) {
        
        String sql = _Query.insertNotas();
        
        try (Connection c = ConexaoBanco.obtemConexao()) {
            
            PreparedStatement ps = c.prepareStatement(sql);
            
            ps.setInt(1, idLivro);
            ps.setInt(2, valorNota);
            ps.setInt(3, idUsuario);

            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0;
        }
        catch (Exception e) {
            
            e.printStackTrace();
            
            JOptionPane.showMessageDialog(null,"Erro ao tentar cadastrar as notas... Por favor tente novamente" ,"ERRO INTERNO", JOptionPane.ERROR_MESSAGE );

            return false;
        }
    }
}

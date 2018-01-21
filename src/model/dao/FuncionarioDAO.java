package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Funcionario;

public class FuncionarioDAO {
    Connection con;
    
    public FuncionarioDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public void create(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into funcionario " +
				"(Matricula, CPF, Login, Senha, Nome, Email, Carga_hora, Matricula_supervisor, data_inicio, Cod_Unid_Suporte) " +
				"values (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, f.getMatricula());
			stmt.setLong(2, f.getCPF());
			stmt.setString(3, f.getLogin());
			stmt.setString(4, f.getSenha());
			stmt.setString(5, f.getNome());
			stmt.setString(6, f.getEmail());
			stmt.setInt(7, f.getCargaHoraria());
			stmt.setString(8, f.getMatriculaSuperv());
			stmt.setDate(9, (Date) f.getDataInicio());
			stmt.setInt(10, f.getCodUnid_Suporte());
			
			stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Funcionario> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setMatricula(rs.getString("Matricula"));
				funcionario.setMatriculaSuperv(rs.getString("MatriculaSupervisor"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCPF(rs.getLong("CPF"));
				funcionario.setCodUnid_Suporte(rs.getInt("CodigoUnidadeDeSuporte"));
				funcionario.setLogin(rs.getString("Login"));
				funcionario.setSenha(rs.getString("Senha"));
				funcionario.setEmail(rs.getString("Email"));
				funcionario.setCargaHoraria(rs.getInt("CargaHoraria"));
				funcionario.setId_jornada(rs.getString("IdJornada"));
				funcionario.setData_inicio(rs.getDate("DataInicio"));

				funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }
    public List<Funcionario> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE Matricula LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setMatricula(rs.getString("Matricula"));
				funcionario.setMatriculaSuperv(rs.getString("MatriculaSupervisor"));
				funcionario.setNome(rs.getString("Nome"));
				funcionario.setCPF(rs.getLong("CPF"));
				funcionario.setCodUnid_Suporte(rs.getInt("CodigoUnidadeDeSuporte"));
				funcionario.setLogin(rs.getString("Login"));
				funcionario.setSenha(rs.getString("Senha"));
				funcionario.setEmail(rs.getString("Email"));
				funcionario.setCargaHoraria(rs.getInt("CargaHoraria"));

				funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return funcionarios;

    }

    public void update(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE funcionario SET Matricula = ? ,CPF = ?,Login = ?,Senha = ?,Nome = ?, Email = ?, CargaHoraria = ?, MatriculaSupervisor = ?, IdJornada = ?, DataInicio = ?, CodigoUnidadeDeSuporte = ?  WHERE Matricula = ?");
            stmt.setString(1, f.getMatricula());
			stmt.setLong(2, f.getCPF());
			stmt.setString(3, f.getLogin());
			stmt.setString(4, f.getSenha());
			stmt.setString(5, f.getNome());
			stmt.setString(6, f.getEmail());
			stmt.setInt(7, f.getCargaHoraria());
			stmt.setString(8, f.getMatriculaSuperv());
			stmt.setString(9, f.getIdjornada());
			stmt.setDate(10, (Date) f.getDataInicio());
			stmt.setInt(11, f.getCodUnid_Suporte());
			stmt.setString(1, "%"+f.getMatricula()+"%");
			
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Funcionario f) {

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE Matricula = ?");
            stmt.setString(1, f.getMatricula());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
}
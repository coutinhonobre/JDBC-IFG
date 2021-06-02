package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class AlunoJDBC {
	
	Connection con;
	String sql;
	PreparedStatement pst;
	
	
	public void salvar(Aluno a) throws IOException {
		
		try {
			Connection con = db.getConexao();
			System.out.println("Conexão realizada com sucesso !");
			
			sql = "INSERT INTO aluno (nome, sexo, dt_nasc) VALUES ( ?,  ?, ?)";
			
			pst = con.prepareStatement(sql);
			pst.setString(1, a.getNome());
			pst.setString(2, a.getSexo());
			
			Date dataSql = new Date(a.getDt_nasc().getTime());
			pst.setDate(3, dataSql);
			
			pst.executeUpdate();
			System.out.println("\nCadastro do aluno realizado com sucesso!");
			
			db.fechaConexao();
			System.out.println("Conex�o fechada com sucesso !");
		}
		catch (SQLException e) {
			
			System.out.println(e);
		}
		
	}
	
	public List<Aluno> listar() throws IOException, SQLException {
		Connection con = db.getConexao();
		System.out.println("Conexão realizada com sucesso !");
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		try(Connection conn = db.getConexao();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery("select * from aluno");
		      ) {		      
		         while(rs.next()){
		            Aluno aluno = new Aluno();
		            aluno.setId(rs.getInt("id"));
		            aluno.setNome(rs.getString("nome"));
		            aluno.setSexo(rs.getString("sexo"));
		            aluno.setDt_nasc(rs.getDate("dt_nasc"));
		            alunos.add(aluno);
		         }
		         return alunos;
		      } catch (SQLException e) {
		         return new ArrayList<Aluno>();
		      } 
	}
	
	public void apagar(int id) throws IOException, SQLException {
		String sql = "DELETE FROM aluno where id = ?";
		try
		  {
			Connection conn = db.getConexao();
		    PreparedStatement ps = conn.prepareStatement(sql);

		    ps.setInt(1, id);

		    ps.executeQuery();
		    ps.close();
		  }
		  catch (SQLException se)
		  {
		    throw se;
		  }
	}
	
	public void alterar(Aluno aluno) throws IOException, SQLException {
		String sql = "UPDATE aluno " +
	            "SET nome = ?, sexo = ?, dt_nasc = ? WHERE id = ?";
		try
		  {
			Connection conn = db.getConexao();
		    PreparedStatement ps = conn.prepareStatement(sql);

		    ps.setString(1, aluno.getNome());
		    ps.setString(2, aluno.getSexo());
		    Date dataSql = new Date(aluno.getDt_nasc().getTime());
		    ps.setDate(3, dataSql);
		    ps.setInt(4, aluno.getId());

		    ps.executeUpdate();
		    ps.close();
		  }
		  catch (SQLException se)
		  {
		    throw se;
		  }
	}
}


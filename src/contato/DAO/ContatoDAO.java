package contato.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import contato.factory.ConnectionFactoty;
import contato.model.Contato;

import com.mysql.jdbc.PreparedStatement;

public class ContatoDAO {

	
	private static final ConnectionFactoty ConnectionFactory = null;

	public void save(Contato contato) {
		String sql  = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?,?,?)";
	
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//cria uma conexão com banco de dados
			conn = ConnectionFactoty.createConnectionToMySQL();
			
			// Crei a PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
		
			pstm.execute();
			System.out.println("Contato salvo com sucesso!!");
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			//para fechar ass conexões
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void update(Contato contato) {
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? "+
		"WHERE id = ? ";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexão
			conn =	ConnectionFactoty.createConnectionToMySQL();
			
			//Criar a classe para executtar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			//Id do registro que deseja atualizar
			pstm.setInt(4, contato.getId());
			
			
			//executar a query
			pstm.execute();
			System.out.println("Atualizado com sucesso!!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if(pstm != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void deleteByID(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactoty.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
		
			pstm.setInt(1, id);
			
			pstm.execute();
			System.out.println("DELETADO com sucesso!!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco
		ResultSet rset = null;
		
		try {
		 conn =	ConnectionFactoty.createConnectionToMySQL();
		
		 pstm = (PreparedStatement) conn.prepareStatement(sql);
		
		 rset = pstm.executeQuery();
		 
		 while (rset.next()) {
			 
			 Contato contato = new Contato();
			 
			 //recuperar o id
			 contato.setId(rset.getInt("id"));
			//recuperar o nome
			contato.setNome(rset.getString("nome"));
			//recuperar o idade
			contato.setIdade(rset.getInt("idade"));
			//recuperar o dataCadastro
			contato.setDataCadastro(rset.getDate("dataCadastro"));
		 
		 
		 contatos.add(contato);
		 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 try {
				 if(rset != null) {
					 rset.close();
				 }
				 if(rset != null) {
					 pstm.close();
				 }
				 if(rset != null) {
					 conn.close();
				 }
			 }catch (Exception e) {
				 e.printStackTrace();				 
			 }
		 } 
		 return contatos;
		 
	}
}

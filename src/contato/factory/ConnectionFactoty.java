package contato.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoty {
 
	//nome do usuario do mysql
	private static final String USERNAME ="root";
	
	//senha do banco né fi
	private static final String PASSWORD ="";
	
	//caminho banco de dados e a maluca da porta, e o nome do BD
	private static final String DATABASSSE_URL = "jdbc:mysql://localhost:3306/agenda";

	
	//conectar ao banco de dados agr HAHAHAHAHAH
	
	public static Connection createConnectionToMySQL() throws Exception {
		//faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		
		Connection connection = DriverManager.getConnection(DATABASSSE_URL, USERNAME, PASSWORD);
		
		return connection;
		
		
	}
	
	public static void main(String[] args) throws Exception {
	
		//recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if(con!=null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
	
	}
	
	




}

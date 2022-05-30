package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String url = "jdbc:mysql://localhost:3306/jsp-projeto?autoReconnect=true&useSSL=false&useTimezone=true&amp&serverTimezone=UTC";
	private static String usuario = "adm";
	private static String senha = "1234";
	private static Connection conexao = null;
	
	
	static {// chamar classe direto
		conectar();
	}
	
	// CONTRUTOR
	public SingleConnection() {
		conectar();
	}		
		
	// CONECTAR
	private static void conectar(){
		
		try {
			
			if(conexao == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexao = DriverManager.getConnection(url,usuario,senha);
				conexao.setAutoCommit(false);
				//System.out.println("Conectado com sucesso");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// GETCONEXAO
	public static Connection getConexao(){
		return conexao;
	}
	
}

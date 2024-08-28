import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
    	 try {
    	        Class.forName("org.postgresql.Driver"); // Certifique-se de carregar o driver
    	        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste", "postgres", "@123");
    	    } catch (ClassNotFoundException e) {
    	        System.err.println("Driver JDBC do PostgreSQL não encontrado.");
    	        e.printStackTrace();
    	    } catch (SQLException e) {
    	        System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
    	        e.printStackTrace();
    	    }
    }

    public void inserir(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (login, senha, sexo) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM usuarios";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0));
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizar(Usuario usuario) {
        try {
            String sql = "UPDATE usuarios SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.setInt(4, usuario.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM usuarios WHERE codigo = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

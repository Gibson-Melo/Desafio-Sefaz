package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import db.DB;
import modelo.Usuario;




@Named("bean")
@SessionScoped
public class UsuarioBeans implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;

	private List<Usuario> list = new ArrayList<>();

	public String adicionar() {
		
		Connection conn = null;

		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO dados (nome,fone,email,senha) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, usuario.getNome());
			st.setString(2, usuario.getTelefone());
			st.setString(3, usuario.getEmail());
			st.setString(4, usuario.getSenha());

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
		limpar();

		return null;
	}

	private void limpar() {
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getList() {
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

}

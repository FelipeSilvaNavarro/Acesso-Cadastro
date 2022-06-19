package poo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CRUD {

	private int id;
	private String usuario;
	private String senha;
	private JTable dados;

	public CRUD(JTable dad) {
		this.dados = dad;
	}

	public CRUD(int id_p) {
		this.id = id_p;
	}

	public CRUD(String usu, String sen) {
		this.usuario = usu;
		this.senha = sen;
	}

	public CRUD(int id_p, String usu, String sen) {
		this.id = id_p;
		this.usuario = usu;
		this.senha = sen;
	}

	public void salvar() {
		try {
			Connection conexao = conexoes.Conexao.faz_conexao();
			String sqlInserir = "insert into dados_senhas(usuario, senha) values (?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sqlInserir);

			stmt.setString(1, usuario);
			stmt.setString(2, senha);

			stmt.execute();

			// Se tudo der certo
			JOptionPane.showMessageDialog(null, "Salvo com sucesso!");

			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Usuario já existe");
			e.getMessage();
		}
	}

	public void atualizar() {
		try {
			Connection conexao = conexoes.Conexao.faz_conexao();
			String sqlUpdate = "update dados_senhas set usuario=?, senha=? where id=?";
			PreparedStatement stmt = conexao.prepareStatement(sqlUpdate);

			stmt.setString(1, usuario);
			stmt.setString(2, senha);
			stmt.setInt(3, id);

			stmt.execute();

			// Se tudo der certo
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void excluir() {
		try {
			Connection conexao = conexoes.Conexao.faz_conexao();
			String sqlExcluir = "delete from dados_senhas where id=?";
			PreparedStatement stmt = conexao.prepareStatement(sqlExcluir);
			stmt.setInt(1, id);

			stmt.execute();

			// Se tudo dert certo
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");

			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listar() {
		try {
			Connection conexao = conexoes.Conexao.faz_conexao();
			String sqlListar = "select * from dados_senhas";
			PreparedStatement stmt = conexao.prepareStatement(sqlListar);
			ResultSet rs = stmt.executeQuery();
			DefaultTableModel modelo = (DefaultTableModel) dados.getModel();
			modelo.setNumRows(0);
			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getString("id"), rs.getString("usuario"), rs.getString("senha") });
			}
			rs.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

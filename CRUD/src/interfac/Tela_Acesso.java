package interfac;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Tela_Acesso {

	public JFrame frmAcessoLaikos;
	private JTextField tfUser;
	private JPasswordField pfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Acesso window = new Tela_Acesso();
					window.frmAcessoLaikos.setVisible(true);
					window.frmAcessoLaikos.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Acesso() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcessoLaikos = new JFrame();
		frmAcessoLaikos.setResizable(false);
		frmAcessoLaikos.getContentPane().setBackground(Color.BLACK);
		frmAcessoLaikos.getContentPane().setForeground(Color.BLACK);
		frmAcessoLaikos.setTitle("Acesso Laikos");
		frmAcessoLaikos.setBounds(100, 100, 329, 297);
		frmAcessoLaikos.getContentPane().setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.CYAN);
		lblUsuario.setFont(new Font("Verdana", Font.BOLD, 18));
		lblUsuario.setBounds(10, 43, 100, 39);
		frmAcessoLaikos.getContentPane().add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.CYAN);
		lblSenha.setFont(new Font("Verdana", Font.BOLD, 18));
		lblSenha.setBounds(10, 110, 100, 39);
		frmAcessoLaikos.getContentPane().add(lblSenha);

		tfUser = new JTextField();
		tfUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUser.setBounds(120, 43, 146, 33);
		frmAcessoLaikos.getContentPane().add(tfUser);
		tfUser.setColumns(10);

		pfSenha = new JPasswordField();
		pfSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfSenha.setBounds(120, 115, 146, 33);
		frmAcessoLaikos.getContentPane().add(pfSenha);

		JButton botaoEntrar = new JButton("Entrar");
		botaoEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var senha = new String(pfSenha.getPassword());
				// Validação do usuario e senha
				if (tfUser.getText().equals("") || senha.equals("")) {
					JOptionPane.showMessageDialog(null, "Usuario ou senha vazios");
				} else {
					try {
						Connection conexao = conexoes.Conexao.faz_conexao();
						String sqlValidacao = "select * from dados_senhas where usuario=? and senha=?";
						PreparedStatement stmt = conexao.prepareStatement(sqlValidacao);

						stmt.setString(1, tfUser.getText());
						stmt.setString(2, senha);

						stmt.executeQuery();
						ResultSet rs = stmt.executeQuery();
						if (rs.next()) {
							// Mandar p/ outra janela
							Tela_Cadastro janela = new Tela_Cadastro();
							janela.frmCadastro.setVisible(true);
							janela.frmCadastro.setLocationRelativeTo(null);
							frmAcessoLaikos.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
						}

						stmt.close();
						conexao.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		botaoEntrar.setForeground(Color.BLUE);
		botaoEntrar.setBackground(Color.CYAN);
		botaoEntrar.setFont(new Font("Verdana", Font.BOLD, 16));
		botaoEntrar.setBounds(95, 201, 114, 23);
		frmAcessoLaikos.getContentPane().add(botaoEntrar);
	}
}

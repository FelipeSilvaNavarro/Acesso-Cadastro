package interfac;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import poo.CRUD;

public class Tela_Cadastro {

	public JFrame frmCadastro;
	private JTextField tfSenhaCadastro;
	private JTextField tfUserCadastro;
	private JTextField tfId;
	private JTextField tfBusca;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Cadastro window = new Tela_Cadastro();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastro = new JFrame();
		frmCadastro.setResizable(false);
		frmCadastro.getContentPane().setBackground(new Color(0, 0, 0));
		frmCadastro.getContentPane().setForeground(Color.BLACK);
		frmCadastro.setTitle("Cadastro");
		frmCadastro.setBounds(100, 100, 381, 537);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 98, 44, 23);
		frmCadastro.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setBounds(10, 132, 88, 23);
		frmCadastro.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setForeground(Color.CYAN);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 166, 88, 23);
		frmCadastro.getContentPane().add(lblNewLabel_2);

		tfSenhaCadastro = new JTextField();
		tfSenhaCadastro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfSenhaCadastro.setBounds(122, 168, 146, 20);
		frmCadastro.getContentPane().add(tfSenhaCadastro);
		tfSenhaCadastro.setColumns(10);

		tfUserCadastro = new JTextField();
		tfUserCadastro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUserCadastro.setColumns(10);
		tfUserCadastro.setBounds(122, 134, 146, 20);
		frmCadastro.getContentPane().add(tfUserCadastro);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfId.setBounds(122, 100, 146, 20);
		frmCadastro.getContentPane().add(tfId);
		tfId.setColumns(10);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"A\u00E7\u00F5es", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, Color.WHITE));
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 336, 344, 56);
		frmCadastro.getContentPane().add(panel);
		panel.setLayout(null);

		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addActionListener(new ActionListener() {
			// Inserir novo usuario c/ senha
			public void actionPerformed(ActionEvent e) {
				// Verificar campos vazios
				if (tfUserCadastro.getText().equals("") || tfSenhaCadastro.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Usuario ou senha vazios");
				} else {
					CRUD ac = new CRUD(tfUserCadastro.getText(), tfSenhaCadastro.getText());
					ac.salvar();
					// Apagar os textos
					tfUserCadastro.setText("");
					tfSenhaCadastro.setText("");
				}
			}
		});
		botaoSalvar.setBounds(10, 21, 78, 23);
		panel.add(botaoSalvar);

		JButton botaoAtualizarDados = new JButton("Atualizar");
		botaoAtualizarDados.addActionListener(new ActionListener() {
			// Atualizar dados do usuario
			public void actionPerformed(ActionEvent e) {
				// Verificar campos vazios
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor abra o ID");
				} else {
					CRUD ac = new CRUD((Integer.parseInt(tfId.getText())), tfUserCadastro.getText(),
							tfSenhaCadastro.getText());
					ac.atualizar();
					// Apagar os textos
					tfId.setText("");
					tfUserCadastro.setText("");
					tfSenhaCadastro.setText("");

				}
			}
		});
		botaoAtualizarDados.setBounds(126, 21, 89, 23);
		panel.add(botaoAtualizarDados);

		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			// Excluir usuario por ID
			public void actionPerformed(ActionEvent e) {
				// Verificar dados vazios
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor informe o ID");
				} else {
					CRUD ac = new CRUD((Integer.parseInt(tfId.getText())));
					ac.excluir();
					// Apagar os textos
					tfId.setText("");
					tfSenhaCadastro.setText("");
					tfUserCadastro.setText("");
				}

			}
		});
		botaoExcluir.setBounds(245, 21, 89, 23);
		panel.add(botaoExcluir);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Abrir dados", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 403, 344, 62);
		frmCadastro.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		tfBusca = new JTextField();
		tfBusca.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				tfBusca.setText("");
			}
		});
		tfBusca.setText("Digite o ID");
		tfBusca.setToolTipText("");
		tfBusca.setForeground(Color.BLACK);
		tfBusca.setFont(new Font("Verdana", Font.PLAIN, 10));
		tfBusca.setBounds(118, 31, 74, 20);
		panel_1.add(tfBusca);
		tfBusca.setColumns(10);

		JButton botaoAbrir = new JButton("Abrir");
		botaoAbrir.addActionListener(new ActionListener() {
			// Abrir dados do usuario por ID
			public void actionPerformed(ActionEvent e) {
				// Verificar campos varios
				if (tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				} else {
					try {
						Connection conexao = conexoes.Conexao.faz_conexao();
						String sqlAbrir = "select * from dados_senhas where id like ?";
						PreparedStatement stmt = conexao.prepareStatement(sqlAbrir);
						stmt.setString(1, "%" + tfBusca.getText());
						ResultSet rs = stmt.executeQuery();
						if (rs.next()) {
							tfUserCadastro.setText(rs.getString("usuario"));
							tfSenhaCadastro.setText(rs.getString("senha"));
							tfId.setText(rs.getString("id"));
						} else {
							tfUserCadastro.setText("");
							tfSenhaCadastro.setText("");
						}
						stmt.close();
						conexao.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		botaoAbrir.setBounds(40, 30, 68, 23);
		panel_1.add(botaoAbrir);

		JButton btnListarDados = new JButton("Listar Dados");
		btnListarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CRUD listar = new CRUD(tbDados);
				listar.listar();
			}
		});
		btnListarDados.setBounds(227, 30, 107, 23);
		panel_1.add(btnListarDados);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 200, 344, 125);
		frmCadastro.getContentPane().add(scrollPane);

		tbDados = new JTable();
		tbDados.setBorder(new EmptyBorder(0, 0, 0, 0));
		tbDados.setFont(new Font("Verdana", Font.PLAIN, 10));
		tbDados.setRowSelectionAllowed(false);
		tbDados.setEnabled(false);
		tbDados.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Id", "Usuario", "Senha" }));
		tbDados.getColumnModel().getColumn(0).setResizable(false);
		tbDados.getColumnModel().getColumn(1).setResizable(false);
		tbDados.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tbDados);

		// Menu começa aqui
		JMenuBar menuBar = new JMenuBar();
		frmCadastro.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("A\u00E7\u00F5es");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Salvar");
		mntmNewMenuItem.setForeground(Color.DARK_GRAY);
		mntmNewMenuItem.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar campos vazios
				if (tfUserCadastro.getText().equals("") || tfSenhaCadastro.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Usuario ou senha vazios");
				} else {
					CRUD ac = new CRUD(tfUserCadastro.getText(), tfSenhaCadastro.getText());
					ac.salvar();

					// Apagar os textos
					tfId.setText("");
					tfUserCadastro.setText("");
					tfSenhaCadastro.setText("");
				}
			}
		});

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Atualizar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar campos vazios
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor abra o ID");
				} else {
					CRUD ac = new CRUD((Integer.parseInt(tfId.getText())), tfUserCadastro.getText(),
							tfSenhaCadastro.getText());
					ac.atualizar();

					// Apagar os textos
					tfId.setText("");
					tfSenhaCadastro.setText("");
					tfUserCadastro.setText("");
				}
			}
		});

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Excluir");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar campos vaioz
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor informe o Id");
				} else {
					CRUD ac = new CRUD((Integer.parseInt(tfId.getText())));
					ac.excluir();

					// Apagar os textos
					tfId.setText("");
					tfUserCadastro.setText("");
					tfSenhaCadastro.setText("");
				}
			}
		});
		mntmNewMenuItem_2.setForeground(Color.DARK_GRAY);
		mntmNewMenuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem_2.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		mntmNewMenuItem_1.setForeground(Color.DARK_GRAY);
		mntmNewMenuItem_1.setFont(new Font("Javanese Text", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnNewMenu.add(mntmNewMenuItem);
	}
}

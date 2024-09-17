/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Motorista;
import modelo.Viagem;
import regras_negocio.Fachada;

public class TelaMotorista {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnListarMotoristas;
	private JButton btnCadastrarMotorista;
	private JButton button_2;
	private JLabel label;
	private JLabel lblCnh;
	private JLabel label_3;
	private JLabel label_4;
	private JButton btnExibirViagens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMotorista window = new TelaMotorista();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaMotorista() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		
		frame.setResizable(false);
		frame.setTitle("Motorista");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() >= 0) 
					label_4.setText("selecionado="+ table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.ORANGE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");		//label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		lblCnh = new JLabel("CNH:");
		lblCnh.setHorizontalAlignment(SwingConstants.LEFT);
		lblCnh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCnh.setBounds(31, 223, 71, 14);
		frame.getContentPane().add(lblCnh);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(78, 218, 195, 20);
		frame.getContentPane().add(textField);

		btnCadastrarMotorista = new JButton("Cadastrar Motorista");
		btnCadastrarMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cnh = textField.getText();
					String nome = textField_1.getText();
					Fachada.cadastrarMotorista(cnh, nome);
					label.setText("Motorista criado: "+ nome);
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCadastrarMotorista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarMotorista.setBounds(78, 294, 153, 23);
		frame.getContentPane().add(btnCadastrarMotorista);

		btnListarMotoristas = new JButton("Listar Motoristas");
		btnListarMotoristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarMotoristas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		btnListarMotoristas.setBounds(290, 11, 134, 23);
		frame.getContentPane().add(btnListarMotoristas);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(28, 252, 63, 14);
		frame.getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(78, 249, 195, 20);
		frame.getContentPane().add(textField_1);

		button_2 = new JButton("Deletar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String cnh = (String) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.excluirMotorista(cnh);
						label.setText("Motorista apagado" );
						listagem();
					}
					else
						label.setText("nao selecionado");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(524, 219, 171, 23);
		frame.getContentPane().add(button_2);

		btnExibirViagens = new JButton("Exibir Viagens");
		btnExibirViagens.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExibirViagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						String cnh = (String) table.getValueAt( table.getSelectedRow(), 0);
						Motorista motorista = Fachada.localizarMotorista(cnh);

						if(motorista !=  null) {
							String texto="";
							if(motorista.getViagens().isEmpty())
								texto = "não possui viagens";
							else
								for (Viagem v : motorista.getViagens()) 
									texto = texto + v.getData() + " - " + v.getVeiculo() + " - " +v.getDestino() + "\n";

							JOptionPane.showMessageDialog(frame, texto, "Viagens", 1);
						}
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		btnExibirViagens.setBounds(357, 219, 134, 23);
		frame.getContentPane().add(btnExibirViagens);
	}

	public void listagem() {
		try{
			List<Motorista> lista = Fachada.listarMotoristas();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("CNH");
			model.addColumn("Nome");

			//adicionar linhas no model
			for(Motorista m : lista) {
				model.addRow(new Object[]{m.getCnh(), m.getNome()} );
			}

			//atualizar model no table (visualizacao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}

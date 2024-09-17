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

import modelo.Veiculo;
import modelo.Viagem;
import regras_negocio.Fachada;

public class TelaVeiculo {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnListarVeiculos;
	private JButton btnCadastrarVeiculo;
	private JButton button_2;
	private JLabel label;
	private JLabel lblPlaca;
	private JLabel lblModelo;
	private JLabel label_4;
	private JButton btnExibirViagens;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVeiculo window = new TelaVeiculo();
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
	public TelaVeiculo() {
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
		frame.setTitle("Veiculo");
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

		lblPlaca = new JLabel("Placa:");
		lblPlaca.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlaca.setBounds(31, 223, 71, 14);
		frame.getContentPane().add(lblPlaca);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(78, 218, 195, 20);
		frame.getContentPane().add(textField);

		btnCadastrarVeiculo = new JButton("Cadastrar Veiculo");
		btnCadastrarVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String placa = textField.getText();
					String modelo = textField_1.getText();
					Fachada.cadastrarVeiculo(placa, modelo);
					label.setText("Veiculo cadastrado: "+ placa);
					listagem();
			
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCadastrarVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarVeiculo.setBounds(78, 294, 153, 23);
		frame.getContentPane().add(btnCadastrarVeiculo);

		btnListarVeiculos = new JButton("Listar Veiculos");
		btnListarVeiculos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarVeiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		btnListarVeiculos.setBounds(290, 11, 134, 23);
		frame.getContentPane().add(btnListarVeiculos);

		lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.LEFT);
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblModelo.setBounds(28, 252, 63, 14);
		frame.getContentPane().add(lblModelo);

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
						String placa = (String) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.excluirVeiculo(placa);
						label.setText("Veiculo apagado" );
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
						String placa = (String) table.getValueAt( table.getSelectedRow(), 0);
						Veiculo veiculo = Fachada.localizarVeiculo(placa);

						if(veiculo !=  null) {
							String texto="";
							if(veiculo.getViagens().isEmpty())
								texto = "não possui viagens";
							else
								for (Viagem v : veiculo.getViagens()) 
									texto = texto + v.getData() + " - " + v.getMotorista() + " - " +v.getDestino() + "\n";

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
			List<Veiculo> lista = Fachada.listarVeiculos();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("Placa");
			model.addColumn("Modelo");

			//adicionar linhas no model
			for(Veiculo v : lista) {
				model.addRow(new Object[]{v.getPlaca(), v.getModelo()} );
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

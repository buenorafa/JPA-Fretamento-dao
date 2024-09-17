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

import modelo.Viagem;
import regras_negocio.Fachada;

public class TelaViagem {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField dataField;
	private JTextField placaField;
	private JButton btnListarMotoristas;
	private JButton btnCadastrarViagem;
	private JButton btnDeletar;
	private JLabel label;
	private JLabel lbl_Data;
	private JLabel lblPlaca;
	private JLabel label_4;

	private JButton btnExibirVeiculo;
	private JTextField cnhField;
	private JTextField destinoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaViagem();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaViagem() {
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
		frame.setTitle("Viagem");
		frame.setBounds(100, 100, 729, 392);
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
		label.setBounds(21, 337, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);

		lbl_Data = new JLabel("Data:");
		lbl_Data.setToolTipText("dd/MM/yyyy");
		lbl_Data.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Data.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_Data.setBounds(21, 219, 46, 15);
		frame.getContentPane().add(lbl_Data);

		dataField = new JTextField();
		dataField.setFont(new Font("Dialog", Font.PLAIN, 12));
		dataField.setColumns(10);
		dataField.setBounds(77, 214, 120, 25);
		frame.getContentPane().add(dataField);

		btnCadastrarViagem = new JButton("Cadastrar Viagem");
		btnCadastrarViagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dataField.getText().isEmpty() || placaField.getText().isEmpty() || cnhField.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String data = dataField.getText();
					String placa = placaField.getText();
					String cnh = cnhField.getText();
					String destino = destinoField.getText();
					
					
					
					Fachada.cadastrarViagem(data, placa, cnh, destino);
					label.setText("viagem criada");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCadastrarViagem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarViagem.setBounds(209, 307, 134, 23);
		frame.getContentPane().add(btnCadastrarViagem);

		btnListarMotoristas = new JButton("Listar Viagens");
		btnListarMotoristas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListarMotoristas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		btnListarMotoristas.setBounds(308, 11, 119, 23);
		frame.getContentPane().add(btnListarMotoristas);

		lblPlaca = new JLabel("Placa:");
		lblPlaca.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlaca.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlaca.setBounds(21, 248, 35, 16);
		frame.getContentPane().add(lblPlaca);

		placaField = new JTextField();
		placaField.setFont(new Font("Dialog", Font.PLAIN, 12));
		placaField.setColumns(10);
		placaField.setBounds(77, 244, 120, 25);
		frame.getContentPane().add(placaField);

		btnDeletar = new JButton("Deletar selecionado");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						label.setText("nao implementado " );
						String id = (String) table.getValueAt( table.getSelectedRow(), 0);
						Fachada.excluirViagem(id);
						label.setText("viagem apagada" );
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
		btnDeletar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDeletar.setBounds(524, 216, 171, 23);
		frame.getContentPane().add(btnDeletar);

		btnExibirVeiculo = new JButton("Exibir Veículo");
		btnExibirVeiculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExibirVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						String id = (String) table.getValueAt( table.getSelectedRow(), 0);
						Viagem viagem= Fachada.localizarViagem(id);

						if(viagem !=  null) {
							String texto="";
							String placa = viagem.getVeiculo().getPlaca();
							String modelo = viagem.getVeiculo().getModelo();
							texto = "Placa: " + placa + "\n\nModelo: " + modelo;
							JOptionPane.showMessageDialog(frame, texto, "Veículo", 1);
						}
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		btnExibirVeiculo.setBounds(378, 216, 134, 23);
		frame.getContentPane().add(btnExibirVeiculo);
		
		JLabel lblCnh = new JLabel("CNH:");
		lblCnh.setHorizontalAlignment(SwingConstants.LEFT);
		lblCnh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCnh.setBounds(21, 280, 31, 16);
		frame.getContentPane().add(lblCnh);
		
		cnhField = new JTextField();
		cnhField.setFont(new Font("Dialog", Font.PLAIN, 12));
		cnhField.setColumns(10);
		cnhField.setBounds(77, 276, 120, 25);
		frame.getContentPane().add(cnhField);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.LEFT);
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestino.setBounds(21, 309, 48, 16);
		frame.getContentPane().add(lblDestino);
		
		destinoField = new JTextField();
		destinoField.setFont(new Font("Dialog", Font.PLAIN, 12));
		destinoField.setColumns(10);
		destinoField.setBounds(77, 305, 120, 25);
		frame.getContentPane().add(destinoField);
		
		JButton btnExibirMotorista = new JButton("Exibir Motorista");
		btnExibirMotorista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExibirMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (table.getSelectedRow() >= 0){	
						String id = (String) table.getValueAt( table.getSelectedRow(), 0);
						Viagem viagem= Fachada.localizarViagem(id);

						if(viagem !=  null) {
							String texto="";
							String cnh = viagem.getMotorista().getCnh();
							String nome = viagem.getMotorista().getNome();
							texto = "CNH: " + cnh + "\n\nNome: " + nome;
							JOptionPane.showMessageDialog(frame, texto, "Motorista", 1);
						}
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});

		btnExibirMotorista.setBounds(378, 248, 134, 23);
		frame.getContentPane().add(btnExibirMotorista);
		
	}


	public void listagem() {
		try{
			List<Viagem> lista = Fachada.listarViagens();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("Data");
			model.addColumn("Destino");

			//adicionar linhas no model
			for(Viagem v : lista) {
				model.addRow(new Object[]{v.getId(), v.getData(), v.getDestino()} );
			}

			//atualizar model no table (visualizaao)
			table.setModel(model);

			label_4.setText("resultados: "+lista.size()+ " objetos");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}





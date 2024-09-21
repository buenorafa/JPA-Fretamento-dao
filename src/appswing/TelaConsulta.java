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

public class TelaConsulta{
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnConsultas;
	private JButton btn_1;
	private JLabel label;
	private JLabel labelConsulta;
	private JTextField placaField;
	private JTextField cnhField;
	private JTextField dataField;
	private JTextField nField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsulta window = new TelaConsulta();
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
	public TelaConsulta() {
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
		frame.setTitle("Consulta");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
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
					labelConsulta.setText("selecionado="+ table.getValueAt( table.getSelectedRow(), 0));
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

		labelConsulta = new JLabel("");
		labelConsulta.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(labelConsulta);

		btn_1 = new JButton("1");
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(dataField.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String data = dataField.getText();
					listagem_1(data);
					cnhField.setText("");
					placaField.setText("");
					nField.setText("");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btn_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_1.setBounds(29, 297, 153, 23);
		frame.getContentPane().add(btn_1);

		btnConsultas = new JButton("Consultas");
		btnConsultas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultas.setBounds(290, 11, 134, 23);
		frame.getContentPane().add(btnConsultas);
		
		JLabel lblplaca = new JLabel("Placa:");
		lblplaca.setHorizontalAlignment(SwingConstants.LEFT);
		lblplaca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblplaca.setBounds(277, 274, 71, 14);
		frame.getContentPane().add(lblplaca);
		
		placaField = new JTextField();
		placaField.setFont(new Font("Dialog", Font.PLAIN, 12));
		placaField.setColumns(10);
		placaField.setBounds(314, 271, 110, 20);
		frame.getContentPane().add(placaField);
		
		JButton btn_2 = new JButton("2");
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cnhField.getText().isEmpty() || placaField.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String cnh = cnhField.getText();
					String placa = placaField.getText();
					listagem_2(placa, cnh);
					dataField.setText("");
					nField.setText("");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btn_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_2.setBounds(271, 300, 153, 23);
		frame.getContentPane().add(btn_2);
		
		JLabel lblcnh = new JLabel("CNH:");
		lblcnh.setHorizontalAlignment(SwingConstants.LEFT);
		lblcnh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblcnh.setBounds(277, 247, 71, 14);
		frame.getContentPane().add(lblcnh);
		
		cnhField = new JTextField();
		cnhField.setFont(new Font("Dialog", Font.PLAIN, 12));
		cnhField.setColumns(10);
		cnhField.setBounds(314, 244, 110, 20);
		frame.getContentPane().add(cnhField);
		
		JLabel lbldata = new JLabel("Data:");
		lbldata.setHorizontalAlignment(SwingConstants.LEFT);
		lbldata.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbldata.setBounds(35, 244, 71, 14);
		frame.getContentPane().add(lbldata);
		
		dataField = new JTextField();
		dataField.setFont(new Font("Dialog", Font.PLAIN, 12));
		dataField.setColumns(10);
		dataField.setBounds(72, 241, 110, 20);
		frame.getContentPane().add(dataField);
		
		JLabel lblNewLabel = new JLabel("Consulta 1: Filtrar viagem por data");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblNewLabel.setBounds(21, 203, 190, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblConsultaFiltrar = new JLabel("Consulta 2: Filtrar viagem por CNH e Placa");
		lblConsultaFiltrar.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblConsultaFiltrar.setBounds(245, 203, 224, 39);
		frame.getContentPane().add(lblConsultaFiltrar);
		
		JButton btnCadastrarMotorista_2 = new JButton("3");
		btnCadastrarMotorista_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nField.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String n = nField.getText();
					listagem_3(n);
					cnhField.setText("");
					placaField.setText("");
					dataField.setText("");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		btnCadastrarMotorista_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarMotorista_2.setBounds(505, 297, 153, 23);
		frame.getContentPane().add(btnCadastrarMotorista_2);
		
		JLabel lbln = new JLabel("N:");
		lbln.setHorizontalAlignment(SwingConstants.LEFT);
		lbln.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbln.setBounds(511, 244, 71, 14);
		frame.getContentPane().add(lbln);
		
		nField = new JTextField();
		nField.setFont(new Font("Dialog", Font.PLAIN, 12));
		nField.setColumns(10);
		nField.setBounds(548, 241, 110, 20);
		frame.getContentPane().add(nField);
		
		JLabel lblConsultaMotorista = new JLabel("Consulta 3: Motorista com mais de N viagens ");
		lblConsultaMotorista.setFont(new Font("Lucida Grande", Font.BOLD, 9));
		lblConsultaMotorista.setBounds(481, 203, 228, 39);
		frame.getContentPane().add(lblConsultaMotorista);
	}

	public void listagem_1(String data) {
		try{
			List<Viagem> lista = Fachada.viagensNaData(data);

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("Data");
			model.addColumn("Veículo");
			model.addColumn("Motorista");
			model.addColumn("Destino");

			//adicionar linhas no model
			for(Viagem v : lista) {
				model.addRow(new Object[]{v.getId(), v.getData(), v.getVeiculo().getPlaca(), v.getMotorista().getNome(), v.getDestino()} );
			}

			//atualizar model no table (visualizacao)
			table.setModel(model);

			labelConsulta.setText("resultados da consulta 1");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	public void listagem_2(String placa, String cnh) {
		try{
			List<Viagem> lista = Fachada.viagensPorPlacaECNH(placa, cnh);

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			//adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("Data");
			model.addColumn("Veículo");
			model.addColumn("Motorista");
			model.addColumn("Destino");

			//adicionar linhas no model
			for(Viagem v : lista) {
				model.addRow(new Object[]{v.getId(), v.getData(), v.getVeiculo().getPlaca(), v.getMotorista().getNome(), v.getDestino()} );
			}

			//atualizar model no table (visualizacao)
			table.setModel(model);

			labelConsulta.setText("resultados da consulta 2");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
	public void listagem_3(String n) {
		try{
			List<Motorista> lista = Fachada.maisDeNViagens(n);
			
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

			labelConsulta.setText("resultados da consulta 3");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
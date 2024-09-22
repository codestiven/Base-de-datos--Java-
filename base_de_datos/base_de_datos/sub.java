package base_de_datos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

public class sub {

	private JFrame busqueda;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sub window = new sub();
					window.busqueda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sub() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		busqueda = new JFrame();
		busqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		busqueda.setBounds(100, 100, 450, 140);
		busqueda.getContentPane().setLayout(new BoxLayout(busqueda.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel lblBusqueda = new JLabel("busqueda");
		lblBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		busqueda.getContentPane().add(lblBusqueda);
		
		JScrollPane scrollPane = new JScrollPane();
		busqueda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
	}

}
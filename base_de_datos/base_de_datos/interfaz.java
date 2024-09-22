package base_de_datos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.Component;
import javax.swing.Box;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class interfaz {

	private JFrame frame,Actualizar,Agregar;
	private JTable Tabla;
	private DefaultTableModel modelo = new DefaultTableModel();
    private String idn;
    private JTable bus;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz window = new interfaz();
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
	public interfaz() {
		initialize();
		actualizar_tabla();
	    Editar();


	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 782, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 766, 503);
		
		Tabla = new JTable();
		Tabla.setName("");
		Tabla.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Tabla.setCellSelectionEnabled(true);
		Tabla.setFillsViewportHeight(true);
		Tabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nombre", "fecha", "Hora", "descripcion del evento"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		frame.getContentPane().setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		panel.setLayout(new GridLayout(0, 1, 0, 2));
		
		JButton agregar = new JButton("Agregar");
	
		panel.add(agregar);
		
		JButton actualizar = new JButton("Actualizar");
	
		panel.add(actualizar);
		
		JButton borrar = new JButton("Borrar");
	
		panel.add(borrar);
		
		JButton buscar = new JButton("Buscar");

		panel.add(buscar);
		frame.getContentPane().add(panel);
		
		JButton btnImformacion = new JButton("Informacion");
		panel.add(btnImformacion);
		scrollPane.setViewportView(Tabla);
		frame.getContentPane().add(scrollPane);
		
		
		
//-------------------------------------------	
		
		agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sub window = new sub();
				Agregar();
				Agregar.setVisible(true);
			}
		});	
		
		actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sub window = new sub();
				Actualizar.setVisible(true);
			}
		});
		
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String entrada = JOptionPane.showInputDialog(null , "ingrese valor a eliminar");
				 
				 
				 try{
					 int  num = Integer. parseInt(entrada);
			            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
Statement cs = cn.createStatement();

			            
			            String sql = "DELETE FROM eventos WHERE id = " + num;
			            int valor = cs.executeUpdate(sql);
			        //    PreparedStatement ps = cn.prepareStatement(sql);
			            
			            
			            if (valor == 1) {
			    
			            	 JOptionPane.showMessageDialog(null, "evento borrado con exito");
			            }else {
			            	 JOptionPane.showMessageDialog(null, "evento no encontrado");
			            }
			
			      //      ps.setInt(1, num);
			        //    ResultSet rs = ps.executeQuery(sql);
			          
		      
			            	
			   
			        
			         cn.close();
			            
			        }catch (SQLException re){

			        	 JOptionPane.showMessageDialog(null, "error al borrar  	 ");
			           
			           
			           
			        }
				 
				 actualizar_tabla();
				
			}
		});
		
		
		
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame busqueda = new JFrame();
				busqueda.setBounds(100, 100, 450, 140);
				busqueda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				busqueda.getContentPane().setLayout(new BoxLayout(busqueda.getContentPane(), BoxLayout.Y_AXIS));
				
				JLabel lblBusqueda = new JLabel("busqueda");
				lblBusqueda.setFont(new Font("Tahoma", Font.PLAIN, 20));
				busqueda.getContentPane().add(lblBusqueda);
				
				JScrollPane scrollPane = new JScrollPane();
				busqueda.getContentPane().add(scrollPane);
				
				bus = new JTable();
				bus.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
					}
				));
				scrollPane.setViewportView(bus);
				
				String entrada = JOptionPane.showInputDialog(null , "ingrese valor a buscar");
				
				try{
					int num = Integer. parseInt(entrada) ;
		            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");

		            
		            String sql = "SELECT * FROM eventos where id = "+ num;
		            
		            PreparedStatement ps = cn.prepareStatement(sql);
		            
		            ResultSet rs = ps.executeQuery();
		          
		            ResultSetMetaData rsmd = rs.getMetaData();
		            int cantidadC = rsmd.getColumnCount();
		            

		            modelo = new DefaultTableModel();
		            
		           

		            modelo.addColumn("Id");
		            modelo.addColumn("Nombre");
		            modelo.addColumn("fecha");
		            modelo.addColumn("Hora");
		            modelo.addColumn("descripcion del evento");
		            
		            
		            
		            
		            
		            while(rs.next()) {
		            	Object[]  filas = new Object[cantidadC];
		            	
		            	for(int i = 0 ; i< cantidadC ; i++) {
		            		filas[i] = rs.getObject(i+1);
		            		
		            	}
		            	
		            	modelo.addRow(filas);
		            }
		            
		            bus.setModel(modelo);
		            
		            bus.getColumnModel().getColumn(0).setPreferredWidth(51);
		            bus.getColumnModel().getColumn(1).setPreferredWidth(84);
		            bus.getColumnModel().getColumn(2).setPreferredWidth(102);
		            bus.getColumnModel().getColumn(3).setPreferredWidth(60);
		            bus.getColumnModel().getColumn(4).setPreferredWidth(178);
		    		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		            
		    		
		    		
		    		
		    		
		    		
		    		busqueda.setVisible(true);
		        }catch (SQLException err){
		        	JOptionPane.showMessageDialog(null, "evento no encontrado ");
		       
		           
		           
		           
		        }
				
				
				
				
				actualizar_tabla();
			}
		});

		}
	
	private void actualizar_tabla() {
		 try{
	            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");

	            
	            String sql = "SELECT * FROM eventos";
	            PreparedStatement ps = cn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	          
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int cantidadC = rsmd.getColumnCount();
	            

	            modelo = new DefaultTableModel();
	            
	           

	            modelo.addColumn("Id");
	            modelo.addColumn("Nombre");
	            modelo.addColumn("fecha");
	            modelo.addColumn("Hora");
	            modelo.addColumn("descripcion del evento");
	            
	            
	            
	            
	            
	            while(rs.next()) {
	            	Object[]  filas = new Object[cantidadC];
	            	
	            	for(int i = 0 ; i< cantidadC ; i++) {
	            		filas[i] = rs.getObject(i+1);
	            		
	            	}
	            	
	            	modelo.addRow(filas);
	            }
	            
	            Tabla.setModel(modelo);
	            
	    		Tabla.getColumnModel().getColumn(0).setPreferredWidth(51);
	    		Tabla.getColumnModel().getColumn(1).setPreferredWidth(84);
	    		Tabla.getColumnModel().getColumn(2).setPreferredWidth(102);
	    		Tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
	    		Tabla.getColumnModel().getColumn(4).setPreferredWidth(178);
	    		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
	            
	        }catch (SQLException e){
	           System.out.print(e.toString()); 
	       
	           
	           
	           
	        }
	}
	
	
	private void Agregar() {
		

	JTextField id_a;
		JTextField nombre_a;
		JTextField fecha_a;
		JTextField hora_a;
		
		Agregar = new JFrame();
		Agregar.setBounds(100, 100, 492, 516);
		Agregar.getContentPane().setLayout(null);
		
		JLabel tex = new JLabel("ID_evento");
		tex.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex.setBounds(10, 43, 197, 14);
		Agregar.getContentPane().add(tex);
		
		JLabel tex_1 = new JLabel("nombre de evento");
		tex_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1.setBounds(10, 80, 197, 14);
		Agregar.getContentPane().add(tex_1);
		
		JLabel tex_1_1 = new JLabel("fecha de evento");
		tex_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1_1.setBounds(10, 118, 197, 14);
		Agregar.getContentPane().add(tex_1_1);
		
		JLabel tex_1_1_1 = new JLabel("Hora de evento");
		tex_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1_1_1.setBounds(10, 159, 197, 14);
		Agregar.getContentPane().add(tex_1_1_1);
		
		JLabel tex_1_1_1_1 = new JLabel("descripcion del evento");
		tex_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tex_1_1_1_1.setBounds(10, 203, 197, 14);
		Agregar.getContentPane().add(tex_1_1_1_1);
		

	  idn = "";
		
		//------------------------------------
		 try{
	            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");

	            
	            String sql = "SELECT AUTO_INCREMENT FROM information_schema.tables WHERE TABLE_SCHEMA='agenda' AND TABLE_NAME='eventos';";
	            PreparedStatement ps = cn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	          

	            Object caca = rs.toString();
	   int id = 0;
	       while(rs.next()) {
	    	   id = rs.getInt(1);
	    	   System.out.print(id);
	       }
	       
       	idn = String.valueOf(id) ;
	            	
	   
	        
	         
	            
	        }catch (SQLException e){
	           System.out.print(e.toString()); 
	       
	           
	           
	           
	        }
		 //-------

		id_a = new JTextField(idn) ;
		id_a.setEditable(false);
		id_a.setBounds(244, 43, 222, 20);
		Agregar.getContentPane().add(id_a);
		id_a.setColumns(10);
		
		
		nombre_a = new JTextField();
		nombre_a.setColumns(10);
		nombre_a.setBounds(244, 80, 222, 20);
		Agregar.getContentPane().add(nombre_a);
		
		fecha_a = new JTextField();
		fecha_a.setColumns(10);
		fecha_a.setBounds(244, 119, 222, 20);
		Agregar.getContentPane().add(fecha_a);
		
		hora_a = new JTextField();
		hora_a.setColumns(10);
		hora_a.setBounds(244, 156, 222, 20);
		Agregar.getContentPane().add(hora_a);
		
		JTextArea Descripcion_a = new JTextArea();
		Descripcion_a.setBounds(10, 229, 456, 182);
		Agregar.getContentPane().add(Descripcion_a);
		
		JLabel tex_2 = new JLabel("Agregar");
		tex_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		tex_2.setBounds(180, 0, 97, 47);
		Agregar.getContentPane().add(tex_2);
		
		JButton btn_A = new JButton("guardar");
		btn_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      try{
			            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
			            PreparedStatement pst = cn.prepareStatement("insert into eventos values(?,?,?,?,?)");
			       
			            pst.setLong(1, 0);
			            pst.setString(2, nombre_a.getText());
			            pst.setString(3, fecha_a.getText());
			            pst.setString(4, hora_a.getText());
			            pst.setString(5, Descripcion_a.getText());
			            pst.executeUpdate();
			            
			            
			            JOptionPane.showMessageDialog(null, "evento agregado con extito");
			            
			            Agregar.dispose();
			      

			        	
			        	 int idn = 0;
			     		
			     		//------------------------------------
			     		 try{
			     	             cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");

			     	            
			     	            String sql = "SELECT id FROM eventos";
			     	            PreparedStatement ps = cn.prepareStatement(sql);
			     	            ResultSet rs = ps.executeQuery();
			     	          

			     	            
			     	           
			     	            while(rs.next()) {
			     	            //	Object[]  filas = new Object[cantidadC];
			     	            	idn++;
			   
			     	            	
			     	   
			     	            }
			     	        
			     	         
			     	            
			     	        }catch (SQLException eu){
			     	           System.out.print(e.toString()); 
			     	       
			     	           
			     	           
			     	           
			     	        }
			   
			        }catch (Exception e1){
			           System.out.print(e1); 
			        }
			      
			      actualizar_tabla();
			}
		});
		btn_A.setBounds(193, 434, 97, 32);
		Agregar.getContentPane().add(btn_A);
		
		
	
	}
	
	

private void Editar() {
		

		JTextField id_a;
		JTextField nombre_a;
		JTextField fecha_a;
		JTextField hora_a;
		
		Actualizar = new JFrame();
		Actualizar.setBounds(100, 100, 492, 516);
		Actualizar.getContentPane().setLayout(null);
		
		JLabel tex = new JLabel("ID_evento");
		tex.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex.setBounds(10, 43, 197, 14);
		Actualizar.getContentPane().add(tex);
		
		JLabel tex_1 = new JLabel("nombre de evento");
		tex_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1.setBounds(10, 80, 197, 14);
		Actualizar.getContentPane().add(tex_1);
		
		JLabel tex_1_1 = new JLabel("fecha de evento");
		tex_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1_1.setBounds(10, 118, 197, 14);
		Actualizar.getContentPane().add(tex_1_1);
		
		JLabel tex_1_1_1 = new JLabel("Hora de evento");
		tex_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		tex_1_1_1.setBounds(10, 159, 197, 14);
		Actualizar.getContentPane().add(tex_1_1_1);
		
		JLabel tex_1_1_1_1 = new JLabel("descripcion del evento");
		tex_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tex_1_1_1_1.setBounds(10, 203, 197, 14);
		Actualizar.getContentPane().add(tex_1_1_1_1);
		



		id_a = new JTextField("" );
		id_a.setBounds(244, 43, 222, 20);
		Actualizar.getContentPane().add(id_a);
		id_a.setColumns(10);
		
		
		nombre_a = new JTextField();
		nombre_a.setColumns(10);
		nombre_a.setBounds(244, 80, 222, 20);
		Actualizar.getContentPane().add(nombre_a);
		
		fecha_a = new JTextField();
		fecha_a.setColumns(10);
		fecha_a.setBounds(244, 119, 222, 20);
		Actualizar.getContentPane().add(fecha_a);
		
		hora_a = new JTextField();
		hora_a.setColumns(10);
		hora_a.setBounds(244, 156, 222, 20);
		Actualizar.getContentPane().add(hora_a);
		
		JTextArea Descripcion_a = new JTextArea();
		Descripcion_a.setBounds(10, 229, 456, 182);
		Actualizar.getContentPane().add(Descripcion_a);
		
		JLabel tex_2 = new JLabel("actualizar");
		tex_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		tex_2.setBounds(160, 0, 130, 47);
		Actualizar.getContentPane().add(tex_2);
		
		JButton btn_M = new JButton("actualizar");
		btn_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			      try{
			            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/agenda", "root", "");
			            PreparedStatement pst = cn.prepareStatement("UPDATE eventos SET nombre = ?, fecha = ?, hora = ?, descripcion = ? WHERE id = ? ");

			        
			            pst.setString(1, nombre_a.getText());
			            pst.setString(2, fecha_a.getText());
			            pst.setString(3, hora_a.getText());
			            pst.setString(4, Descripcion_a.getText());
			            pst.setInt(5, Integer.parseInt(id_a.getText()));
			            pst.executeUpdate();
			            
			            
			           
			            JOptionPane.showMessageDialog(null, "evento actualizado con extito");
			            
			        	Actualizar.dispose();
			     
			 
			        	
			        
			     	
			   
			        }catch (Exception e1){
			        	  JOptionPane.showMessageDialog(null, "error , evento no encontrado");
			        }
			      
			      actualizar_tabla();
			}
		});
		btn_M.setBounds(193, 434, 97, 32);
		Actualizar.getContentPane().add(btn_M);
		
		
	
	}
}


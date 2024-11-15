package MVC_Ejercicio18.vista;

import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField textFieldUsuario;
	public JTextField textFieldContrasenia;
	public JTextField textFieldNombre;
	public JTextField textFieldLocalidad;
	public JTable tableResultados;
	public DefaultTableModel modeloTbl= new DefaultTableModel();
	public JButton btnIniciarSesion;
	public JButton btnModificar;
	public JButton btnBorrar;
	public JButton btnGuardar;
	public JButton btnListar;
	public JButton btnNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 441);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConectar = new JMenu("Conectar");
		menuBar.add(mnConectar);
		
		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion\r\n");
		mnConectar.add(mntmIniciarSesion);
		
		JMenu mnConectarDB = new JMenu("Conectar ddbb");
		mnConectar.add(mnConectarDB);
		
		JMenuItem mntmOracle = new JMenuItem("Oracle\r\n");
		mnConectarDB.add(mntmOracle);
		
		JMenuItem mntmMySQL = new JMenuItem("MySQL\r\n");
		mnConectarDB.add(mntmMySQL);
		
		JMenuItem mntmSQLite = new JMenuItem("SQLite");
		mnConectarDB.add(mntmSQLite);
		
		JMenu mnSalir = new JMenu("Salir");
		menuBar.add(mnSalir);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mnSalir.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSalir.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBounds(10, 23, 195, 110);
		contentPane.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(10, 23, 79, 14);
		panelLogin.add(lblUsuario);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(99, 20, 86, 20);
		panelLogin.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contrasenia");
		lblContraseña.setBounds(10, 52, 79, 14);
		panelLogin.add(lblContraseña);
		
		textFieldContrasenia = new JTextField();
		textFieldContrasenia.setBounds(99, 51, 86, 20);
		panelLogin.add(textFieldContrasenia);
		textFieldContrasenia.setColumns(10);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciarSesion.setBounds(43, 76, 126, 23);
		panelLogin.add(btnIniciarSesion);
		
		JPanel panelCRUD = new JPanel();
		panelCRUD.setBounds(229, 23, 349, 284);
		contentPane.add(panelCRUD);
		panelCRUD.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 14, 46, 14);
		panelCRUD.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(73, 11, 86, 20);
		panelCRUD.add(textFieldNombre);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 43, 53, 14);
		panelCRUD.add(lblLocalidad);
		
		textFieldLocalidad = new JTextField();
		textFieldLocalidad.setColumns(10);
		textFieldLocalidad.setBounds(73, 40, 86, 20);
		panelCRUD.add(textFieldLocalidad);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(235, 10, 89, 23);
		panelCRUD.add(btnNuevo);
		
		btnListar = new JButton("Listar");
		btnListar.setBounds(235, 43, 89, 23);
		panelCRUD.add(btnListar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(10, 84, 89, 23);
		panelCRUD.add(btnModificar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(121, 84, 89, 23);
		panelCRUD.add(btnBorrar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(235, 84, 89, 23);
		panelCRUD.add(btnGuardar);
		
		
		JScrollPane scrollPane = new JScrollPane();//lo creo sobre la tabla
		scrollPane.setBounds(30, 133, 291, 129); //mismo tamaño que la tabla
		tableResultados = new JTable();
		tableResultados.setModel(modeloTbl); //Creo el modelo del panel por columnas
		Object[] identificadores = {"id", "nombre", "localidad"};
		modeloTbl.setColumnIdentifiers(identificadores);
		scrollPane.setViewportView(tableResultados);
		tableResultados.setFillsViewportHeight(true);
		
		tableResultados.setBounds(30, 133, 291, 129);
	
		
		
		panelCRUD.add(scrollPane);
		setVisible(true); 
	}
}

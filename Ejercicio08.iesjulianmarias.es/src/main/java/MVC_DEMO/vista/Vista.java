package MVC_DEMO.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel VistaGeneral;
	public JTextField textOp1; //boton y cuadros de texto publicos
	public JButton btnSumar;
	public JButton btnRestar;
	public TextField textResultado;
	public TextField textOp2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { //EJECUTAR LA CLASE APP, NO LA VISTA
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
		setTitle("MVC_CalculadoraSimple\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		VistaGeneral = new JPanel();
		VistaGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(VistaGeneral);
		VistaGeneral.setLayout(null);
		
		textOp1 = new JTextField();
		textOp1.setBounds(233, 29, 86, 20);
		VistaGeneral.add(textOp1);
		textOp1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Operador 1: ");
		lblNewLabel.setBounds(55, 32, 111, 14);
		VistaGeneral.add(lblNewLabel);
		
		textOp2 = new TextField();
		textOp2.setBounds(233, 78, 86, 20);
		VistaGeneral.add(textOp2);
		
		textResultado = new TextField();
		textResultado.setBounds(233, 124, 86, 20);
		VistaGeneral.add(textResultado);
		
		JLabel lblNewLabel_1 = new JLabel("Operador 2: \r\n");
		lblNewLabel_1.setBounds(55, 78, 76, 14);
		VistaGeneral.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Resultado: ");
		lblNewLabel_2.setBounds(55, 124, 111, 14);
		VistaGeneral.add(lblNewLabel_2);
		
		btnRestar = new JButton("Restar");
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRestar.setBounds(68, 194, 89, 23);
		VistaGeneral.add(btnRestar);
		
		btnSumar = new JButton("Sumar"); //doble click en el boton
		btnSumar.addActionListener(new ActionListener() { //Invocar al metodo desde el controlador
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSumar.setBounds(260, 194, 89, 23);
		VistaGeneral.add(btnSumar);
		setVisible(true); //IMPORTANTE PARA QUE SE VEA LA VENTANA DESDE LA CLASE APP
	}
}

package MVC_DEMO.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MVC_DEMO.modelo.Modelo;
import MVC_DEMO.vista.Vista;

public class Controlador implements ActionListener {
	private Modelo modelo;
	private Vista vista;
	

	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		vista.btnSumar.addActionListener(this);
		vista.btnRestar.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		modelo.setOperador1(Integer.parseInt(vista.textOp1.getText())); //obtengo los dos valores y los paso a enteros
		modelo.setOperador2(Integer.parseInt(vista.textOp2.getText()));
		if(e.getSource()==vista.btnSumar) {
			vista.textResultado.setText(String.valueOf(modelo.suma()));
			
		} else if (e.getSource()==vista.btnRestar) {
			vista.textResultado.setText(String.valueOf(modelo.resta()));			
		}
	}

}

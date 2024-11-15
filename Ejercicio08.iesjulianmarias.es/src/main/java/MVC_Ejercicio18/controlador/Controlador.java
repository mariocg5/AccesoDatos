package MVC_Ejercicio18.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import MVC_Ejercicio18.modelo.Departamento;
import MVC_Ejercicio18.modelo.Modelo;
import MVC_Ejercicio18.vista.Vista;

public class Controlador {
	private Modelo modelo;
	private Vista vista;

	public Controlador(Modelo modelo, Vista vista) {
		super();
		this.modelo = modelo;
		this.vista = vista;
		vista.btnNuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				limpiaControles();

			}
		});
		vista.btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.anadirDpto(vista.textFieldNombre.getText(), vista.textFieldLocalidad.getText());
				limpiaControles();
				rellenaTabla(); // cada vez que guardo un registro me lo actualiza en la tabla

			}

		});

		vista.btnListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rellenaTabla();
			}

		});

		vista.tableResultados.addMouseListener(new MouseAdapter() { // cuando hace click en la tabla ponemos los datos
																	// del nombre y localidad seleccionada

			@Override
			public void mouseClicked(MouseEvent e) {
				int filas = vista.tableResultados.getSelectedRow();
				vista.textFieldNombre.setText(vista.modeloTbl.getValueAt(filas, 1).toString());
				vista.textFieldLocalidad.setText(vista.modeloTbl.getValueAt(filas, 2).toString());
			}
		});

		vista.btnBorrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int filas = vista.tableResultados.getSelectedRow();
				modelo.borrarDpto(Integer.valueOf(vista.modeloTbl.getValueAt(filas, 0).toString()));
				rellenaTabla();
				limpiaControles();

			}
		});

		vista.btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int filas = vista.tableResultados.getSelectedRow();
				modelo.modificarDpto(Integer.valueOf(vista.modeloTbl.getValueAt(filas, 0).toString()),
						vista.textFieldNombre.getText(), vista.textFieldLocalidad.getText());
				rellenaTabla();
				limpiaControles();

			}
		});

	}

	private void rellenaTabla() {
		vista.modeloTbl.setNumRows(0); // No vuelve a poner la lista cuando se pulsa varias veces
		ArrayList<Departamento> dptoListado = modelo.listarDptos();
		for (Departamento dpto : dptoListado) {
			Object[] fila = { dpto.getDepNum(), dpto.getDepNombre(), dpto.getDepLocalidad() };
			vista.modeloTbl.addRow(fila);
		}

	}

	private void limpiaControles() {
		vista.textFieldNombre.setText(null);
		vista.textFieldLocalidad.setText(null);

	}
}

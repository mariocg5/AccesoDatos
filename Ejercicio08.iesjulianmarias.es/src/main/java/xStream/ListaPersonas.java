package xStream;

import java.util.ArrayList;

import ficheros.ejercicio6.Persona;

public class ListaPersonas {

	private ArrayList<Persona> lista = new ArrayList<Persona>();

	public ListaPersonas(ArrayList<Persona> lista) {
		super();
		this.lista = lista;
	}

	public ListaPersonas() {
		super();
	}

	public ArrayList<Persona> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Persona> lista) {
		this.lista = lista;
	}
	
	/**
	 * Método para añadir personas al xml
	 * @param persona
	 */
	public void anadir(Persona persona) {
		lista.add(persona);
	}
	
}

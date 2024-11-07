package jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jaxb.clasesEjercicio16.Examen;
import jaxb.clasesEjercicio16.Autor;
import utilidades.utilidades;

public class Ejercicio16 {
	private static JAXBContext jC;
	private static Examen examen;
	private static final String DOCUMENTO = "Ejercicio16 .xml";
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		unmarshalling();
		menu();
	}
	
	public static void unmarshalling() {
		
		try {
			jC = JAXBContext.newInstance(Examen.class);
			Unmarshaller uM = jC.createUnmarshaller();
			examen = (Examen) uM.unmarshal(new File(utilidades.RUTA+DOCUMENTO)); //definir ruta y doctrabajo		
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	public static void marshalling() {
		try {
			jC=JAXBContext.newInstance(Examen.class);
			Marshaller jM = jC.createMarshaller();
			jM.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jM.marshal(examen, new File(utilidades.RUTA+DOCUMENTO));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	private static void menu() {
        int opcion;
        boolean salir=true;
        while(salir) {
            System.out.println("Menu  Autores:");
            System.out.println("1. Mostrar autores");
            System.out.println("2. Añadir autor");
            System.out.println("3. Modificar autor");
            System.out.println("4. Eliminar autor");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
            case 1:
            	System.out.println("Leyendo autores");
            	mostrarAutores();
            	break;
            case 2:
            	System.out.println("Añadiendo autor");
            	anadirAutor();
            	break;
            case 3:
            	System.out.println("Modificando autor");
            	 modificarAutor();
            	 break;
            case 4:
            	System.out.println("Eliminar autores");
            	eliminarAutor();
            	break;
            case 5:
            	System.out.println("Saliendo del programa");
            	salir=false;
            	break;
            default:
            	System.out.println("Opción no válida");
            	break;
            }
        } 
    }
	
	
	private static void mostrarAutores() {	
		//usar iterador tambien
        System.out.println("\nLista de Autores:");
        for (Autor autor : examen.getListaAutor()) {
            System.out.printf("ID: %s, Nombre: %s %s %s, Puesto: %s, Entidad: %s%n",
                autor.getId(), autor.getNombre(), autor.getApellido1(), autor.getApellido2(),
                autor.getPuesto(), autor.getEntidadTrabajo());
        }
    }
	 private static void anadirAutor() {
	        System.out.print("Ingrese el ID del nuevo autor: ");
	        String id = sc.nextLine();
	        System.out.print("Nombre: ");
	        String nombre = sc.nextLine();
	        System.out.print("Primer Apellido: ");
	        String apellido1 = sc.nextLine();
	        System.out.print("Segundo Apellido: ");
	        String apellido2 = sc.nextLine();
	        System.out.print("Puesto: ");
	        String puesto = sc.nextLine();
	        System.out.print("Entidad de Trabajo: ");
	        String entidad = sc.nextLine();

	        Autor nuevoAutor = new Autor(id, nombre, apellido1, apellido2, puesto, entidad);
	        if (examen.getListaAutor() == null) {
	            examen.setListaAutor(new ArrayList<>());
	        }
	        examen.getListaAutor().add(nuevoAutor);
	        marshalling();
	        System.out.println("Autor añadido con éxito.");
	    }
	  private static void modificarAutor() {
	        System.out.print("Ingrese el ID del autor a modificar: ");
	        String id = sc.nextLine();
	        Autor autor = buscarAutor(id);

	        if (autor != null) {
	            System.out.print("Nuevo puesto: ");
	            String nuevoPuesto = sc.nextLine();
	            System.out.print("Nueva entidad de trabajo: ");
	            String nuevaEntidad = sc.nextLine();

	            autor.setPuesto(nuevoPuesto);
	            autor.setEntidadTrabajo(nuevaEntidad);
	            marshalling();
	            System.out.println("Autor modificado con éxito.");
	        } else {
	            System.out.println("No se encontró un autor con el ID especificado.");
	        }
	    }
	  private static void eliminarAutor() {
	        System.out.print("Ingrese el ID del autor a eliminar: ");
	        String id = sc.nextLine();
	        Autor autor = buscarAutor(id);

	        if (autor != null) {
	            examen.getListaAutor().remove(autor);
	            marshalling();
	            System.out.println("Autor eliminado con éxito.");
	        } else {
	            System.out.println("No se encontró un autor con el ID especificado.");
	        }
	    }
	  
	private static Autor buscarAutor(String id) {
		for (Autor autor : examen.getListaAutor()) {
            if (autor.getId().equalsIgnoreCase(id)) {
                return autor;
            }
        }
		return null;
	}
	
	
	
}

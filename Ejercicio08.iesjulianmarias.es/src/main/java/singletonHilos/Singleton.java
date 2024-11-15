package singletonHilos;

public class Singleton {
	private static Singleton instance; //1. variable privada de si mismo instanciada
	private String color;
	

	private Singleton(String color) { //tiene que ser privado
		super();
		this.color = color;
	}

	public static synchronized Singleton getInstance(String color) { //Metodo publico que llama al constructor
		if(instance==null) { //importante el synchronized para que los hilos se sincronicen
			instance=new Singleton(color);
		}
		return instance;
	}

	public String getColor() {
		return color;
	}

}

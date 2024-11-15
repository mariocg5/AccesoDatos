package singletonHilos;

public class PrincipalMultiHilo {
	
	static class hilo1 implements Runnable { //Creación del primer hilo

		@Override
		public void run() {
			Singleton singleton = Singleton.getInstance("Rojo");//Crea una instancia del singleton e imprime el valor de la propiedad del singleton
			System.out.println("Singleton de color: " + singleton.getColor());
		}
		
	}
	
	static class hilo2 implements Runnable { //Creación del segundo hilo

		@Override
		public void run() {
			Singleton singleton = Singleton.getInstance("Azul");//Crea una instancia del singleton e imprime el valor de la propiedad del singleton
			System.out.println("Singleton de color: " + singleton.getColor());
		}
		
	}

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new hilo1()); //Creación en el main de los hilos
		Thread hilo2 = new Thread(new hilo2());
		hilo1.start();
		hilo2.start();

	}

}

package com.summun.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.summun.client.entity.Game;
import com.summun.client.service.ServiceProxyGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class GamesClientApplication implements CommandLineRunner {
	
	@Autowired
	private ServiceProxyGame spg;
	
	@Autowired
	private ApplicationContext context;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		// Invocamos el metodo run
		SpringApplication.run(GamesClientApplication.class, args);
		// Al terminar el metodo run se cierra la aplicacion
		System.out.println("Cliente -> Fin de la aplicacion");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("        APLICACIÓN KKGAMES CLIENTE");
		System.out.println("-----------------------------------");

		// Bloque try, abre el escaner y lo cierra al terminar el bloque.
		try (Scanner sc = new Scanner(System.in)){
		
			System.out.println("Bienvenido a la aplicacion de KKgames");
			//--------------------- MENU --------------------------
			// boolean para controlar la salida del do while.
			boolean exitMenu = true;
			do {
				// Solicita al usuario que elija una opcion por consola.
				System.out.println(
						  "-----------------------------------\n"
						+ "Elija rapidito una de las opciones de menu:"
						+ "\n1-.Dar de alta un videojuego"
						+ "\n2-.Dar de baja un videojuego"
						+ "\n3-.Modificar un videojuego"
						+ "\n4-.Buscar un videojuego"
						+ "\n5-.Consultar el catalogo de KKgames"
						+ "\n0-.Por dios sacame de aqui esta maquina esta loca");

				// Lee la respuesta del usuario por consola.
				String answer = sc.nextLine();
				
				// Bucle que solicita un numero hasta que el valor introducido sea 1, 2 o 3.
				while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")
						&& !answer.equals("5") && !answer.equals("0")) {
					// Contador de tontunas a mas intentos aumenta el cabreo del servidor jajaj
					int cont=0;
					switch (cont){
						case 0: System.out.println("Tiene que escoger una de las opciones");
						case 2: System.out.println("Te has vuelto a equivocar!!!");
						case 3: System.out.println("Piensa bien antes de responder humano");
						case 4: System.out.println("¿Dislexia?");
						case 5: System.out.println("Te ha dado un iptus o es que tus padres son primos?");
						default: System.out.println("404 cerebro not found");
					}
					// Cada intento aumenta el contador de tonteria xD
					cont++;
					// Lee la respuesta del usuario por consola.
					answer = sc.nextLine();
				}
				
				//Respuesta del programa segun la eleccion del usuario.
				switch (answer) {
					case "1":
						// Informa de la eleccion del cliente
						System.out.println("Ha seleccionado dar de alta un videojuego");
						System.out.println("No cualquier cosa, solo aceptamos juegos bien toxicos");
						// Creamos un nuevo objeto de la clase Game sin argumentos
						Game g1 = new Game();
						// Solicita por consola el nombre del videojuego
						System.out.println("Introduce el nombre del videojuego");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g1.setName(sc.nextLine());
						// Solicita por consola el nombre de la compañia a la que pertenece
						System.out.println("Introduce el nombre de la compañia a la que pertenece");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g1.setCompany(sc.nextLine());
						// Solicita por consola el nombre de la compañia a la que pertenece
						System.out.println("Introduce la nota en JaviconCholas");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g1.setScore(Integer.parseInt(sc.nextLine()));
						// Al añadir el objeto nos devuelve el objeto añadido lo almacenamos para pintarlo por consola
						Game nGame = spg.addGame(g1);
						
						if (nGame != null) {
							System.out.println("Ocurrio un error al añadir el juego te he dicho que no toques nada...");
						}else {
							System.out.println("run -> Juego dado de alta " + nGame);
						}
						break;
						
					case "2":
						// Informa de la eleccion del cliente
						System.out.println("Ha seleccionado dar de baja un videojuego");
						System.out.println("Borra el fornite y veras la gente llorando en los foros muahhhah");
						// Solicita por consola el nombre del libro
						System.out.println("Introduce el ID del videojuego");
						// Lee nombre introducido por consola y lo almacena en la variable.
						long delId = Long.parseLong(sc.nextLine());
						// Devuelve un boolean y lo almacenamos en la variable
						boolean isDeleted = spg.deleteGameById(delId);
						
						if (isDeleted != true) {
							System.out.println("Ocurrio un error al eliminar el juego, ya la estas liando...");
						}else {
							System.out.println("run -> Juego dado de baja");
						}
						
						break;
						
					case "3":
						// Informa de la eleccion del cliente
						System.out.println("Ha seleccionado modificar un videojuego");
						System.out.println("Te estoy viiigilaaaandoooo, veo lo que haces...");
						// Creamos un nuevo objeto de la clase Game sin argumentos
						Game g2 = new Game();
						// Solicita por consola el nombre del libro
						System.out.println("Introduce el ID del videojuego");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g2.setId(Long.parseLong(sc.nextLine()));
						// Solicita por consola el nombre del videojuego
						System.out.println("Introduce el nombre del videojuego");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g2.setName(sc.nextLine());
						// Solicita por consola el nombre de la compañia a la que pertenece
						System.out.println("Introduce el nombre de la compañia a la que pertenece");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g2.setCompany(sc.nextLine());
						// Solicita por consola el nombre de la compañia a la que pertenece
						System.out.println("Introduce la nota en JaviconCholas");
						// Lee nombre introducido por consola y lo almacena en la variable.
						g2.setScore(Integer.parseInt(sc.nextLine()));
						// Al añadir el objeto nos devuelve el objeto añadido lo almacenamos para pintarlo por consola
						boolean isModify = spg.modifyGame(g2);
						
						if (isModify != true) {
							System.out.println("Ocurrio un error al modificar el juego, creo que me he liado...");
						}else {
							System.out.println("run -> Juego modificado");
						}
						
						break;
						
					case "4":
						// Informa de la eleccion del cliente
						System.out.println("Ha seleccionado buscar un videojuego");
						System.out.println("Busca Toby, busca");
						// Solicita por consola el nombre del libro
						System.out.println("Introduce el ID del videojuego");
						// Lee nombre introducido por consola y lo almacena en la variable.
						Game g3 = spg.getGamebyId(Integer.parseInt(sc.nextLine()));;
						if (g3 != null) {
							System.out.println("Ups tu juego no esta, se lo ha comido el ");
						}else {
							System.out.println("run -> Juego dado de alta " + g3);
						}
					
						break;
						
					case "5":
						// Informa de la eleccion del cliente
						System.out.println("Ha seleccionado consultar el catalogo");
						System.out.println("Mira todo lo que quieras goloso, pero sin tocar..");
						List<Game> gList = spg.toListGames(null);
						//Recorremos la lista y la imprimimos con funciones lambda
						gList.forEach((v) -> System.out.println(v));
						
						break;
						
					case "0":
						System.out.println("Ya ha descolocado todo?");
						System.out.println("adios agradable sujeto...cierre al salir.");
						exitMenu=true;
						break;
				}
				
				
			// Fin del do while, comprueba si el boolean es true.
			}while(exitMenu);
			stopApp();
		// Captura las posibles excepciones.	
		} catch (Exception e) {
			System.err.println("CLIENTE: Error -> " + e);
			e.printStackTrace();
		}
		
	}

	private void stopApp() {
		// Paramos el servidor web
		SpringApplication.exit(context, () -> 0);
	}

}

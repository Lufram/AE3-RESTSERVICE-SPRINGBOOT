package com.summun.launcher.server.model.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.summun.launcher.server.model.entity.Game;

@Component
public class DaoGame {
	// Lista de Games donde almacenaremos 
	public List<Game> gamesList;
	// Contador de Games
	public long count;
	
	// Constructor
	public DaoGame() {
		super();
		
		System.out.println("DaoGame -> Creando la lista de Games!");
		gamesList = new ArrayList<Game>();
		Game g1 = new Game(count++,"League of Trolls", "RiotGames", 8);//ID: 0
		Game g2 = new Game(count++,"Minecraft", "Mojang Studios", 7);//ID: 1
		Game g3 = new Game(count++,"Rise of Tomb Raider", "EIDOS", 9);//ID: 2
		Game g4 = new Game(count++,"Lary 7", "Sierra Entertainment", 11);//ID:3
		Game g5 = new Game(count++,"FF7", "Square Enix", 10);//ID:4
		gamesList.add(g1);
		gamesList.add(g2);
		gamesList.add(g3);
		gamesList.add(g4);
		gamesList.add(g5);
	}
	

	// METODOS

	/**
	 * Devuelve un juego a partir de su posicion del array
	 * @param posicion la posicion del arrya que buscamos
	 * @return el juego que ocupe en la posicion del array, null en caso de
	 * que no exista o se haya ido fuera de rango del array
	 */
	public Game get(int position) {
		try {
			return gamesList.get(position);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Juego fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que devuelve toda los juegos del array
	 * @return una lista con todas los juegos del array
	 */
	public List<Game> list() {
		return gamesList;
	}
	
	/**
	 * Metodo que introduce una Game al final de la lista
	 * @param p la Game que queremos introducir
	 */
	public void add(Game g) {
		g.setId(count++);
		gamesList.add(g);
	}
	
	/**
	 * Borramos una Game de una posicion del array
	 * @param posicion la posicion a borrar
	 * @return devolvemos la Game que hemos quitado del array, 
	 * o null en caso de que no exista.
	 */
	public Game delete(int posicion) {
		try {
			return gamesList.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Game fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que modifica un juego de una posicion del array
	 * @param p contiene todos los datos que queremos modificar, pero 
	 * p.getId() contiene la posicion del array que queremos eliminar
	 * @return la Game modificada en caso de que exista, null en caso
	 * contrario
	 */
	public Game update(Game g) {
		try {
			Game gAux = gamesList.get((int) g.getId());
			gAux.setName(g.getName());
			gAux.setCompany(g.getCompany());
			
			return gAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Game fuera de rango");
			return null;
		}
	}
	
	/**
	 * Metodo que devuelve todas las Games por nombre. Como puede
	 * haber varias Games con el mismo nombre (HARRY) tengo que
	 * devolver una lista con todas las encontradas
	 * @param nombre representa el nombre por el que vamos a hacer la
	 * busqueda
	 * @return una lista con las Games que coincidan en el nombre.
	 * La lista estará vacia en caso de que no hay coincidencias
	 */
	public List<Game> listByName(String name){
		List<Game> gamesListAux = new ArrayList<Game>();
		for(Game g : gamesList) {
			if(g.getName().equalsIgnoreCase(name)) {//contains()
				gamesListAux.add(g);
			}
		}
		return gamesListAux;
	}
}

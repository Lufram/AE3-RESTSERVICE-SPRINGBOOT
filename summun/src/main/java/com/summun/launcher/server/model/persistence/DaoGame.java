package com.summun.launcher.server.model.persistence;

import java.util.ArrayList;
import java.util.List;

import com.summun.launcher.server.model.entity.Game;

public class DaoGame {
	// Lista de Games donde almacenaremos 
	public List<Game> gamesList;
	// Contador de Games
	public int count;
	
	// Constructor
	public DaoGame() {
		super();
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
			Game gAux = gamesList.get(g.getId());
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
		for(Game p : gamesList) {
			if(p.getName().equalsIgnoreCase(name)) {//contains()
				gamesListAux.add(p);
			}
		}
		return gamesListAux;
	}
}

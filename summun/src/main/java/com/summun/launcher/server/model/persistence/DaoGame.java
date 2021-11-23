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
		
		System.out.println("DaoGame -> Creando la lista de Juegos!");
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
	 * Devuelve un juego a partir de su id
	 * @param id del juego
	 * @return el juego que ocupe en la posicion del array, null en caso de
	 * que no exista o se haya ido fuera de rango del array
	 */
	public Game get(int id) {
		try {
			Game g = null;
            for(Game n: gamesList) {
                if (id == n.getId()) {
                   g = n;
                   break;
                }
            }
            return g;
            
		} catch (IndexOutOfBoundsException e) {
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
	 * @param g la Game que queremos introducir
	 * @return si esta añadido o no
	 */
	public boolean add(Game g) {
		 for(Game n: gamesList) {
	        if (g.getName().trim().equalsIgnoreCase(n.getName())) {
	            g = null;
	        break;
	        }
	     }
	     if(g != null) {
	         g.setId(count++);
	         gamesList.add(g);
	         return true;
	     }else {
	         return false;
	     }
	}
	
	/**
	 * Borramos un Juego por su Id
	 * @param id del juego
	 * @return devolvemos la Game que hemos quitado del array, 
	 * o null en caso de que no exista.
	 */
	public boolean delete(int id) {
		try {
			Game g = null;
            for(Game n: gamesList) {
                if (id == n.getId()) {
                   g = n;
                   break;
                }
            }
           
			return gamesList.remove(g);
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("delete -> Game fuera de rango");
			return false;
		}
	}
	
	/**
	 * Metodo que modifica un juego de una posicion del array
	 * @param p contiene todos los datos que queremos modificar, pero 
	 * p.getId() contiene la posicion del array que queremos eliminar
	 * @return la Game modificada en caso de que exista, null en caso
	 * contrario
	 */
	public String update(Game g) {
		
		Game vUpdated = null;
	        String res = "";
	        for (Game n: gamesList) {
	            if(n.getId() == g.getId()) {
	                vUpdated = n;
	            }
	            if(n.getName().trim().equalsIgnoreCase(g.getName().trim())) {
	            	res = "Exist";
	                break;
	            }

	        }
	        try {
	            if(res.equalsIgnoreCase("Exist") ) {
	                System.out.println("Ya existe un videojuego con este nombre");
	                res = "Exist";
	            }else {
	                vUpdated.setName(g.getName());
	                vUpdated.setCompany(g.getCompany());
	                vUpdated.setScore(g.getScore());
	                res = "VideojuegoModificado";
	            }
	            return res;
	        } catch (NullPointerException e) {
	            System.out.println("No existe este videojuego");
	            return "NoExist";
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

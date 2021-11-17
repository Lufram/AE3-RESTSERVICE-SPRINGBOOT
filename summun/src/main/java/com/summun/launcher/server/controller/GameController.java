package com.summun.launcher.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.summun.launcher.server.model.entity.Game;
import com.summun.launcher.server.model.persistence.DaoGame;

@RestController
public class GameController {
	
	@Autowired
	private DaoGame daoGame;

	//------------GET PERSONA POR ID------------
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8086/games/ID" y el metodo a usar seria GET
	//ID sería el identificador que queremos buscar
	@GetMapping(path="games/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Game> getPersona(@PathVariable("id") int id) {
		System.out.println("Buscando persona con id: " + id);
		Game p = daoGame.get(id);
		if(p != null) {
			return new ResponseEntity<Game>(p,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//------------POST------------
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8086/games" y el metodo a usar seria POST
	//Pasandole la persona sin el ID dentro del body del HTTP request
	@PostMapping(path="games",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> addGame(@RequestBody Game g) {
		System.out.println("Alta juego: objeto game: " + g);
		daoGame.add(g);
		return new ResponseEntity<Game>(g,HttpStatus.CREATED);//201 CREATED
	}
	
	//------------GET LISTA PERSONAS------------
	//La URL para acceder a este metodo en caso de querer todas las personas
	//sería: 
	//"http://localhost:8086/games" y el metodo a usar seria GET
	//Si queremos filtrar por nombre entonces deberemos usar:
	//"http://localhost:8086/games?name=NOMBRE_A_FILTRAR"
	@GetMapping(path="games",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> listedGames(
			@RequestParam(name="name",required=false) String name) {
		List<Game> gamesList = null;
		//Si no me viene el nombre, devolvemos toda la lista
		if(name == null) {
			System.out.println("Listando las personas");
			gamesList = daoGame.list();			
		}else {
			System.out.println("Listando las personas por nombre: " + name);
			gamesList = daoGame.listByName(name);
		}
		System.out.println(gamesList);
		return new ResponseEntity<List<Game>>(gamesList,HttpStatus.OK);
	}
	//------------PUT------------
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8086/games/ID" y el metodo a usar seria PUT
	//Pasandole la persona sin el ID dentro del body del HTTP request
	@PutMapping(path="games/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> modificarPersona(
			@PathVariable("id") int id, 
			@RequestBody Game g) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + g);
		g.setId(id);
		Game pUpdate = daoGame.update(g);
		if(pUpdate != null) {
			return new ResponseEntity<Game>(HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	//------------DELETE------------
	//La URL para acceder a este metodo sería: 
	//"http://localhost:8086/games/ID" y el metodo a usar seria DELETE
	@DeleteMapping(path="games/{id}")
	public ResponseEntity<Game> deleteGame(@PathVariable int id) {
		System.out.println("ID a borrar: " + id);
		Game p = daoGame.delete(id);
		if(p != null) {
			return new ResponseEntity<Game>(p,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Game>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
}

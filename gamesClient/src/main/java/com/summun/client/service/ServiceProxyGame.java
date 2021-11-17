package com.summun.client.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.summun.client.entity.Game;

@Service
public class ServiceProxyGame {

	//La URL base del servicio REST de Games
		public static final String URL = "http://localhost:8086/games/";
		
		//Inyectamos el objeto de tipo RestTemplate que nos ayudara
		//a hacer las peticiones HTTP al servicio REST
		@Autowired
		private RestTemplate restTemplate;
		
		/**
		 * Metodo que obtiene una Game del servicio REST a partir de un id
		 * En caso de que el id no exita arrojaria una expcepcion que se captura
		 * para sacar el codigo de respuesta
		 * 
		 * @param id que queremos obtener
		 * @return retorna la Game que estamos buscando, null en caso de que la
		 * Game no se encuentre en el servidor (devuelva 404) o haya habido algun
		 * otro error.
		 */
		public Game getGamebyId(int id){
			try {
				ResponseEntity<Game> re = restTemplate.getForEntity(URL + id, Game.class);
				HttpStatus hs= re.getStatusCode();
				if(hs == HttpStatus.OK) {				
					return re.getBody();
				}else {
					System.out.println("Respuesta no contemplada");
					return null;
				}
			}catch (HttpClientErrorException e) {
				System.out.println("obtener -> La Game NO se ha encontrado, id: " + id);
			    System.out.println("obtener -> Codigo de respuesta: " + e.getStatusCode());
			    return null;
			}
		}
		
		/**
		 * Metodo que da de alta una Game en el servicio REST
		 * 
		 * @param p la Game que vamos a dar de alta
		 * @return la Game con el id actualizado que se ha dado de alta en el
		 * servicio REST. Null en caso de que no se haya podido dar de alta
		 */
		public Game addGame(Game p){
			try {
				ResponseEntity<Game> re = restTemplate.postForEntity(URL, p, Game.class);
				System.out.println("alta -> Codigo de respuesta " + re.getStatusCode());
				return re.getBody();
			} catch (HttpClientErrorException e) {
				System.out.println("alta -> La Game NO se ha dado de alta, id: " + p);
			    System.out.println("alta -> Codigo de respuesta: " + e.getStatusCode());
			    return null;
			}
		}
		
		/**
		 * 
		 * Modifica una Game en el servicio REST
		 * 
		 * @param p la Game que queremos modificar, se hara a partir del 
		 * id por lo que tiene que estar relleno.
		 * @return true en caso de que se haya podido modificar la Game. 
		 * false en caso contrario.
		 */
		public boolean modifyGame(Game p){
			try {
				restTemplate.put(URL + p.getId(), p, Game.class);
				return true;
			} catch (HttpClientErrorException e) {
				System.out.println("modificar -> El Game NO se ha modificado, id: " + p.getId());
			    System.out.println("modificar -> Codigo de respuesta: " + e.getStatusCode());
			    return false;
			}
		}
		
		/**
		 * 
		 * Borra una Game en el servicio REST
		 * 
		 * @param id el id de la Game que queremos borrar.
		 * @return true en caso de que se haya podido borrar la Game. 
		 * false en caso contrario.
		 */
		public boolean deleteGameById(long id){
			try {
				restTemplate.delete(URL + id);
				return true;
			} catch (HttpClientErrorException e) {
				System.out.println("borrar -> El Juego NO se ha borrado, id: " + id);
			    System.out.println("borrar -> Codigo de respuesta: " + e.getStatusCode());
			    return false;
			}
		}
		
		/**
		 * Metodo que devuelve todas las Games o todas las Games filtradas
		 * por nombre del web service
		 * 
		 * @param nombre en caso de ser distinto de null, devolvera el listado
		 * filtrado por el nombre que le hayamos pasado en este parametro. En caso
		 * de que sea null, el listado de las Games sera completo
		 * @return el listado de las Games segun el parametro de entrada o 
		 * null en caso de algun error con el servicio REST
		 */
		public List<Game> toListGames(String nombre){
			String queryParams = "";		
			if(nombre != null) {
				queryParams += "?nombre=" + nombre;
			}
			
			try {
				ResponseEntity<Game[]> response =
						  restTemplate.getForEntity(URL + queryParams,Game[].class);
				Game[] arrayGames = response.getBody();
				return Arrays.asList(arrayGames);
			} catch (HttpClientErrorException e) {
				System.out.println("listar -> Error al obtener la lista de Games");
			    System.out.println("listar -> Codigo de respuesta: " + e.getStatusCode());
			    return null;
			}
		}
	
}

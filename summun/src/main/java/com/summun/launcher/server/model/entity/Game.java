package com.summun.launcher.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Game {
	
	private long id;
	private String name;
	private String company;
	private int score;
	
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", company=" + company + ", score=" + score + "]";
	}
	

	
	
}

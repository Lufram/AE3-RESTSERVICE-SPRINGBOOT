package com.summun.launcher.server.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class Game {
	
	private long id;
	private String name;
	private String company;
	private int score;
	
	public Game() {
		super();
	}
	
	public Game(long id, String name, String company, int score) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", company=" + company + ", score=" + score + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}

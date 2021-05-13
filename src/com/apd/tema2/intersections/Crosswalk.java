package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class Crosswalk implements Intersection {
	// time = timpul cat cat inca vin pietoni

	// nr = numarul maxim de pietoni pe care semaforul
		// il permite sa treaca

	// s = semafor de 1
	public int time;
	public int nr;
	public Semaphore s = new Semaphore(1);
	public Crosswalk(int time, int nr) {
		this.time = time;
		this.nr = nr;
	}
}

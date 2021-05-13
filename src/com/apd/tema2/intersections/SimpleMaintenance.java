package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleMaintenance implements Intersection {

	// nrAllowed = nr de masini permis in acelasi
		// timp dintr-un sens

	// s0 = semafor pt sensul 0, initializat cu nrAllowed
	// s1 = semafor pt sensul 1, initilizat cu 0
	// barrier = bariera de valoare nrAllowed

	public int nrAllowed;
	public Semaphore s0;
	public Semaphore s1;
	public CyclicBarrier barrier;
	public SimpleMaintenance(int nrAllowed) {
		this.nrAllowed = nrAllowed;
		s0 = new Semaphore(nrAllowed);
		s1 = new Semaphore(0);
		barrier = new CyclicBarrier(nrAllowed);
	}
}

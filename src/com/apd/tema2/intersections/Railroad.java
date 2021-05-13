package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Railroad implements Intersection {
	// queue0 = coada cu masini de pe sensul 0
	// queue1 = caoda cu masini de pe sensul 1
	// barrier = bariera pt toate masinile
	// s = semafor de valoare 1

	public ArrayBlockingQueue queue0;
	public ArrayBlockingQueue queue1;
	public CyclicBarrier barrier = new CyclicBarrier(Main.carsNo);
	public Semaphore s = new Semaphore(1);

	public Railroad() {
		queue0 = new ArrayBlockingQueue(Main.carsNo);
		queue1 = new ArrayBlockingQueue(Main.carsNo);
	}
}

package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class PriorityIntersection implements Intersection {
	// nrlow = nr de masini fata prioritate
	// nrhigh = nr de masini cu prioritate
	// queueLow = coada ce contine masini fara prioritate
	// queueHigh = coada ce contine masini cu prioritate
	// s = semafor de 1
	public int nrlow;
	public int nrhigh;
	public ArrayBlockingQueue queueLow ;
	public ArrayBlockingQueue queueHigh ;
	public Semaphore s = new Semaphore(1);

	public PriorityIntersection(int nrhigh, int nrlow) {
		this.nrlow = nrlow;
		this.nrhigh = nrhigh;
		queueLow = new ArrayBlockingQueue<Car>(nrlow);
		queueHigh = new ArrayBlockingQueue<Car>(nrhigh);
	}
}

package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleMaxXCar implements Intersection {

	// n = nr de directii
	// t = timpul de asteptare in giratoriu
	// x = nr de masini care pot intra in acelasi timp
	// s = lista de semafoare de valoare x

	public int n;
	public int t;
	public int x;
	public ArrayList<Semaphore> s = new ArrayList<>();

	public SimpleMaxXCar(int n, int t, int x) {
		this.n = n;
		this.t = t;
		this.x = x;
		for(int i = 0; i<n;i++) {
			s.add(i, new Semaphore(x));
		}

	}
}

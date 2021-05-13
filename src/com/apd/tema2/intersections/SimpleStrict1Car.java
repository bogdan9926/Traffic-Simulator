package com.apd.tema2.intersections;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SimpleStrict1Car implements Intersection {
	// n = nr de directii
	// t = timpul de asteptare in giratoriu
	// s = lista de semafoare de valoare 1
	public int n;
	public int t;
	public ArrayList<Semaphore> s = new ArrayList<>();
	public SimpleStrict1Car(int n, int t) {
		this.n = n;
		this.t = t;
		for(int i = 0; i<n;i++) {
			s.add(i, new Semaphore(1));
		}
	}
}

package com.apd.tema2.intersections;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleNRoundabout implements Intersection {
	// n = nr de masini care intra la un moment dat
	// t = timpul de asteptare in giratoriu
	// s = semafor de valoare n
	public int n;
	public int t;
	public Semaphore s;
	public SimpleNRoundabout(int n, int t) {
		this.n = n;
		this.t = t;
		s = new Semaphore(n);
	}
}

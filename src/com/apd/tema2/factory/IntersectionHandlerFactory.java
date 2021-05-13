package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;
import com.apd.tema2.intersections.*;
import com.apd.tema2.utils.Constants;

import java.util.concurrent.BrokenBarrierException;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {

    public static IntersectionHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of
        // them)
        // road in maintenance - 2 ways 1 lane each, X cars at a time
        // road in maintenance - 1 way, M out of N lanes are blocked, X cars at a time
        // railroad blockage for s seconds for all the cars
        // unmarked intersection
        // cars racing
        return switch (handlerType) {
            case "simple_semaphore" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 1
					System.out.println("Car "+ car.getId() + " has reached the semaphore, " +
							                   "now waiting...");
	                try {
		                sleep(car.getWaitingTime());
	                } catch (InterruptedException e) {
		                e.printStackTrace();
	                }
	                System.out.println("Car " + car.getId() + " has waited enough, now driving...");

                }
            };
            case "simple_n_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 2
					SimpleNRoundabout inter = (SimpleNRoundabout) Main.intersection;

						try {
							System.out.println("Car "+ car.getId() + " has reached the " +
									                   "roundabout, now waiting...");
							// semafor pt cele n masini
							inter.s.acquire();


							System.out.println("Car "+ car.getId() + " has entered the roundabout");
							// sleep t secunde
							sleep(inter.t);
							System.out.println("Car "+ car.getId() +" has exited the roundabout after "
									                   + inter.t + " seconds");
							inter.s.release();

						} catch (InterruptedException e) {
							e.printStackTrace();
						}

                }
            };
            case "simple_strict_1_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
					// ex 3
                	SimpleStrict1Car inter = (SimpleStrict1Car) Main.intersection;
	                System.out.println("Car "+ car.getId() + " has reached the roundabout");

	                try {
	                	    //semafor de 1 pentru fiecare directie
			                inter.s.get(car.getStartDirection()).acquire();

			                System.out.println("Car "+ car.getId() + " has entered the roundabout from " +
					                                   "lane " + car.getStartDirection());
			                // sleep t secunde
			                sleep(inter.t);
			                System.out.println("Car "+ car.getId() +" has exited the roundabout after " +
					                                   inter.t + " seconds");
			                inter.s.get(car.getStartDirection()).release();

		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }

                }
            };
            case "simple_strict_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
					// ex 4
	                SimpleStrictXCar inter = (SimpleStrictXCar) Main.intersection;
	                System.out.println("Car "+ car.getId() + " has reached the roundabout, now waiting...");

	                try {
	                	// bariera pt a se scrie prima oara mesajele de reached
		                inter.barrierAfterReach.await();
	                } catch (InterruptedException e) {
		                e.printStackTrace();
	                } catch (BrokenBarrierException e) {
		                e.printStackTrace();
	                }
	                try {
			                try {
			                	// semafor de x pentru fiecare directie
				                inter.s.get(car.getStartDirection()).acquire();
				                System.out.println("Car "+ car.getId() + " was selected to enter the " +
						                                   "roundabout from lane " + car.getStartDirection());
				                // bariera pt a se scrie mesajele cu selected
				                inter.barrier.await();
			                } catch (BrokenBarrierException e) {
				                e.printStackTrace();
			                }


			                System.out.println("Car "+ car.getId() + " has entered the roundabout from lane" +
					                                   " " + car.getStartDirection());

							// sleep t secunde cand masina e in giratoriu
			                sleep(inter.t);

			                try {
			                	// bariera pt a se scrie mesajele cu entered
				                inter.barrier.await();
				                System.out.println("Car "+ car.getId() +" has exited the roundabout after "
						                                   + inter.t + " seconds");
			                } catch (BrokenBarrierException e) {
				                e.printStackTrace();
			                }

			                inter.s.get(car.getStartDirection()).release();

		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }


                }
            };
            case "simple_max_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 5
                    // Get your Intersection instance
	                SimpleMaxXCar inter = (SimpleMaxXCar) Main.intersection;
                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

	                try {
		                System.out.println("Car "+ car.getId() + " has reached the roundabout from lane "
				                                   + car.getStartDirection());
		                // semafor de x pt fiecare directie
		                inter.s.get(car.getStartDirection()).acquire();
		                System.out.println("Car "+ car.getId() + " has entered the roundabout from lane "
				                                   + car.getStartDirection());

		                // sleep t secunde cand masina e in giratoriu
		                sleep(inter.t);

		                System.out.println("Car "+ car.getId() +" has exited the roundabout after "
				                                   + inter.t + " seconds");

		                inter.s.get(car.getStartDirection()).release();

	                } catch (InterruptedException e) {
		                e.printStackTrace();
	                }
                }
            };
            case "priority_intersection" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 6
                    // Get your Intersection instance
					PriorityIntersection inter = (PriorityIntersection) Main.intersection;
                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

	                // masinile din lane 1 sunt adaugate in queue fara prioritate
	                if(car.getPriority()==1) {
		                inter.queueLow.add(car);
		                System.out.println("Car "+ car.getId()+" with low priority is " +
				                                   "trying to enter the intersection...");

		                // se asteapta pana cand in intersectie nu mai sunt masini
		                // cu prioritate
		                while(!inter.queueHigh.isEmpty()) {}

		                // daca nu sunt in intersectie masini cu prioritate
		                if(inter.queueHigh.isEmpty()) {
			                try {
			                	// semafor pt ca operatia de poll sa nu se faca in paralel
				                inter.s.acquire();
			                } catch (InterruptedException e) {
				                e.printStackTrace();
			                }
			                // se scoate varful cozii
			                Car auxLow = (Car) inter.queueLow.poll();
			                System.out.println("Car "+ auxLow.getId()+" with low priority " +
					                                   "has entered the intersection");
			                inter.s.release();


		                }

	                }
					//daca masina are prioritate
	                else {
						//se adauga in coada cu prioritate
		                inter.queueHigh.add(car);


	                	System.out.println("Car "+ car.getId()+" with high priority has " +
				                                   "entered the intersection");
		                try {
		                	// sleep 2000ms pt fiecare masina cu prioritate
			                sleep(2000);
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }
		                System.out.println("Car "+ car.getId()+" with high priority has " +
				                                   "exited the intersection");

		                //se scoate din coada
		                inter.queueHigh.poll();

	                }

                }
            };
            case "crosswalk" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 7
					Crosswalk inter = (Crosswalk) Main.intersection;
					// 2 variabile ce tin evidenta la ultimul mesaj
					boolean wasGreen=false, wasRed=false;
					Pedestrians pedestrians = (Pedestrians) Main.pedestrians;

					while(!pedestrians.isFinished()) {
						// daca nu sunt pietoni
						if(!pedestrians.isPass() && !wasGreen) {

							System.out.println("Car "+ car.getId() + " has now green light");
							wasGreen = true;
							wasRed = false;
						}


						// daca sunt pietoni
						if(pedestrians.isPass() && !wasRed) {

							System.out.println("Car "+ car.getId() + " has now red light");
							wasGreen = false;
							wasRed = true;
						}

					}
					// am observat ca uneori nu toate masinile apuca sa trimita ultimul mesaj
	                // in cazul acesta il fortez
					if(!wasGreen)
						System.out.println("Car "+ car.getId() + " has now green light");


                }
            };
            case "simple_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                	// ex 8
                	SimpleMaintenance inter = (SimpleMaintenance) Main.intersection;

	                System.out.println("Car "+ car.getId()+" from side number " +
			                                   car.getStartDirection() + " has reached the bottleneck");

	                // daca masina vine din directia 0
	                if(car.getStartDirection()==0) {

		                try {
		                	// semaforul s0 care initial are valoarea = nr de masini permise in
			                // acelasi timp de pe un sens
			                inter.s0.acquire();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }

		                System.out.println("Car "+ car.getId()+" from side number 0 has passed " +
				                                   "the bottleneck");
		                try {
		                	// bariera cu valoarea = nr de masini permise in acelasi timp pe un sens
			                inter.barrier.await();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                } catch (BrokenBarrierException e) {
			                e.printStackTrace();
		                }
		                // se creste semaforul de pe sensul opus, initializat la 0
		                // se va creste cu nr, avand bariera mai sus
		                inter.s1.release();

	                }
					//analog ca mai sus , schimband s0 cu s1 si invers
	                if(car.getStartDirection()==1) {

		                try {
			                inter.s1.acquire();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }

		                System.out.println("Car "+ car.getId()+" from side number 1 has passed " +
				                                   "the bottleneck");
		                try {
			                inter.barrier.await();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                } catch (BrokenBarrierException e) {
			                e.printStackTrace();
		                }
		                inter.s0.release();

	                }

                }
            };
            case "complex_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {

                    
                }
            };
            case "railroad" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
					// ex 10
					Railroad inter = (Railroad) Main.intersection;
					System.out.println("Car " + car.getId() +" from side number "+ car.getStartDirection()
							                   +" has stopped by the railroad");
					// adaugare intr-o coada pentru fiecare sens
					if(car.getStartDirection() ==0 ) inter.queue0.add(car);
					else inter.queue1.add(car);
	                try {
	                	// bariera pt toate masinile
		                inter.barrier.await();
		                if(car.getId()==0) {
			                System.out.println("The train has passed, cars can now proceed");
		                }
		                // bariera pt toate masinile
		                inter.barrier.await();
	                } catch (InterruptedException e) {
		                e.printStackTrace();
	                } catch (BrokenBarrierException e) {
		                e.printStackTrace();
	                }

	                // pt masinile din directia 0
	                if(car.getStartDirection() ==0 ) {
		                try {
		                	// semafor de valoare 1 pt operatia de poll
			                inter.s.acquire();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }
		                // se scoate capul cozii
		                Car aux = (Car) inter.queue0.poll();
		                System.out.println("Car " + aux.getId() +
				                                   " from side number 0 has started driving");
		                inter.s.release();
	                }
					// analog ca mai sus, dar pt sensul celalalt
	                else {
		                try {
			                inter.s.acquire();
		                } catch (InterruptedException e) {
			                e.printStackTrace();
		                }
		                Car aux = (Car) inter.queue1.poll();
		                System.out.println("Car " + aux.getId() +
				                                   " from side number 1 has started driving");
		                inter.s.release();
	                }

                }
            };
            default -> null;
        };
    }
}

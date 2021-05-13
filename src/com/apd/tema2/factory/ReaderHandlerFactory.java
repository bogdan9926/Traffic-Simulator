package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
        return switch (handlerType) {
            case "simple_semaphore" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) {
                    // Exemplu de utilizare:
                    // Main.intersection = IntersectionFactory.getIntersection("simpleIntersection");
                }
            };
            case "simple_n_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    // To parse input line use:
                     String[] line = br.readLine().split(" ");
                     int n = Integer.parseInt(line[0]);
                     int t = Integer.parseInt(line[1]);
	                 Main.intersection = new SimpleNRoundabout(n,t);//IntersectionFactory.getIntersection("SimpleNRoundabout");
                }
            };
            case "simple_strict_1_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

	                String[] line = br.readLine().split(" ");
	                int n = Integer.parseInt(line[0]);
	                int t = Integer.parseInt(line[1]);
	                Main.intersection = new SimpleStrict1Car(n,t);
                }
            };
            case "simple_strict_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

	                String[] line = br.readLine().split(" ");
	                int n = Integer.parseInt(line[0]);
	                int t = Integer.parseInt(line[1]);
	                int x = Integer.parseInt(line[2]);
	                Main.intersection = new SimpleStrictXCar(n,t,x);
                }
            };
            case "simple_max_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
	                String[] line = br.readLine().split(" ");
	                int n = Integer.parseInt(line[0]);
	                int t = Integer.parseInt(line[1]);
	                int x = Integer.parseInt(line[2]);
	                Main.intersection = new SimpleMaxXCar(n,t,x);
                }
            };
            case "priority_intersection" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
	                String[] line = br.readLine().split(" ");
	                int n = Integer.parseInt(line[0]);
	                int t = Integer.parseInt(line[1]);
	                Main.intersection = new PriorityIntersection(n,t);
                }
            };
            case "crosswalk" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
	                String[] line = br.readLine().split(" ");
	                int EXECUTE_TIME = Integer.parseInt(line[0]);
	                int MAX_NO_OF_PEDESTRIANS = Integer.parseInt(line[1]);
	                Main.pedestrians = new Pedestrians(EXECUTE_TIME,MAX_NO_OF_PEDESTRIANS);
					Main.intersection = new Crosswalk(EXECUTE_TIME,MAX_NO_OF_PEDESTRIANS);
                }
            };
            case "simple_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
	                String[] line = br.readLine().split(" ");
	                int x = Integer.parseInt(line[0]);
	                Main.intersection = new SimpleMaintenance(x);
                }
            };
            case "complex_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    
                }
            };
            case "railroad" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    Main.intersection = new Railroad();
                }
            };
            default -> null;
        };
    }

}

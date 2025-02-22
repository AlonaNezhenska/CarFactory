package car;

import car.Car;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
//solid
//single responsibility
public class Race {
    private final Map<Integer, Car> carMap = new HashMap<>();
    private final Map<Car, Double> trackPositions = new HashMap<>();
    private final int raceDistance;
    private final Car[] cars;
    private volatile boolean finishRequested = false;

    public Race(Car[] cars, int raceDistance) {
        this.cars = cars;
        this.raceDistance = raceDistance;
        // Initialisation of Maps
        for (int i = 0; i < cars.length; i++) {
            carMap.put(i + 1, cars[i]);
            trackPositions.put(cars[i], 0.0);
        }
    }




    public int startRace() throws InterruptedException, IOException {
        System.out.println("Starting race!");
        System.out.println("Type 'finish' if ypu want to end race and   'info <N of Car>' for current car info.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean raceFinished = false;
        int winnerCarNumber = -1;

        // loop
        while (!raceFinished) {
            // 10 sec
            Thread.sleep(10000);


            updatePositions();

            // current info
            printCarsInfo();

            // input processing
            while (reader.ready()) {
                String input = reader.readLine().trim();
                if (input.equalsIgnoreCase("finish")) {

                    finishRequested = true;
                } else if (input.toLowerCase().startsWith("info")) {
                    String[] parts = input.split("\\s+");
                    if (parts.length >= 2) {
                        try {
                            int carNum = Integer.parseInt(parts[1]);
                            Car car = carMap.get(carNum);
                            if (car != null) {
                                double pos = trackPositions.get(car);
                                System.out.printf("Car Info %d: %s, distance %.2f км%n", carNum, car, pos);
                            } else {
                                System.out.println("No such car number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong Formatting. Use: info <номер>");
                        }
                    }
                }
            }

            // Check if car is on the finish distance
            for (Map.Entry<Integer, Car> entry : carMap.entrySet()) {
                if (trackPositions.get(entry.getValue()) >= raceDistance) {
                    raceFinished = true;
                    winnerCarNumber = entry.getKey();
                    break;
                }
            }
            // if "finish" command clicked -> choose as winner car with biggesr distance
            if (finishRequested) {
                raceFinished = true;
                winnerCarNumber = getLeaderCarNumber();
            }
        }
        return winnerCarNumber;
    }

    // update of car positions
    // each car - each thread
    //Race in ran 1 time in main, Race gave distance
    // race thread calls each car and ask how mush have you gone
    //each car tells race when it had arrive - put event in map (cunccurent hash map)

    private void updatePositions() {
        for (Car car : cars) {
            double currentPos = trackPositions.get(car);
            // 10 sec for calculating the distance of cars each 10 sec
            double timeIntervalHours = 10.0 / 3600.0;
            double newPos = currentPos + car.getSpeed() * timeIntervalHours;
            trackPositions.put(car, newPos);
        }
    }

    // output of current info for each car
    private void printCarsInfo() {
        System.out.println("----- Race results -----");
        for (Map.Entry<Integer, Car> entry : carMap.entrySet()) {
            double pos = trackPositions.get(entry.getValue());
            System.out.printf("Car %d: has gone %.2f км%n", entry.getKey(), pos);
        }
        System.out.println("---------------------------");
    }

    // car with the biggest distance gone (leader)
    private int getLeaderCarNumber() {
        Car leader = null;
        double maxDistance = -1;
        for (Car car : cars) {
            double pos = trackPositions.get(car);
            if (pos > maxDistance) {
                maxDistance = pos;
                leader = car;
            }
        }
        int leaderCarNumber = -1;
        for (Map.Entry<Integer, Car> entry : carMap.entrySet()) {
            if (entry.getValue() == leader) {
                leaderCarNumber = entry.getKey();
                break;
            }
        }
        return leaderCarNumber;
    }
}

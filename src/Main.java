import car.Car;
import car.CarFactory;
import car.Race;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Random random = new  Random();


        Car[] cars = new Car[3];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = CarFactory.createCar1();
            cars[i].setSpeed(random.nextInt(200) + 100); // Случайная скорость от 100 до 299 км/ч
        }


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Distance (кm): ");
        int raceDistance = scanner.nextInt();

        Race race = new Race(cars, raceDistance);
        int winnerCarNumber = race.startRace();

        System.out.printf("Race Finished! The winner is: %d.%n", winnerCarNumber);
    }
}

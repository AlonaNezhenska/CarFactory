package car;

public class CarFactory {
    public static Car createCar1() {
        //add method getWheel(diameter18)
        //conditioner - optional

        Car car = new Car(Car.Color.RED, "Toyota", " ", 300);

        // Add wheels
        car.addWheels(new Wheel(18), new Wheel(18), new Wheel(18), new Wheel(18));

        // engine
        car.changeEngine(new Engine("V6", "2025-01-01", 200));

        //  conditioner
        //car.changeConditioner(new Conditioner("Deluxe", "cool"));

        return car;
    }
}

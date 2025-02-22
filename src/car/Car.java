package car;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Car  extends Thread{

    public enum Color {
        RED, BLUE, BLACK
    }

    private Color color;
    private String brand;
    private String dashboard;
    private int speed;
    private Engine engine;
    private Conditioner conditioner;
    private final List<Wheel> wheels = new ArrayList<>();

    // constructor
    public Car(Color color, String brand, String dashboard, int speed) {
        this.color = color;
        this.brand = brand;
        this.dashboard = dashboard;
        this.speed = speed;
    }

    // Add wheels one by one
    private void addWheel(Wheel wheel) {
        if (wheel.getDiameter() <= 20) {
            this.wheels.add(wheel);
        } else {
            System.out.println("car.Wheel diameter exceeds the limit (20 inches).");
        }
    }

    //  add multiple wheels
    void addWheels(Wheel... wheels) {
        for (Wheel w : wheels) {
            addWheel(w);
        }
    }

    // Assign engine
    public void changeEngine(Engine engine) {
        this.engine = engine;
    }

    // Assign conditioner
    public void changeConditioner(Conditioner conditioner) {
        this.conditioner = conditioner;
    }

    // Custom toString to fit your desired format
    @Override
    public String toString() {
        String wheelsInfo = !wheels.isEmpty() ?
                String.format("%d, diameter of wheels = %d", wheels.size(), wheels.getFirst().getDiameter())
                : "0";

        // engine’s power is  in the durability field
        String engineInfo = (engine != null) ? "Durability: " + engine.getDurability() : "No engine";
        String conditionerInfo = (conditioner != null) ? "Type: " + conditioner.getType() : "No conditioner";

        return String.format(
                "car.Car [Color: %s, Brand: %s, Wheels: %s, Dashboard: %s car.Engine: %s, car.Conditioner: %s, Speed: %d km/h]",
                color, brand, wheelsInfo, dashboard, engineInfo, conditionerInfo, speed
        );

    }
}
package car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Engine {
    private String engineBrand;
    private String oilChange;
    private int durability;

    public Engine(String engineBrand, String oilChange, int durability) {
        this.engineBrand = engineBrand;
        this.oilChange = oilChange;
        this.durability = durability;
    }
}

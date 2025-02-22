package car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wheel {
    private int diameter;

    public Wheel(int diameter) {
        this.diameter = diameter;
    }
}

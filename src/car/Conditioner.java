package car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Conditioner {
    private String conditionerBrand;
    private String type;

    public Conditioner(String conditionerBrand, String type) {
        this.conditionerBrand = conditionerBrand;
        this.type = type;
    }
}

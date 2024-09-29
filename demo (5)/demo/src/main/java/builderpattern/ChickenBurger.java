package builderpattern;

import org.springframework.context.annotation.Bean;

public class ChickenBurger extends Burger{
    @Override
    public float price(){
        return 50.0f;
    }
    @Override
    public String name(){
        return "Chicken Burger";
    }
}

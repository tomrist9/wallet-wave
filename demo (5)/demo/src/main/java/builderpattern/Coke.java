package builderpattern;

public class Coke extends ColdDrink{
    @Override
    public float price(){
        return 3f;

    }
    @Override
    public String name() {
        return "Coke";
    }
}

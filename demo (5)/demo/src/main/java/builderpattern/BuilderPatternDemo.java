package builderpattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        MealBuilder mealBuilder=new MealBuilder();
        Meal vegMeal= mealBuilder.prepareVegMeal();
        System.out.println("Veg meal");
        vegMeal.showItems();
        System.out.println("Total cost:"+ vegMeal.getCost());

        Meal nonVegmeal= mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegmeal.showItems();
        System.out.println("Total Cost: " + nonVegmeal.getCost());

    }
}

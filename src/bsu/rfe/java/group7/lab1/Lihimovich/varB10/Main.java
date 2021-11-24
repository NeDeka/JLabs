package bsu.rfe.java.group7.lab1.Lihimovich.varB10;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Food[] breakfast = new Food[20];
        int itemsSoFar = 0;
        boolean sort_needed = false, calories_needed = false;
        int cheeseN = 0, LimanadN = 0, appleN = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-calories"))
                calories_needed = true;
            if (args[i].equals("-sort"))
                sort_needed = true;
            else {
                String[] parts = args[i].split("/");
                if (parts[0].equals("Сыр")) {
                    breakfast[itemsSoFar] = new Cheese();
                    cheeseN++;
                    itemsSoFar++;
                } else if (parts[0].equals("Яблоко")) {
                    breakfast[itemsSoFar] = new Apple(parts[1]);
                    appleN++;
                    itemsSoFar++;
                } else if (parts[0].equals("Лимонад")) {
                    breakfast[itemsSoFar] = new Limonad(parts[1]);
                    LimanadN++;
                    itemsSoFar++;
                }
            }
        }
        for (Food item: breakfast)
            if (item!=null)
                item.consume();
            else
                break;

        if (calories_needed) {
            int calories = 0;
            for (int i = 0; i < itemsSoFar; i++)
                calories += breakfast[i].calculateCalories();
            System.out.println("\nОбщая калорийность завтрака: " + calories + "ккал");
        }

        if(sort_needed) {
            Arrays.sort(breakfast, new Comparator() {
                public int compare(Object f1, Object f2) {

                    if(f1 instanceof  Limonad && f2 instanceof Limonad){
                        return ((Limonad)f1).getTaste().compareTo(((Limonad)f2).getTaste());
                    }
                    if(f1 instanceof  Apple && f2 instanceof Apple){
                        return ((Apple)f1).getSize().compareTo(((Apple)f2).getSize());
                    }
                    if(f1 instanceof  Limonad && f2 instanceof Apple){
                        return ((Limonad)f1).getTaste().compareTo(((Apple)f2).getSize());
                    }
                    if(f1 instanceof  Apple && f2 instanceof Limonad){
                        return ((Apple)f1).getSize().compareTo(((Limonad)f2).getTaste());}
                    if(f1 instanceof Cheese || f2 instanceof Cheese) return -1;
                    return 0;
                }
            });
        }
        
        System.out.println("\nКол-во яблок:" + " " + appleN);
        System.out.println("Кол-во сыров:" + " " + cheeseN);
        System.out.println("Кол-во лимоннадов:" + " " + LimanadN);

        System.out.println("\nОтсортированные продукты: ");
        for (int i = 0; i < breakfast.length; i++) {
            if (breakfast[i] == null)
                continue;
            System.out.println(breakfast[i].toString() + " " + breakfast[i].calculateCalories() + "ккал");
        }

        System.out.println("\nСъедено продуктов: " + itemsSoFar);
        System.out.println("\nВсего хорошего!");
    }
}

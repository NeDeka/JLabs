package bsu.rfe.java.group7.lab1.Lihimovich.varB10;

public class Cheese extends Food {
    public Cheese() {
        super("Сыр");
    }
    public void consume() {
        System.out.println(this + " съеден");
    }
    public int calculateCalories()
    {
        return 402;
    }
}

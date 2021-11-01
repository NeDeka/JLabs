package bsu.rfe.java.group7.lab1.Lihimovich.varB10;

public class Limonad extends Food {
    private String taste;
    public Limonad(String taste){
        super("Лимонад");
        this.taste = taste;
    }
    public void consume() {
        System.out.println(this + " выпит");
    }
    public void setTaste(String taste){
        this.taste = taste;
    }
    public String getTaste()
    {
        return taste;
    }

    public int calculateCalories(){
        int calories = 0;
        if (!taste.equals("лимон")) {
            if(taste.equals("апельсин"))
            {
                calories+=36;
            }
            else if(taste.equals("клубнмка"))
            {
                calories+=41;
            }
        } else {
            calories+=16;
        }
        return calories;
    }
    public boolean equals(Object arg0) {
        if (super.equals(arg0)) { // Шаг 1
            if (!(arg0 instanceof Limonad)) return false; // Шаг 2
            return taste.equals(((Limonad)arg0).taste); // Шаг 3
        } else
            return false;
    }
    public String toString() {
        return super.toString() + " со вкусом '" + taste.toUpperCase() + "'";
    }
}

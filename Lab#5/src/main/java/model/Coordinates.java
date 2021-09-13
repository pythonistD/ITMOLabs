package model;

/**
 * Координаты Дракона
 */
public class Coordinates {
    private double x; //Поле не может быть null
    private double y; //Значение поля должно быть больше -923, Поле не может быть null\\

    public Coordinates(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

}

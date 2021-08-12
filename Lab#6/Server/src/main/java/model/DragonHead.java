package model;

/**
 * Голова Дракона
 */
public class DragonHead {
    private double toothCount;

    public DragonHead(double toothCount) {
        this.toothCount = toothCount;
    }


    public double getToothCount() {
        return toothCount;
    }

    public void setToothCount(double toothCount) {
        this.toothCount = toothCount;
    }

    @Override
    public String toString() {
        return "DragonHead{" +
                "toothCount=" + toothCount +
                '}';
    }
}

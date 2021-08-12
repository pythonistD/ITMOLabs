package model;

import MyExceptions.IncorrectIdException;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Объетк коллекции
 */
public class Dragon implements Comparable<Dragon> {
    private long id;  //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле может быть null
    private double wingspan; //Значение поля должно быть больше 0
    private Boolean speaking; //Поле не может быть null
    private DragonType type; //Поле не может быть null
    private DragonHead dragonHead;
    private static LinkedList<Dragon> dragons = new LinkedList<>();
    private static long counter;
    private Clock clock = Clock.systemUTC();
    private static LocalDateTime startDate;
    private static LocalDateTime endDate;

    public static void setStartDate(LocalDateTime startDate) {
        Dragon.startDate = startDate;
    }

    public static void setEndDate(LocalDateTime endDate) {
        Dragon.endDate = endDate;
    }

    public static LocalDateTime getStartDate() {
        return Dragon.startDate;
    }

    public static LocalDateTime getEndDate() {
        return Dragon.endDate;
    }

    public long inctCounter() {
        return counter++;
    }

    public static LinkedList<Dragon> getDragonsCollection() {
        return Dragon.dragons;
    }

    public Dragon() {

    }

    public Dragon(String name, Long age, Double wingspan, Boolean speaking, Coordinates coordinates, DragonHead dragonHead, DragonType type) {
        this.name = name;
        this.age = age;
        this.wingspan = wingspan;
        this.coordinates = coordinates;
        this.speaking = speaking;
        this.type = type;
        this.dragonHead = dragonHead;
        this.creationDate = LocalDate.now(clock);
    }

    public Dragon(Long id, String name, Long age, Double wingspan, Boolean speaking, Coordinates coordinates, DragonHead dragonHead, DragonType type) throws IncorrectIdException {
        this.name = name;
        this.age = age;
        this.id = id;
        this.wingspan = wingspan;
        this.coordinates = coordinates;
        this.speaking = speaking;
        this.type = type;
        this.dragonHead = dragonHead;
        this.creationDate = LocalDate.now(clock);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getAge() {
        return age;
    }

    public DragonType getType() {
        return type;
    }

    public DragonHead getHead() {
        return dragonHead;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dragon dragon = (Dragon) o;
        return id == dragon.id &&
                Double.compare(dragon.wingspan, wingspan) == 0 &&
                Objects.equals(name, dragon.name) &&
                Objects.equals(coordinates, dragon.coordinates) &&
                Objects.equals(creationDate, dragon.creationDate) &&
                Objects.equals(age, dragon.age) &&
                Objects.equals(speaking, dragon.speaking) &&
                type == dragon.type &&
                Objects.equals(dragonHead, dragon.dragonHead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, wingspan, speaking, type, dragonHead);
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                "," + age +
                "," + wingspan +
                "," + speaking +
                "," + coordinates.getX() +
                "," + coordinates.getY() +
                "," + dragonHead.getToothCount() +
                "," + type + "\n";
    }

    @Override
    public int compareTo(Dragon o) {
        return (int) (this.age - o.getAge());
    }
}

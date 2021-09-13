package control.commands;

import model.Dragon;

import java.util.Comparator;

/**
 * Компаратор Драконов
 */
public class DragonComparator implements Comparator<Dragon> {
    /**
     * Метод сравнения Драконов
     *
     * @param o1 - первый дракон
     * @param o2 - следующий за первым
     * @return результат сравнения
     */
    @Override
    public int compare(Dragon o1, Dragon o2) {
        int result = 2;
        if ((o1.getAge() - o2.getAge()) < 0) {
            result = -1;
        } else if ((o1.getAge() - o2.getAge()) > 0) {
            result = 1;
        } else if ((o1.getAge() - o2.getAge()) == 0) {
            result = 0;
        }
        return result;
    }
}

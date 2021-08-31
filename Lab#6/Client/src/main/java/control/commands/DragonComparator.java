package control.commands;

import model.Dragon;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Компаратор Драконов
 */
public class DragonComparator implements Comparator<Dragon> , Serializable {
    private static final long serialVersionUID = 11L;
    /**
     * Метод сравнения Драконов
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Dragon o1, Dragon o2) {
        if ((o1.getAge() - o2.getAge()) < 0) {
            return -1;
        }
        if ((o1.getAge() - o2.getAge()) > 0) {
            return 1;
        }
        if ((o1.getAge() - o2.getAge()) == 0) {
            return 0;
        }
        return 2;
    }
}

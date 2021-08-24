package control.commands;


import control.Response;
import control.Utility;
import control.ValidateFields;
import model.Coordinates;
import model.Dragon;
import model.DragonHead;
import model.DragonType;

import java.time.LocalDateTime;
import java.util.ListIterator;

public class AddCommand extends Command {
    private static final long serialVersionUID = 5L;
    private Response response ;
    private long id;
    private String name;
    private Long age;
    private Double wingspan;
    private Boolean speaking;
    private Double x;
    private Double y;
    private Double tooth;
    private DragonType dragonType;
    /**
     * Запуск команды
     * @throws Exception
     */
    @Override
    public void execute() throws Exception {
        try {
            Dragon dragon = createDragon();
            Dragon.getDragonsCollection().add(dragon);
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении в коллекцию");
        }
        response = new Response("add", "Дракон:" + name + " успешно добавлен");
    }
    public Dragon createDragon() throws Exception {
        id = Dragon.getDragonsCollection().getLast().getId() + 1;
       Dragon dragon = new Dragon(id,name,age,wingspan,speaking,new Coordinates(x,y),new DragonHead(tooth),dragonType);
        return dragon;
    }
    public Response getResponse(){
        return this.response;
    }

}

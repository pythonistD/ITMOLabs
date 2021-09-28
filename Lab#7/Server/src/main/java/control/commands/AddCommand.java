package control.commands;


import MyExceptions.CommandException;
import MyExceptions.IncorrectIdException;
import control.Response;
import model.Coordinates;
import model.Dragon;
import model.DragonHead;
import model.DragonType;

public class AddCommand extends Command {
    private static final long serialVersionUID = 5L;
    private Response response;
    private long id;
    private String name;
    private Long age;
    private Double wingspan;
    private Boolean speaking;
    private Double x;
    private Double y;
    private Double tooth;
    private DragonType dragonType;
    private String owner;

    /**
     * Запуск команды
     *
     * @throws CommandException
     */
    @Override
    public void execute() throws CommandException {
        try {
            Dragon dragon = createDragon();
            Dragon.getDragonsCollection().add(dragon);
        } catch (Exception e) {
            throw new CommandException("ошибка при добавлении");
        }
        response = new Response("add", "Дракон:" + name + " успешно добавлен");
    }

    public Dragon createDragon(){
        id = Dragon.getDragonsCollection().size();
        return new Dragon(id, name, age, wingspan, speaking, new Coordinates(x, y), new DragonHead(tooth), dragonType,owner);
    }

    public Response getResponse() {
        return this.response;
    }

}

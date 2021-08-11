import control.Application;
import control.DataReader;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Application app = new Application();
            DataReader.setInputfileCollection(args[0]);

            app.consoleMod();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.print("CSV файл не найден");
        }
    }
}

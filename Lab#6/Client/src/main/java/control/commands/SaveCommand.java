package control.commands;

import control.Response;
import model.Dragon;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ListIterator;

public class SaveCommand extends Command {
    private static final long serialVersionUID = 20L;
    private Response response;
    /**
     * Запуск комманды
     */
    @Override
    public void execute(){

    }
}

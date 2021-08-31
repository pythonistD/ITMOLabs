package control.commands;

import control.DataReader;
import control.InfDeliverer;
import control.Information;
import control.Validator;

import java.io.BufferedReader;
import java.io.Serializable;

public class ExecuteScriptCommand extends Command{
    private static final long serialVersionUID = 14L;
    private Information information = new Information();

    /**
     * Выполняет запуск скрипта
     * @throws Exception
     */
    public void execute() throws Exception {
        information = InfDeliverer.infDeliver();


    }

}

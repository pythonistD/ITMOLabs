package control;

import MyExceptions.IncorrectIdException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CollectionManager {
    private DataReader dataReader = new DataReader();
    private DataWriter dataWriter = new DataWriter();

    public void readCollection() {

        try {
            DataReader.getCollectionData();
            dataWriter.writeCollectionData(DataReader.getCollectionData());
        } catch (IncorrectIdException | IOException e) {
            e.printStackTrace();
        }
    }
}

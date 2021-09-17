package control;

import MyExceptions.IncorrectIdException;

import java.io.IOException;

public class CollectionManager {
    private final DataWriter dataWriter = new DataWriter();

    public void readCollection() {
        try {
            DataReader.getCollectionData();
            dataWriter.writeCollectionData(DataReader.getCollectionData());
        } catch (IncorrectIdException | IOException e) {
            e.printStackTrace();
        }
    }
}

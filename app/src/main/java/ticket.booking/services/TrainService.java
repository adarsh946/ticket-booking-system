package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {

    Train train = new Train();

    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Train> trainList;

    private static final String TRAIN_PATH = "app/src/main/java/ticket.booking/localDb/Train.json";

    public TrainService(Train train) throws IOException{
        this.train = train;
        loadTrains();
    }

    public List<Train> loadTrains() throws IOException{
        File trains = new File(TRAIN_PATH);
        return objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }





}

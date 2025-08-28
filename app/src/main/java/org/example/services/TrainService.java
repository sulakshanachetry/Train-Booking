package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    private List<Train> trainList;

    private final ObjectMapper objectMapper= new ObjectMapper();
    private static final String TRAINS_PATH = "E:/java/IRCTC/app/src/main/java/org/example/localDb/Trains.json";

    TrainService() throws IOException
    {
        loadTrainListFromFile();
    }

    public void loadTrainListFromFile() throws IOException
    {
        trainList = objectMapper.readValue(new File(TRAINS_PATH), new TypeReference<List<Train>>() {});
    }

    public void saveTrainListToFile() throws IOException{
        objectMapper.writeValue(new File(TRAINS_PATH), trainList);
    }

    public List<Train> searchTrains(String source, String dest){
        return trainList.stream().filter(train -> validTrain(train,source,dest))
                .collect(Collectors.toList());
    }

    public boolean validTrain(Train train, String source, String dest){
        List<String> stations= train.getStations();

        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destIndex = stations.indexOf(dest.toLowerCase());

        return sourceIndex!=-1 && destIndex!=-1 && sourceIndex<destIndex;

    }

    public Train getTrain(Integer trainNo){
        return trainList.stream().filter(t->t.getTrainNo().equals(trainNo)).findFirst().orElse(null);
    }

    public void updateTrain(Train toBeUpdatedTrain) throws IOException
    {
        for(int i=0;i<trainList.size();i++){
            if(trainList.get(i).getTrainId().equals(toBeUpdatedTrain.getTrainId())){
                trainList.set(i,toBeUpdatedTrain);
            }
        }
        saveTrainListToFile();
    }

}

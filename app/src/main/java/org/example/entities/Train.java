package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)

public class Train {

    @Getter @Setter
    private String trainId;
    @Getter @Setter
    private Integer trainNo;
    @Getter @Setter
    private List<List<Integer>> seats;
    @Getter @Setter
    private Map<String, String> stationTimes;
    @Getter @Setter
    private List<String> stations;

    public Train(String trainId, Integer trainNo, List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations)
    {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats=seats;
        this.stationTimes=stationTimes;
        this.stations=stations;
    }

    public  Train(){}

    public String getTrainInfo()
    {
        return String.format("Train id is %s with Train No: %s",trainId,trainNo);
    }
}

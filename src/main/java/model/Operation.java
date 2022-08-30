package model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.time.LocalDateTime;

@PlanningEntity
public class Operation {

    @PlanningId
    private Long id;

    private String requiredProfession;
    private Equipment requiredEquipment;
    private int duration;
    private int profit;

    @PlanningVariable(valueRangeProviderRefs = "timeRange")
    private LocalDateTime startTime;
    @PlanningVariable(valueRangeProviderRefs = "workerRange")
    private Worker chosenWorker;
    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
    private Equipment chosenEquipment;


    public Operation(){

    }

    public Operation(Long id, String requiredProfession, Equipment requiredEquipment, int duration, int profit) {
        this.id = id;
        this.requiredProfession = requiredProfession;
        this.requiredEquipment = requiredEquipment;
        this.duration = duration;
        this.profit = profit;
    }

    public Long getId() {
        return id;
    }

    public String getRequiredProfession() {
        return requiredProfession;
    }

    public Equipment getRequiredEquipment() {
        return requiredEquipment;
    }

    public int getDuration() {
        return duration;
    }

    public int getProfit() {
        return profit;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Worker getChosenWorker() {
        return chosenWorker;
    }

    public void setChosenWorker(Worker chosenWorker) {
        this.chosenWorker = chosenWorker;
    }

    public Equipment getChosenEquipment() {
        return chosenEquipment;
    }

    public void setChosenEquipment(Equipment chosenEquipment) {
        this.chosenEquipment = chosenEquipment;
    }
}

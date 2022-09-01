package model;

import java.time.LocalDateTime;


public class AnchorOperation implements Operation {

    private Long id;
    private String requiredProfession;
    private Equipment requiredEquipment;
    private int duration;
    private int profit;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    @PlanningVariable(valueRangeProviderRefs = "workerRange")
//    private Worker chosenWorker;
//
//    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
//    private Equipment chosenEquipment;

    public AnchorOperation() {

    }

    public AnchorOperation(Long id, String requiredProfession, Equipment requiredEquipment, int duration, int profit) {
        this.id = id;
        this.requiredProfession = requiredProfession;
        this.requiredEquipment = requiredEquipment;
        this.duration = duration;
        this.profit = profit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequiredProfession() {
        return requiredProfession;
    }

    public void setRequiredProfession(String requiredProfession) {
        this.requiredProfession = requiredProfession;
    }

    public Equipment getRequiredEquipment() {
        return requiredEquipment;
    }

    public void setRequiredEquipment(Equipment requiredEquipment) {
        this.requiredEquipment = requiredEquipment;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

//    public Worker getChosenWorker() {
//        return chosenWorker;
//    }
//
//    public void setChosenWorker(Worker chosenWorker) {
//        this.chosenWorker = chosenWorker;
//    }
//
//    public Equipment getChosenEquipment() {
//        return chosenEquipment;
//    }
//
//    public void setChosenEquipment(Equipment chosenEquipment) {
//        this.chosenEquipment = chosenEquipment;
//    }
}

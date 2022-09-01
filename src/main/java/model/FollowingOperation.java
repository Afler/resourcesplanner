package model;

import listener.OperationTimeUpdatingListener;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import java.time.LocalDateTime;

@PlanningEntity
public class FollowingOperation implements Operation {

    @PlanningId
    private Long id;

    private String requiredProfession;
    private Equipment requiredEquipment;
    private int duration;
    private int profit;

    @PlanningVariable(valueRangeProviderRefs = {"anchorOperationRange", "followingOperationRange"},
            graphType = PlanningVariableGraphType.CHAINED)
    private Operation previousOperation;

    @CustomShadowVariable(variableListenerClass = OperationTimeUpdatingListener.class,
            sources = {@PlanningVariableReference(variableName = "previousOperation")})
    private LocalDateTime startTime;

    @CustomShadowVariable(variableListenerRef = @PlanningVariableReference(variableName = "startTime"))
    private LocalDateTime endTime;

    @PlanningVariable(valueRangeProviderRefs = "workerRange")
    private Worker chosenWorker;

    @PlanningVariable(valueRangeProviderRefs = "equipmentRange")
    private Equipment chosenEquipment;

    public FollowingOperation() {

    }

    public FollowingOperation(Long id, String requiredProfession, Equipment requiredEquipment, int duration, int profit) {
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

    public Operation getPreviousOperation() {
        return previousOperation;
    }

    public void setPreviousOperation(Operation previousOperation) {
        this.previousOperation = previousOperation;
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

//    @Override
//    public String toString() {
//        return "Start time: " + startTime.toString() +
//                "\nChosen worker: " + chosenWorker +
//                "\nChosen equipment: " + chosenEquipment;
//    }
}

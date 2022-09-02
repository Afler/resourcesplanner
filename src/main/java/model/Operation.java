package model;

import listener.EndTimeUpdatingListener;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.CustomShadowVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableReference;

import java.time.LocalDateTime;

@PlanningEntity
public class Operation {

    private WorkerProfession requiredWorkerProfession;
    private EquipmentModel requiredEquipmentModel;
    private long duration;
    private int profit;
    private LocalDateTime maxEndTime;

    @PlanningId
    private long id;

    @PlanningVariable(valueRangeProviderRefs = "workerRange", nullable = true)
    private Worker chosenWorker;

    @PlanningVariable(valueRangeProviderRefs = "startTimeRange")
    private LocalDateTime chosenStartTime;

    @CustomShadowVariable(variableListenerClass = EndTimeUpdatingListener.class,
            sources = {@PlanningVariableReference(variableName = "chosenStartTime")})
    private LocalDateTime endTime;

    private Operation() {

    }

    public Operation(long id, WorkerProfession requiredWorkerProfession, EquipmentModel requiredEquipment, long duration, int profit, LocalDateTime maxEndTime) {
        this.id = id;
        this.requiredWorkerProfession = requiredWorkerProfession;
        this.requiredEquipmentModel = requiredEquipment;
        this.duration = duration;
        this.profit = profit;
        this.maxEndTime = maxEndTime;
    }

    public WorkerProfession getRequiredWorkerProfession() {
        return requiredWorkerProfession;
    }

    public void setRequiredWorkerProfession(WorkerProfession requiredWorkerProfession) {
        this.requiredWorkerProfession = requiredWorkerProfession;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Worker getChosenWorker() {
        return chosenWorker;
    }

    public void setChosenWorker(Worker chosenWorker) {
        this.chosenWorker = chosenWorker;
    }

    public LocalDateTime getChosenStartTime() {
        return chosenStartTime;
    }

    public void setChosenStartTime(LocalDateTime chosenStartTime) {
        this.chosenStartTime = chosenStartTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public LocalDateTime getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(LocalDateTime maxEndTime) {
        this.maxEndTime = maxEndTime;
    }

    public EquipmentModel getRequiredEquipmentModel() {
        return requiredEquipmentModel;
    }

    public void setRequiredEquipmentModel(EquipmentModel requiredEquipmentModel) {
        this.requiredEquipmentModel = requiredEquipmentModel;
    }

}

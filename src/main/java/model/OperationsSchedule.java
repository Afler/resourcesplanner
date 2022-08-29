package model;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import java.util.LinkedList;
import java.util.List;

@PlanningEntity
public class OperationsSchedule {

    @PlanningVariable
    private List<Operation> operations = new LinkedList<>();

    private PlanningPeriod planningPeriod;

    public PlanningPeriod getPlanningPeriod() {
        return planningPeriod;
    }

    public void setPlanningPeriod(PlanningPeriod planningPeriod) {
        this.planningPeriod = planningPeriod;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}

package solution;

import model.*;
import org.optaplanner.core.api.domain.solution.*;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@PlanningSolution
public class JobScheduleSolution {

    @ValueRangeProvider(id = "workerRange")
    @ProblemFactCollectionProperty
    private List<Worker> workers;

    @ValueRangeProvider(id = "equipmentRange")
    @ProblemFactCollectionProperty
    private List<Equipment> equipment;

    @ProblemFactProperty
    @ValueRangeProvider(id = "startTime")
    private LocalDateTime startTime;
    @ProblemFactProperty
    @ValueRangeProvider(id = "endTime")
    private LocalDateTime endTime;

    @ProblemFactProperty
    private AnchorOperation anchorOperation;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "followingOperationRange")
    private List<FollowingOperation> followingOperations;

    @PlanningScore
    private HardSoftScore score;

    public JobScheduleSolution() {
    }

    public JobScheduleSolution(List<Worker> workers, List<Equipment> equipment, LocalDateTime startTime, LocalDateTime endTime, AnchorOperation anchorOperation, List<FollowingOperation> operations) {
        this.workers = workers;
        this.equipment = equipment;
        this.startTime = startTime;
        this.endTime = endTime;
        this.anchorOperation = anchorOperation;
        this.followingOperations = operations;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @ValueRangeProvider(id = "anchorOperationRange")
    public List<AnchorOperation> getAnchorOperation() {
        return Collections.singletonList(anchorOperation);
    }

    public void setAnchorOperation(AnchorOperation anchorOperation) {
        this.anchorOperation = anchorOperation;
    }

    public List<FollowingOperation> getFollowingOperations() {
        return followingOperations;
    }

    public void setFollowingOperations(List<FollowingOperation> followingOperations) {
        this.followingOperations = followingOperations;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}

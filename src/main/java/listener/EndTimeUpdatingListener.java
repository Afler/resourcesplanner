package listener;

import model.Operation;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import solution.JobScheduleSolution;

public class EndTimeUpdatingListener implements VariableListener<JobScheduleSolution, Operation> {

    private void updateEndTime(Operation operation) {
        if (operation != null && operation.getChosenStartTime() != null) {
            operation.setEndTime(operation.getChosenStartTime().plusHours(operation.getDuration()));
        }
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {
    }

    @Override
    public void afterVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {
        updateEndTime(operation);
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {
        updateEndTime(operation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, Operation operation) {

    }
}

package model.listener;

import model.AnchorOperation;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import solution.JobScheduleSolution;

public class AnchorEndTimeUpdatingVarListener implements VariableListener<JobScheduleSolution, AnchorOperation> {

    private void updateOperationEndTime(AnchorOperation anchorOperation) {
        anchorOperation.setEndTime(anchorOperation.getStartTime().plusMinutes(anchorOperation.getDuration()));
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {
        updateOperationEndTime(anchorOperation);
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {
        updateOperationEndTime(anchorOperation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, AnchorOperation anchorOperation) {

    }
}

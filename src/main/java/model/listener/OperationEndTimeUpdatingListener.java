package model.listener;

import model.FollowingOperation;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import solution.JobScheduleSolution;

public class OperationEndTimeUpdatingListener implements VariableListener<JobScheduleSolution, FollowingOperation> {

    private void updateOperationEndTime(FollowingOperation anchorOperation) {
        anchorOperation.setEndTime(anchorOperation.getStartTime().plusMinutes(anchorOperation.getDuration()));
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationEndTime(followingOperation);
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationEndTime(followingOperation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }
}

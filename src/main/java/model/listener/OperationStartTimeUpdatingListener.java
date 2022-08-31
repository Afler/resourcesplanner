package model.listener;

import model.FollowingOperation;
import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;
import solution.JobScheduleSolution;

public class OperationStartTimeUpdatingListener implements VariableListener<JobScheduleSolution, FollowingOperation> {

    private void updateOperationStartTime(FollowingOperation followingOperation) {
        followingOperation.setStartTime(followingOperation.getPreviousOperation().getEndTime());
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterVariableChanged(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationStartTime(followingOperation);
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {
        updateOperationStartTime(followingOperation);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<JobScheduleSolution> scoreDirector, FollowingOperation followingOperation) {

    }
}

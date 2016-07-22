package org.txazo.tool.util.quartz.job;

import org.quartz.*;

/**
 * JobAdapter
 */
@DisallowConcurrentExecution
public abstract class JobAdapter<V> implements Job, JobExecutor<V>, JobRemover<V> {

    private boolean beforeExecute(V value) {
        return !canRemove(value) && canExecute(value);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetailAdapter<V> jobDetailAdapter = (JobDetailAdapter) context.getJobDetail();
        JobData<V> jobData = jobDetailAdapter.getJobData();
        JobCallback<V> jobCallback = jobDetailAdapter.getJobCallback();
        V value = jobData.getValue();
        if (beforeExecute(value)) {
            try {
                jobCallback.callback(value);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        if (canRemove(value)) {
            try {
                context.getScheduler().deleteJob(context.getJobDetail().getKey());
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        System.out.println("execute end");
    }

}

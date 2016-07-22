package org.txazo.tool.util.quartz;

import org.quartz.*;

/**
 * DefaultQuartzScheduler
 */
public class DefaultQuartzScheduler implements QuartzScheduler {

    private Scheduler scheduler;

    public DefaultQuartzScheduler() {
    }

    public DefaultQuartzScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public boolean addSchedule(JobDetail jobDetail, Trigger trigger) {
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSchedule(JobKey jobKey) {
        try {
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSchedule(TriggerKey triggerKey) {
        try {
            scheduler.unscheduleJob(triggerKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSchedule(JobDetail jobDetail) {
        try {
            scheduler.addJob(jobDetail, true);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSchedule(TriggerKey triggerKey, Trigger trigger) {
        try {
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSchedule(JobDetail jobDetail, TriggerKey triggerKey, Trigger trigger) {
        try {
            scheduler.addJob(jobDetail, true);
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}

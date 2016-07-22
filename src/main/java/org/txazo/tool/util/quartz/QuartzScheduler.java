package org.txazo.tool.util.quartz;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

/**
 * QuartzScheduler
 */
public interface QuartzScheduler {

    public boolean addSchedule(JobDetail jobDetail, Trigger trigger);

    public boolean deleteSchedule(JobKey jobKey);

    public boolean deleteSchedule(TriggerKey triggerKey);

    public boolean updateSchedule(JobDetail jobDetail);

    public boolean updateSchedule(TriggerKey triggerKey, Trigger trigger);

    public boolean updateSchedule(JobDetail jobDetail, TriggerKey triggerKey, Trigger trigger);

}

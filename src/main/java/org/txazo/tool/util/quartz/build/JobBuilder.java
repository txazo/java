package org.txazo.tool.util.quartz.build;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.txazo.tool.util.quartz.job.JobAdapter;
import org.txazo.tool.util.quartz.job.JobCallback;
import org.txazo.tool.util.quartz.job.JobData;
import org.txazo.tool.util.quartz.job.JobDetailAdapter;

/**
 * JobBuilder
 */
public abstract class JobBuilder {

    public static <V> JobDetail newJobDetail(String jobName, Class<? extends JobAdapter> jobClass, JobData<V> jobData, JobCallback<V> jobCallback) {
        return newJobDetail(KeyBuilder.newJobKey(jobName, jobClass), jobClass, jobData, jobCallback);
    }

    public static <V> JobDetail newJobDetail(JobKey jobKey, Class<? extends JobAdapter> jobClass, JobData<V> jobData, JobCallback<V> jobCallback) {
        return new JobDetailAdapter(jobKey, jobClass, jobData, jobCallback);
    }

}

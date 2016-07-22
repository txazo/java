package org.txazo.tool.util.quartz.job;

import org.quartz.JobKey;
import org.quartz.impl.JobDetailImpl;

/**
 * JobDetailAdapter
 */
public class JobDetailAdapter<V> extends JobDetailImpl implements Value<V> {

    private static final long serialVersionUID = 5296184612540623850L;

    private JobData<V> jobData;
    private JobCallback<V> jobCallback;

    public JobDetailAdapter(JobKey jobKey, Class<? extends JobAdapter> jobClass, JobData<V> jobData, JobCallback<V> jobCallback) {
        this.jobData = jobData;
        this.jobCallback = jobCallback;
        setKey(jobKey);
        setJobClass(jobClass);
        setDurability(true);
    }

    public JobCallback<V> getJobCallback() {
        return jobCallback;
    }

    public void setJobCallback(JobCallback<V> jobCallback) {
        this.jobCallback = jobCallback;
    }

    public JobData<V> getJobData() {
        return jobData;
    }

    public void setJobData(JobData<V> jobData) {
        this.jobData = jobData;
    }

}

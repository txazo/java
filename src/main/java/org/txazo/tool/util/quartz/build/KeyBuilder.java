package org.txazo.tool.util.quartz.build;

import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.txazo.tool.util.quartz.job.JobAdapter;

/**
 * KeyBuilder
 */
public abstract class KeyBuilder {

    public static JobKey newJobKey(String name, Class<? extends JobAdapter> clazz) {
        return newJobKey(name, clazz.getSimpleName());
    }

    public static JobKey newJobKey(String name, String group) {
        return JobKey.jobKey(name, group);
    }

    public static TriggerKey newTriggerKey(String name, Class<? extends JobAdapter> clazz) {
        return newTriggerKey(name, clazz.getSimpleName());
    }

    public static TriggerKey newTriggerKey(String name, String group) {
        return TriggerKey.triggerKey(name, group);
    }

}

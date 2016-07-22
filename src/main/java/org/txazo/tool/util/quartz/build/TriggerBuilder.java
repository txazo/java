package org.txazo.tool.util.quartz.build;

import org.quartz.Trigger;
import org.quartz.TriggerKey;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * TriggerBuilder
 */
public abstract class TriggerBuilder {

    public static Trigger newCronTrigger(String expression, TriggerKey triggerKey) {
        return newTrigger()
                .withIdentity(triggerKey)
                .withSchedule(cronSchedule(expression).inTimeZone(TimeZone.getTimeZone("GMT+8")))
                .build();
    }

}

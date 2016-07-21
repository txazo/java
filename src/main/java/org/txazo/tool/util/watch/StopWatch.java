package org.txazo.tool.util.watch;

import org.txazo.tool.util.DecimalFormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 计时器工具类
 */
public class StopWatch {

    private long totalTimeMillis = 0L;
    private List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();

    public static StopWatch newInstance() {
        return new StopWatch();
    }

    public StopWatch watch(String taskName, StopWatchTask task) throws Throwable {
        return watch(taskName, 1, task);
    }

    public StopWatch watch(String taskName, int times, StopWatchTask task) throws Throwable {
        if (times <= 0) {
            throw new IllegalArgumentException("times must > 0");
        }

        long startTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            task.execute();
        }
        long timeMillis = System.currentTimeMillis() - startTimeMillis;

        totalTimeMillis += timeMillis;
        taskInfos.add(new TaskInfo(taskName, timeMillis));

        return this;
    }

    public void showTime() {
        StringBuilder builder = new StringBuilder();
        builder.append("StopWatch : running time (millis) = ").append(totalTimeMillis).append("\n");
        builder.append("-----------------------------------------\n");
        builder.append("ms\t\t\t%\t\t\ttask name\n");
        builder.append("-----------------------------------------\n");
        for (TaskInfo info : taskInfos) {
            builder.append(info.getTimeMillis()).append("\t\t\t");
            builder.append(DecimalFormatUtils.format("#.##", info.getTimeMillis() * 100 / totalTimeMillis)).append("%\t\t\t");
            builder.append(info.getTaskName()).append("\n");
        }
        System.out.println(builder.toString());
    }

    private static class TaskInfo {

        private String taskName;
        private long timeMillis;

        public TaskInfo(String taskName, long timeMillis) {
            this.taskName = taskName;
            this.timeMillis = timeMillis;
        }

        public String getTaskName() {
            return taskName;
        }


        public long getTimeMillis() {
            return timeMillis;
        }

    }

}

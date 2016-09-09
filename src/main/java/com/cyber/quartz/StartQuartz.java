/**
 * 
 */
package com.cyber.quartz;

import java.util.concurrent.TimeUnit;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyl
 * @date 2016年9月9日
 * 
 */
public class StartQuartz {

	private static Logger logger = LoggerFactory.getLogger(StartQuartz.class);

	public static void main(String[] args) {
		try {
			// 获取Scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			// 具体任务
			JobDetail job = JobBuilder.newJob(SimpleJob.class)
					.withIdentity("job1", "group1").build();
			// 触发时间点 方式一
			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
					.simpleSchedule().withIntervalInSeconds(5).repeatForever();
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1").startNow()
					.withSchedule(simpleScheduleBuilder).build();
			// 触发时间点 方式二
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 * * * * ? *");
			Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
			        .withSchedule(cronScheduleBuilder).build();
			
			// 交由Scheduler安排触发
			scheduler.scheduleJob(job, trigger);
			/* 为观察程序运行，此设置主程序睡眠3分钟才继续往下运行（因下一个步骤是“关闭Scheduler”） */
			try {
				TimeUnit.MINUTES.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 关闭Scheduler
			scheduler.shutdown();

		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

}

/**
 * 
 */
package com.cyber.quartz;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyl
 * @date 2016年9月9日
 * 先运行StartQuartz，把任务存入数据库。然后启动该类，查看该类是否启动任务
 */
public class StartQuartzNoJob {

	private static Logger logger = LoggerFactory.getLogger(StartQuartzNoJob.class);

	public static void main(String[] args) {
		try {
			// 获取Scheduler实例
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			//通过名称删除任务
			JobKey jobKey = new JobKey("job1","group1");
			if(scheduler.checkExists(jobKey)){
				System.out.println("delete job1");
				scheduler.deleteJob(jobKey);
			}
			
			//获取trigger的相关信息
//			Trigger trigger = scheduler.getTrigger(new TriggerKey(""));
//			Date nextFireTime = trigger.getNextFireTime();
			
//			try {
//				TimeUnit.MINUTES.sleep(3);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

			// 关闭Scheduler
//			scheduler.shutdown();

		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		}
	}

}

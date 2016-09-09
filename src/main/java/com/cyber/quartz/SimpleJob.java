/**
 * 
 */
package com.cyber.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zyl
 * @date 2016年9月9日
 * 简单的定时任务
 */
public class SimpleJob implements Job {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 此任务仅打印日志便于调试、观察
		this.logger.info(this.getClass().getName() + " trigger...");
		System.err.println("------------------");
	}
}

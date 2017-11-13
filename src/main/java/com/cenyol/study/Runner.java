package com.cenyol.study;

import com.cenyol.study.topics.SubscribeFeedbackTopic;
import com.cenyol.study.topics.SubscribeNewDeviceTopic;
import com.cenyol.study.topics.SubscribeSensorDataTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cenyol mail: mr.cenyol@gmail.com
 * @date 06/11/2017 19:12
 *
 * 开发测试阶段使用Topic下的类来执行。最终打包使用的入口是本文件的main函数。
 */
public class Runner {
    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    public static void main(String[] args) {
        logger.debug("Starting application, init ExecutorService, establish newCachedThreadPool");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SubscribeFeedbackTopic.getInstance());
        executorService.execute(SubscribeNewDeviceTopic.getInstance());
        executorService.execute(SubscribeSensorDataTopic.getInstance());
        logger.debug("Establishing thread pool done!\n");
    }
}
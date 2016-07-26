package com.vivo.testbed.bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于解析Config.xml文件
 * @author wuxiangli
 *
 */
public class Config {
    
    /**
     * 配置文件路径
     */
    public static final String GONFIG_FILE_PATH = "Config.xml";
    /**
     * 对应usecase节点，配置的是单个用例的信息。
     */
    public static final String CONFIG_USECASE = "usecase";
    /**
     * 对应节点multithread_usecase，多线程测试用例
     */
    public static final String CONDIG_USECASE_MULTITHREAD = "multithread_usecase";
    /**
     * 配置文件中的name节点
     */
    public static final String GONFIG_NAME = "name";
    /**
     * 配置文件中的info节点
     */
    public static final String CONFIG_INFO = "info";
    /**
     * 配置文件中的action节点
     */
    public static final String CONFIG_ACTION = "action";
    
    
    public static ExecutorService executorService = Executors.newFixedThreadPool(8); // 固定五个线程来执行任务
}

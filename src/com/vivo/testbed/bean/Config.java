package com.vivo.testbed.bean;

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
}

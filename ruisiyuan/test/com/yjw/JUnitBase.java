package com.yjw;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * dao层测试基类
 * @author eason
 *
 * 2016年6月6日下午4:39:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 配置事物  defaultRollback = false , 默认为 true，默认情况下，增删改操作不会生效，事物自动回滚
@TransactionConfiguration(transactionManager = "txManagerMyBaties", defaultRollback = false)  
@Transactional
@ContextConfiguration(locations = {"classpath:spring-applicationContext.xml"})
public class JUnitBase extends AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * 指定数据源
	 */
	@Override  
    @Resource(name = "dataSource")  // 数据源名称必须与配置文件配置的名称一致
    public void setDataSource(DataSource dataSource) {
		System.out.println(dataSource);
        super.setDataSource(dataSource);  
    }
}

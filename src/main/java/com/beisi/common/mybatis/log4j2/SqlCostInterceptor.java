package com.beisi.common.mybatis.log4j2;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

/**
 * Sql执行时间记录拦截器
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "query", args = { Statement.class, ResultHandler.class }),
		@Signature(type = StatementHandler.class, method = "update", args = { Statement.class }),
		@Signature(type = StatementHandler.class, method = "batch", args = { Statement.class }) })
public class SqlCostInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		long startTime = System.currentTimeMillis();
		try {
			return invocation.proceed();
		} finally {
			long endTime = System.currentTimeMillis();
			long sqlCost = endTime - startTime;
			System.out.println("执行耗时 : [" + sqlCost + "ms ] ");
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}

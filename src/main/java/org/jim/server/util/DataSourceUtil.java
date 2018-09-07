package org.jim.server.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.jfinal.kit.PropKit;

public class DataSourceUtil {

	private static Logger LOG = LoggerFactory.getLogger(DataSourceUtil.class);
	public static DruidDataSource getDataSource() {
		PropKit.use("app.properties");
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(PropKit.get("className"));
		dataSource.setUsername(PropKit.get("userName"));
		dataSource.setPassword(PropKit.get("password"));
		dataSource.setUrl(PropKit.get("url"));
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxActive(20);
		dataSource.setMaxWait(60000);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setValidationQuery("SELECT 'X'");
		LOG.info("DataSourceUtil----数据库链接初始化成功....");
		return dataSource;
	}

	public static DruidPooledConnection getConnection() throws SQLException {
		DruidDataSource source = DataSourceUtil.getDataSource();
		LOG.info("获取数据库链接成功....");
		return source.getConnection();
	}

	public static void closeConnection(DruidPooledConnection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void closePreparedStatement(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

package com.smartErp.util.frame;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;


@Intercepts(value = { @Signature(args = { Connection.class }, method = "prepare", type = StatementHandler.class) })
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget(); 
		StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(handler, "delegate");  
		BoundSql boundSql = delegate.getBoundSql();
		Object obj = boundSql.getParameterObject();  
		if (obj instanceof Page<?>) {  
			Page<?> page = (Page<?>) obj;
			MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
			Connection connection = (Connection)invocation.getArgs()[0];  
			String sql = boundSql.getSql(); 
			this.setTotalRecord(page, mappedStatement, connection);
			String pageSql = this.getPageSql(page, sql);  
			ReflectUtil.setFieldValue(boundSql, "sql", pageSql);  
		}
		return invocation.proceed(); 
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);  
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
	
	private String getPageSql(Page<?> page, String sql) {  
       StringBuffer sqlBuffer = new StringBuffer(sql);  
       int offset = (page.getPageNo() - 1) * page.getPageSize();  
       sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());  
       return sqlBuffer.toString();  
	}  
	
	private void setTotalRecord(Page<?> page, MappedStatement mappedStatement, Connection connection) {  
		BoundSql boundSql = mappedStatement.getBoundSql(page);
		String sql = boundSql.getSql();
		String countSql = this.getCountSql(sql);
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(countSql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int totalRecord = rs.getInt(1);
				// 给当前的参数page对象设置总记录数
				page.setTotalRecord(totalRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 

	}
	
	private String getCountSql(String sql) {
		return "select count(1) from (" + sql + ") as t_interceptor_count";  
	}


	private static class ReflectUtil {  
	       /** 
	        * 利用反射获取指定对象的指定属性 
	        * @param obj 目标对象 
	        * @param fieldName 目标属性 
	        * @return 目标属性的值 
	        */  
	       public static Object getFieldValue(Object obj, String fieldName) {  
	           Object result = null;  
	           Field field = ReflectUtil.getField(obj, fieldName);  
	           if (field != null) {  
	              field.setAccessible(true);  
	              try {  
	                  result = field.get(obj);  
	              } catch (IllegalArgumentException e) {  
	                  // TODO Auto-generated catch block  
	                  e.printStackTrace();  
	              } catch (IllegalAccessException e) {  
	                  // TODO Auto-generated catch block  
	                  e.printStackTrace();  
	              }  
	           }  
	           return result;  
	       }  
	        
	       /** 
	        * 利用反射获取指定对象里面的指定属性 
	        * @param obj 目标对象 
	        * @param fieldName 目标属性 
	        * @return 目标字段 
	        */  
	       private static Field getField(Object obj, String fieldName) {  
	           Field field = null;  
	          for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {  
	              try {  
	                  field = clazz.getDeclaredField(fieldName);  
	                  break;  
	              } catch (NoSuchFieldException e) {  
	                  //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
	              }  
	           }  
	           return field;  
	       }  
	   
	       /** 
	        * 利用反射设置指定对象的指定属性为指定的值 
	        * @param obj 目标对象 
	        * @param fieldName 目标属性 
	         * @param fieldValue 目标值 
	        */  
	       public static void setFieldValue(Object obj, String fieldName, String fieldValue) {  
	           Field field = ReflectUtil.getField(obj, fieldName);  
	           if (field != null) {  
	              try {  
	                  field.setAccessible(true);  
	                  field.set(obj, fieldValue);  
	              } catch (IllegalArgumentException e) {  
	                  // TODO Auto-generated catch block  
	                  e.printStackTrace();  
	              } catch (IllegalAccessException e) {  
	                  // TODO Auto-generated catch block  
	                  e.printStackTrace();  
	              }  
	           }  
	        }  
	    }  

}

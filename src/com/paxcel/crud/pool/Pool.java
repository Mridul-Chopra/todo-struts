package com.paxcel.crud.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.dbcp2.BasicDataSource;

public class Pool {

	
	private static HashMap<String,String> properties = new HashMap<String,String>(); //  hashmap containing all the config properties
	private static BasicDataSource dataSource; // dbcp2 datasource
	
	static{
		Pool.getProps(); // get the props
		Pool.getDataSource(); // set up the datasource
		//System.out.println(properties);
	}
	

	/*
	 *	Sets the dbcp2 connection dataSource 
	 */
	public static BasicDataSource getDataSource(){
		if(dataSource == null) {
			
			BasicDataSource ds  = new BasicDataSource(); //creating a dataSource
			
			/*Setting the properties of the data source*/
			ds.setDriverClassName(properties.get("driverClass"));
			ds.setUrl(properties.get("url"));
			ds.setUsername(properties.get("username"));
			ds.setPassword(properties.get("password"));
			
			ds.setMinIdle(Integer.parseInt(properties.get("minIdle")));
            ds.setMaxIdle(Integer.parseInt(properties.get("maxIdle")));
            ds.setMaxOpenPreparedStatements(Integer.parseInt(properties.get("maxPS")));
            
            dataSource = ds;
		}
		return dataSource;
	}
	
	
	/*
	 *	Connection provider function  
	 */
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoStruts","root","123");
			return cn;
			//return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*
	 *	gets the properties from the configuration file 
	 */
	private static void getProps(){
		try {
			
			/* Load all the properties from the config file*/
			Properties props = new Properties();
			props.load(Pool.class.getClassLoader().getResourceAsStream("config.properties"));
			Set<String> keys = props.stringPropertyNames(); // get all the keys from the properties
			
			for(String key : keys) {
				Pool.properties.put(key, props.getProperty(key)); // put all the key value pairs in the hash map
			}

		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
}

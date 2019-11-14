package com.tutorialsninjaandheatclinic.TutorialsninjaAndHeatclinicAutomation.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDataFromDataBase {
	PreparedStatement pst;
	ResultSet rs = null;

	public String fetchData(String databaseName, String tableName, String id, String columnName, String primaryKey)
			throws SQLException {
		ResultSet rs = null;
		try {

			Connection con = new LoadDriver().lodingDriver(databaseName);
			Statement stmt = con.createStatement();
			System.out.println(
					"select " + columnName + " from " + tableName + " where " + primaryKey + "=" + "'" + id + "'");
			System.out.println("The primery key value is " + id);
			rs = stmt.executeQuery(
					"select " + columnName + " from " + tableName + " where " + primaryKey + "=" + "'" + id + "'");

			while (rs.next()) {
				System.out.println(rs.getString(1));
				return rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println("Sorry! wrong Input");
			e.printStackTrace();
		}
		return rs.getString(1);
	}

	public static void main(String[] args) throws SQLException {
		FetchDataFromDataBase fetch = new FetchDataFromDataBase();
		System.out.println(fetch.fetchData("Selenium", "orange_hrm", "good", "FirstName", "Comment"));
	}
}
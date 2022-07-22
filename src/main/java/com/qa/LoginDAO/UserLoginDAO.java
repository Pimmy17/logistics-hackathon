package com.qa.LoginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.TMTY.domain.Logins;
import com.qa.logins.UserLogins;
import com.qa.logisticshackathon.utils.DBUtils;



public class UserLoginDAO {

	public static final Logger LOGGER = LogManager.getLogger();
	
	public UserLogins modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long userId = resultSet.getLong("userid");
		String role = resultSet.getString("role");
		return new UserLogins(userId, role);
	}
	
	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	
	public List<UserLogins> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM user logins");) {
			List<UserLogins> logins = new ArrayList<>();
			while (resultSet.next()) {
				logins.add(modelFromResultSet(resultSet));
			}
			return logins;
			
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public UserLogins create(UserLogins userlogins) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO logins(role) VALUES (?)");) {
			statement.setString(1, userlogins.getRole());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	
	public UserLogins read(Long userId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM logins WHERE login_id = ?");) {
			statement.setLong(1, userId);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public UserLogins update(UserLogins logins) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE logins SET assigner_name = ?, driver_name = ?, privilege = ? WHERE login_id = ?");) {
			statement.setString(1, logins.getRole());
			statement.executeUpdate();
			return read(logins.getUserId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	
}

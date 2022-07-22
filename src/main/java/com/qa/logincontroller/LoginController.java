package com.qa.logincontroller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.LoginDAO.UserLoginDAO;
import com.qa.logins.UserLogins;
import com.qa.logisticshackathon.utils.Utils;

public class LoginController implements CrudController<UserLogins> {

	public static final Logger LOGGER = LogManager.getLogger();

	private UserLoginDAO userlogindao;
	private Utils utils;

	public LoginController(UserLoginDAO userloginDAO, Utils utils) {
		super();
		this.userlogindao = userloginDAO;
		this.utils = utils;

	}

	@Override
	public List<UserLogins> readAll() {
		List<UserLogins> userlogins = userlogindao.readAll();
		for (UserLogins userlogin : userlogins) {
			LOGGER.info(userlogin);
		}
		return userlogins;
	}

	@Override
	public UserLogins create() {
		LOGGER.info("Please enter your Role");
		String role = utils.getString();
		UserLogins userlogins = new UserLogins(role);
		LOGGER.info("User Logged");
		return userlogins;
	}

	@Override
	public UserLogins update() {
		LOGGER.info("Please enter the id of the user you would like to update");
		Long Userid = utils.getLong();
		LOGGER.info("Please enter the new role");
		String role = utils.getString();
		UserLogins userlogins = new UserLogins(Userid, role);
		LOGGER.info("User Updated");
		return userlogins;

	}

	@Override
	public int delete(Long id) {
		LOGGER.info("Please enter the id of the user you would like to delete");
		Long Userid = utils.getLong();
		return userlogindao.delete(Userid);
	}

	@Override
	public UserLogins read(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserLogins modelFromResultSet(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

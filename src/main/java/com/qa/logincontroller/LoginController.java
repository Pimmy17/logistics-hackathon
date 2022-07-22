package com.qa.logincontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.LoginDAO.CrudController;
import com.qa.LoginDAO.UserLoginDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.logins.UserLogins;
import com.qa.logisticshackathon.utils.Utils;

public class LoginController implements CrudController<UserLogins> {
		
		public static final Logger LOGGER = LogManager.getLogger();
		
		private UserLoginDAO userlogindao;
		private Utils utils;
		
		public LoginController(UserLoginDAO userloginDAO, Utils utils ) {
			super();
			this.userlogindao = userloginDAO;
			this.utils = utils;
		
	}

		@Override
		public List<UserLogin> readAll() {
			List<UserLogins> userlogins = UserLoginDAO.readAll();
			for (UserLogins userlogin : userlogins) {
				LOGGER.info(userlogin);
			return null;
		}

		@Override
		public UserLogin create() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public UserLogin update() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int delete() {
			// TODO Auto-generated method stub
			return 0;
		}
	
	
}

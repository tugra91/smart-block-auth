package com.turkcell.blockmailAuth.logininfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.turkcell.blockmailAuth.logininfo.dao.LoginInfoDao;
import com.turkcell.blockmailAuth.logininfo.model.UserInformationModel;

@Repository
public class LoginInfoDaoImpl implements LoginInfoDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Override
	public UserInformationModel findByUsername(String username) {
		Query query = new Query(Criteria.where("username").is(username)).limit(1);	
		List<UserInformationModel> userList = new ArrayList<>();
		userList = mongoTemplate.find(query, UserInformationModel.class);
		return userList.isEmpty() ? null : userList.get(0);
	}

}

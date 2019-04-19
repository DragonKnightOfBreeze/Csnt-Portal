package com.windea.demo.csntportal.service;

import com.windea.demo.csntportal.domain.entity.Admin;

public interface AdminService {
	Admin login(String username, String password);
}

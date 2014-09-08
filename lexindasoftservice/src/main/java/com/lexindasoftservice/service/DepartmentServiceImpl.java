package com.lexindasoftservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexindasoftservice.dao.AdminDao;
import com.lexindasoftservice.dao.DepartmentDao;
import com.lexindasoftservice.model.Admin;
import com.lexindasoftservice.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;

	public List<Department> getDepartmentInfo(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.getDepartmentInfo(department);
	}

	public int insertDepartmentInfo(Department department) {
		// TODO Auto-generated method stub
		return departmentDao.insertDepartmentInfo(department);
	}

	
	
}

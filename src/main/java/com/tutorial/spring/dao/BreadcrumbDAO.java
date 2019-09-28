package com.tutorial.spring.dao;

import java.util.HashMap;

import com.tutorial.spring.model.Breadcrumb;

public interface BreadcrumbDAO {
	
	public Breadcrumb getBreadcrumb(HashMap<String, Object> paramMap);
	
}
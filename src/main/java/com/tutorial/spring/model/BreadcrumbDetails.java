package com.tutorial.spring.model;

import java.util.ArrayList;
import java.util.HashMap;

public class BreadcrumbDetails {

	private static HashMap<String, HashMap<String, Breadcrumb>> detailsMap;
	
	static {
		detailsMap = new HashMap<>();
		
		HashMap<String, Breadcrumb> methodMap = new HashMap<>();
		Breadcrumb breadcrumb = new Breadcrumb();
		ArrayList<BreadcrumbItem> breadcrumbItems = new ArrayList<>();
		
		// Home
		breadcrumbItems.add(new BreadcrumbItem("Home", "/example?screen=1&method=homepage"));
		breadcrumb.setBreadcrumbItems(breadcrumbItems);
		methodMap.put("homepage", breadcrumb);
		detailsMap.put("1", methodMap);
		
		// about us
		breadcrumbItems = new ArrayList<>();
		breadcrumb = new Breadcrumb();
		methodMap = new HashMap<>();
		breadcrumbItems.add(new BreadcrumbItem("Home", "/example?screen=1&method=homepage"));
		breadcrumbItems.add(new BreadcrumbItem("About Us", "/example?method=about-us&screen=2"));
		breadcrumb.setBreadcrumbItems(breadcrumbItems);
		methodMap.put("about-us", breadcrumb);
		detailsMap.put("2", methodMap);
		
		// contact us
		breadcrumbItems = new ArrayList<>();
		breadcrumb = new Breadcrumb();
		methodMap = new HashMap<>();
		breadcrumbItems.add(new BreadcrumbItem("Home", "/example?screen=1&method=homepage"));
		breadcrumbItems.add(new BreadcrumbItem("Contact Us", "/example?screen=3&method=contact-us"));
		breadcrumb.setBreadcrumbItems(breadcrumbItems);
		methodMap.put("contact-us", breadcrumb);
		detailsMap.put("3", methodMap);
	}

	public static HashMap<String, HashMap<String, Breadcrumb>> getDetailsMap() {
		return detailsMap;
	}
}
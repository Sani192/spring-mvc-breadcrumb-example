package com.tutorial.spring.model;

import java.util.ArrayList;

public class Breadcrumb {

	private ArrayList<BreadcrumbItem> breadcrumbItems;

	public Breadcrumb() {
		super();
	}

	public Breadcrumb(ArrayList<BreadcrumbItem> breadcrumbItems) {
		super();
		this.breadcrumbItems = breadcrumbItems;
	}

	public ArrayList<BreadcrumbItem> getBreadcrumbItems() {
		return breadcrumbItems;
	}

	public void setBreadcrumbItems(ArrayList<BreadcrumbItem> breadcrumbItems) {
		this.breadcrumbItems = breadcrumbItems;
	}
}
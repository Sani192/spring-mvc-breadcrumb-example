package com.tutorial.spring.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.tutorial.spring.config.ApplicationContextProvider;
import com.tutorial.spring.dao.BreadcrumbDAO;
import com.tutorial.spring.model.Breadcrumb;
import com.tutorial.spring.model.BreadcrumbDetails;

public class BreadcrumbUtils {
	
	private static final HashMap<String, HashMap<String, Breadcrumb>> detailsMap = BreadcrumbDetails.getDetailsMap();
	public static final boolean IS_BREADCRUMB_DETAILS_FROM_DB = false;

	public static Breadcrumb getBreadcrumb(HttpServletRequest request, boolean isFromDb) {
		Breadcrumb breadcrumb = null;
		String screen = "";
		String method = "";
		
		final Map params = request.getParameterMap();
	    final Iterator i = params.keySet().iterator();
	    while (i.hasNext()) {
	    	final String key = (String) i.next();
	    	if(StringUtils.isNotBlank(key)) {
	    		if(key.equals("screen")) {
	    			screen = ((String[]) params.get(key))[0];
	    		} else if(key.equals("method")) {
	    			method = ((String[]) params.get(key))[0];
	    		}
	    	}
	    }
		if(isFromDb) {
			BreadcrumbDAO breadcrumbDAO = ApplicationContextProvider.getBean(BreadcrumbDAO.class);
			
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("screen", screen);
			paramMap.put("method", method);
			breadcrumb = breadcrumbDAO.getBreadcrumb(paramMap );
			
		} else {
			if(StringUtils.isNotBlank(screen)) {
		    	final HashMap<String, Breadcrumb> hashMap = detailsMap.get(screen);
		    	if(hashMap != null) {
		    		breadcrumb = hashMap.get(method);
		    	}
		    }
		}
		return breadcrumb;
	}
}

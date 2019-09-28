package com.tutorial.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.tutorial.spring.model.Breadcrumb;
import com.tutorial.spring.model.BreadcrumbItem;

public class BreadcrumbDAOImpl implements BreadcrumbDAO {

	private JdbcTemplate jdbcTemplate;
	
	public BreadcrumbDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Breadcrumb getBreadcrumb(HashMap<String, Object> paramMap) {
		if(paramMap == null) {
			paramMap = new HashMap<>();
		}
		final String screenValue = (String) paramMap.get("screen");
		final String methodValue = (String) paramMap.get("method");
		Breadcrumb breadcrumb = new Breadcrumb();
		if(StringUtils.isBlank(screenValue) || StringUtils.isBlank(methodValue)) {
			return breadcrumb;
		}
		
		final String screen = "screen=" + screenValue;
		final String method = "method=" + methodValue;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT bd.title, bd.url, @pv := bd.fk_parent_breadcrumb_id fk_parent_breadcrumb_id")
			.append(" FROM (SELECT * FROM breadcrumb_details order by id desc) bd")
			.append(" JOIN (SELECT @pv := (")
			.append(" SELECT id FROM breadcrumb_details")
			.append(" WHERE")
			.append(" url REGEXP '[[:<:]]" + screen + "[[:>:]]'")
			.append(" AND url REGEXP '[[:<:]]" + method + "[[:>:]]'")
			.append(" )")
			.append(" ) TEMP")
			.append(" WHERE bd.id = @pv");
		
		List<BreadcrumbItem> breadcrumbItems = jdbcTemplate.query(sb.toString(), new RowMapper<BreadcrumbItem>() {
			@Override
			public BreadcrumbItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new BreadcrumbItem(rs.getString("title"), rs.getString("url"));
			}
		});
		Collections.reverse(breadcrumbItems);
		breadcrumb.setBreadcrumbItems((ArrayList<BreadcrumbItem>) breadcrumbItems);
		return breadcrumb;
	}
}
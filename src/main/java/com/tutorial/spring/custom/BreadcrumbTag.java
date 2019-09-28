package com.tutorial.spring.custom;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.tutorial.spring.model.Breadcrumb;
import com.tutorial.spring.model.BreadcrumbItem;
import com.tutorial.spring.utils.BreadcrumbUtils;

public class BreadcrumbTag implements Tag {

	private PageContext pageContext;
	private Tag parentTag;
	private String separator;
	private String cssClass;
	private String cssStyle;
	
	public BreadcrumbTag() {
		super();
		this.separator = " -> ";
		this.cssClass = "";
		this.cssStyle = "";
	}

	@Override
	public void setPageContext(PageContext pc) {
		this.pageContext = pc;
	}

	@Override
	public void setParent(Tag t) {
		this.parentTag = t;
	}

	@Override
	public Tag getParent() {
		return null;
	}

	@Override
	public int doStartTag() throws JspException {
		return Tag.SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		final HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        final Breadcrumb breadcrumb = BreadcrumbUtils.getBreadcrumb(request, BreadcrumbUtils.IS_BREADCRUMB_DETAILS_FROM_DB);
        if(breadcrumb == null || breadcrumb.getBreadcrumbItems() == null || breadcrumb.getBreadcrumbItems().size() == 0) {
        	return Tag.EVAL_PAGE;
        }

        JspWriter out = pageContext.getOut();
        try {
        	final StringBuffer sb = new StringBuffer("");
            final HttpServletResponse httpServletResponse = (HttpServletResponse) this.pageContext.getResponse();
        	final ArrayList<BreadcrumbItem> breadcrumbItems = breadcrumb.getBreadcrumbItems();
        	final int totalItems = breadcrumbItems.size();
        	int i = 0;
            while (i < totalItems) {
            	boolean isLastItem = (i == (totalItems - 1));
            	final BreadcrumbItem breadcrumbItem = breadcrumbItems.get(i);
            	if(isLastItem) {
            		sb.append("<span");
            	} else {
            		sb.append("<a href=\"")
                	.append(httpServletResponse.encodeURL(breadcrumbItem.getUrl()))
                	.append("\"");
            	}
                if (this.cssStyle != null) {
                    sb.append(" style=\"")
                    	.append(cssStyle)
                    	.append("\"");
                }
                if (this.cssClass != null) {
                    sb.append(" class=\"")
                    	.append(cssClass)
                    	.append("\"");
                }
                sb.append(">")
                	.append(breadcrumbItem.getTitle())
                	.append(isLastItem ? "</span>" : "</a>");
                if (!isLastItem) {
                    sb.append(this.separator);
                }
                i++;
            }
            sb.append("\n");
        	out.write(sb.toString());
        	out.flush();
        } catch (IOException e) {
        	e.printStackTrace();
        	throw new JspException(e);
        }
        return Tag.EVAL_PAGE;
	}

	@Override
	public void release() {
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssStyle() {
		return cssStyle;
	}

	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
}
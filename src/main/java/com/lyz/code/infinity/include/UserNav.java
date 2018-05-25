package com.lyz.code.infinity.include;

import com.lyz.code.infinity.domain.Include;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.verb.ListAllByPage;

public class UserNav extends Include{	
	public UserNav(){
		super();
		this.fileName = "usernav.jsp";
		this.packageToken = "";
	}

	@Override
	public String generateIncludeString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- Common Navigation Panel for our site -->\n");
		sb.append("<li id=\"submenu\">\n");
		sb.append("\t<h2><a href=\"../index.jsp\">Homepage</a></h2><br/>\n");
		sb.append("\t<h2><a href=\"../jsonjsp/index.jsp\">Json UI</a></h2><br/>\n");
		sb.append("\t<h2><a>Select an option</a></h2>\n");
		sb.append("\t<ul>\n");
		for (ListAllByPage lsa : this.allListAllByPageList){
			sb.append("\t\t<li><a href=\""+"../controller/"+StringUtil.lowerFirst(lsa.getVerbName())+"Controller\">"+lsa.getVerbName()+"</a></li>\n");   
		}
		sb.append("\t</ul>\n");
		sb.append("</li>\n");
		return sb.toString();
	}
}

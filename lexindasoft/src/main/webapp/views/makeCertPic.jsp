<%@page contentType="image/jpeg" %>
　　<jsp:useBean id="image" scope="page" class="com.lexindasoft.utils.MakeCertPic" />
　　<%
	String str=image.getCertPic(0,0,response.getOutputStream());
	session.setAttribute("certCode", str);
	out.clear();
	out = pageContext.pushBody();
	%>
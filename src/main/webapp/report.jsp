<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ListIterator"%>
<%@page import="com.upperlink.pojo.Summary"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Page</title>
</head>
<body >

<div align="center" style="margin-top: 50px">
	<%
		ArrayList<Summary> object = (ArrayList<Summary>)request.getAttribute("reportSummary");
	%>
	<table>
		<thead align="center">
			<tr>
				<td>SKILL | </td>
				<td>NUMBER OF DEVELOPERS</td>
			</tr>
		<%
			for(Summary summary : object) {
				
				out.println("<tr>");
				out.println("<td>");
			    out.println(summary.getSkills());
			    out.println("</td>");
				out.println("<td>");
				out.println(summary.getDeveloper());
				out.println("</td>");			    
			    out.println("<tr>");
			}
		%>
		</thead>
	</table>
</div>

</body>
</html>
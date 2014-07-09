<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
<body>
	<Table>
		<tr>
			<td>
				<html:link action="HomeStartAction.do" module="/home">Home</html:link>
			</td>
			<td>
				<html:link action="AvatarStart.do" module="/avatar">Charakter</html:link>
			</td>
			<td>
				<html:link action="AdventureStartAction.do" module="/adventure">Abenteuer</html:link>
			</td>
			<td>
				<html:link action="Logout.do" module="/">Logout</html:link>
			</td>
		</tr>
	</Table>
</body>
</html>
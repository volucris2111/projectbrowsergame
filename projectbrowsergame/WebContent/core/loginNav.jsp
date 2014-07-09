<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
<body>
	<table>
		<tr>
			<td>
				<html:link action="Login.do">Login</html:link>
			</td>
			<td>
				<html:link action="UserCreateStartAction.do">Registrieren</html:link>
			</td>
		</tr>
	</table>
</body>
</html>
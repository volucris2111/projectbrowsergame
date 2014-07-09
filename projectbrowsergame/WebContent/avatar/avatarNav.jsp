<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
	<body>
		<table>
			<tr>
				<td>
					<html:link action="AvatarStart.do" module="/avatar">Übersicht</html:link>
				</td>
			</tr>
			<tr>
				<td>
					<html:link action="AvatarInventory.do" module="/avatar">Inventar</html:link>
				</td>
			</tr>
		</table>
	</body>
</html>
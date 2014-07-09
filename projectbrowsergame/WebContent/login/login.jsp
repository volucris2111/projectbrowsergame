<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html:html lang="true">
	<body style="background-image:url(../images/bg/HG.png)">
		<html:form action="/LoginPostAction.do">
			<table>
				<tr>
					<td>
						Username:
					</td>
					<td>
						<html:text name="loginForm" property="currentUser.name"/>
					</td>
					<td>
						Passwort:
					</td>
					<td>
						<html:password name="loginForm" property="currentUser.password"/>
					</td>
					<td>
						<html:submit value="Login" property="login"/>
					</td>
				</tr>
				<logic:notEmpty name="loginForm" property="message">
				<tr>
					<td colspan="5">
						<font color="red"><b><bean:write name="loginForm" property="message"/></b></font>
					</td>
				</tr>
				</logic:notEmpty>
			</table>
		</html:form>  
	</body>
</html:html>

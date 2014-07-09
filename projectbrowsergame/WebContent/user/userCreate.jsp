<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<html>
	<body>
		<html:form action="UserCreatePostAction.do" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						Username:
					</td>
					<td>
						<html:text name="loginForm" property="currentUser.name"/>
					</td>
				</tr>
				<tr>
					<td>
						Passwort:
					</td>
					<td>
						<html:password name="loginForm" property="currentUser.password"/>
					</td>
				</tr>
				<tr>
					<td>
						Passwort wiederholen:
					</td>
					<td>
						<html:password name="loginForm" property="currentUser.passwordRepeat"/>
					</td>
				</tr>
				<tr>
					<td>
						Mailadresse:
					</td>
					<td>
						<html:text name="loginForm" property="currentUser.mailaddress"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit value="Registrieren"  property="register"/>
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
</html>
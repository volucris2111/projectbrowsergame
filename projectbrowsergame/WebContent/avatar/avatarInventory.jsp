<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<body>
		<html:form action="AvatarDetailsPost.do" enctype="multipart/form-data">
			<table>
				<tr>
					<td colspan="2">
						 Inventar von <bean:write name="avatarForm" property="currentAvatar.name"/>:
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						Münzbeutel:
					</td>
					<td>
						<bean:write name="avatarForm" property="currentAvatar.inventory.coins" format="##"/>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<logic:iterate  name="avatarForm" property="currentAvatar.inventory.resources" id="currentResource">
				<tr>
					<td>
						<bean:write name="currentResource" property="key.resourceForJsp"/>:
					</td>
					<td>
						<bean:write name="currentResource" property="value" format="##"/>
					</td>
				</tr>
				</logic:iterate>
			</table>
		</html:form>
	</body>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ page import="com.browsergame.project.attribute.constants.AttributeNameConstants"%>
<html>
	<body>
		<html:form action="AvatarGroupPost.do" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<bean:write name="avatarForm" property="currentAvatar.name"/>
					</td>
				</tr>
				<logic:iterate name="avatarForm" property="currentAvatar.masters" id="currentMaster">
					<tr>
						<td>
							<bean:write name="currentMaster" property="name"/>
						</td>
					</tr>				
				</logic:iterate>
			</table>
		</html:form>
	</body>
</html>
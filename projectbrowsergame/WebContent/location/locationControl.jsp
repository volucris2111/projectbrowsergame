<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="LocationControlPostAction.do">
		<table>
			<tr>
				<td>
					<logic:equal name="adventureForm" property="currentField.location.ownerAvatarId" value="${adventureForm.userLoggedInHero.avatarId}">
						<html:submit value="Produzieren" property="produce"/>
					</logic:equal>
				</td>
			</tr>
		</table>
	</html:form>
</html>
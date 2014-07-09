<%@ page language="java" pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<body>
		<html:form action="AvatarCreatePost.do" enctype="multipart/form-data">
			<table>
				<tr>
					<td colspan="2">
						<bean:write name="avatarForm" property="errorMessage"/>
					</td>
				</tr>
				<tr>
					<td>
						 Name:
					</td>
					<td>
						<html:text name="avatarForm" property="currentAvatar.name"/> <html:submit value="Zufall" property="randomName"></html:submit>
					</td>
				</tr>
				<tr>
					<td>
						 Geschlecht:
					</td>
					<td>
						<html:select name="avatarForm" property="currentAvatar.male">
							<html:option value="true">Männliche</html:option>
							<html:option value="false">Weiblich</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table>
							<tr>
								<logic:iterate name="avatarForm" property="availableAvatarClasses" id="currentAvatarClass" indexId="currentAvatarClassIndex">
									<td valign="top">
										<table>
											<tr>
												<td colspan="2">
													<html:radio name="avatarForm" property="selectedAvatarClassIndex" value="${currentAvatarClassIndex}"></html:radio>
													<font style="text-decoration: underline; font-weight:bold"><bean:write name="currentAvatarClass" property="nameForJspMale"/>/<bean:write name="currentAvatarClass" property="nameForJspFemale"/></font>
												</td>
											</tr>
											<tr>
												<td style="text-decoration: underline">
													Fähigkeit:
												</td>
												<td style="text-decoration: underline">
													Stufe:
												</td>
											</tr>
											<logic:iterate name="currentAvatarClass" property="skills" id="currentSkill">
											<tr>
												<td>
													<bean:write name="currentSkill" property="value.nameForJsp"/>:
												</td>
												<td>
													<bean:write name="currentSkill" property="value.lvl" format="##"/>
												</td>
											</tr>
											</logic:iterate>
										</table>
									</td>
								</logic:iterate>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:submit value="Annehmen" property="accept"/>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
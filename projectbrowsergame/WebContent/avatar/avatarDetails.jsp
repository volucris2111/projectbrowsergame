<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ page import="com.browsergame.project.attribute.constants.AttributeNameConstants"%>
<html>
	<body>
		<html:form action="AvatarDetailsPost.do" enctype="multipart/form-data">
			<table>
				<tr>
					<td align="center">
						<table>
							<tr>
								<td align="center" style="text-decoration: underline; font-weight:bold" colspan="4" align="center">
									Allgemein
								</td>
							</tr>
							<tr>
								<td>
									 Spieler:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.user.name"/>
								</td>
								<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
								<td>
									Lebenspunkte:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.lifePointsCurrent" format="##"/>/<bean:write name="avatarForm" property="currentAvatar.lifePointsMax" format="##"/>
								</td>
								</logic:equal>
							</tr>
							<tr>
								<td>
									 Charaktername:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.name"/>
								</td>
								<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
								<td>
									Ausdauerpunkte:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.staminaPointsCurrent" format="##"/>/<bean:write name="avatarForm" property="currentAvatar.staminaPointsMax" format="##"/>
								</td>
								</logic:equal>
							</tr>
							<tr>
								<td>
									 Stufe:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.level" format="##"/>
									<html:hidden name="avatarForm" property="currentAvatar.level" styleId="avatarLevelHidden"/>
									<bean:define name="avatarForm" property="currentAvatar.level" id="avatarLevel"></bean:define>
								</td>
								<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
								<td>
									Seelenpunkte:
								</td>
								<td>
									<bean:write name="avatarForm" property="currentAvatar.spiritPointsCurrent" format="##"/>/<bean:write name="avatarForm" property="currentAvatar.spiritPointsMax" format="##"/>
								</td>
								</logic:equal>
							</tr>
							<tr>
								<td>
									 Geschlecht:
								</td>
								<td>
									<logic:equal name="avatarForm" property="currentAvatar.male" value="true">
										männlich
									</logic:equal>
									<logic:equal name="avatarForm" property="currentAvatar.male" value="false">
										weiblich
									</logic:equal>
								</td>
								<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
								<td colspan="2">
									&nbsp;
								</td>
								</logic:equal>
							</tr>
							<tr>
								<td>
									 Beruf:
								</td>
								<td>
									<logic:equal name="avatarForm" property="currentAvatar.male" value="true">
										<bean:write name="avatarForm" property="currentAvatar.avatarClass.nameForJspMale"/>
									</logic:equal>
									<logic:equal name="avatarForm" property="currentAvatar.male" value="false">
										<bean:write name="avatarForm" property="currentAvatar.avatarClass.nameForJspFemale"/>
									</logic:equal>
								</td>
								<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
								<td colspan="2">
									<html:submit value="Gruppe" property="group"/>
								</td>
								</logic:equal>
							</tr>
						</table>
					</td>
					<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
					<td valign="top" align="center">
						<html:hidden name="avatarForm" property="attributePointsToSpend" styleId="attributePointsToSpend"/>
						<table>
							<tr>
								<td colspan="8" style="text-decoration: underline; font-weight:bold" align="center" id="attrbiuteTitelTd">
									Eigenschaften
									<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
										(<bean:write name="avatarForm" property="attributePointsToSpend" format="##"/> Punkte verfügbar)
									</logic:equal>
								</td>
							</tr>
							<tr>
								<nested:iterate name="avatarForm" property="currentAvatar.attributes" id="currentAttribute" indexId="currentAttributeIndex">
									<%if((currentAttributeIndex + 1) % 2 == 1){%>
									<tr>
									<%}%>
										<td>
											<nested:write property="nameForJsp"/>
											<nested:hidden property="newLvl" value="${currentAttribute.value.lvl}" styleId="${currentAttributeIndex}NewLevel"/>
											<nested:hidden property="lvl" styleId="${currentAttributeIndex}OldLevel"/>
										</td>
										<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
											<td>
												&nbsp;<html:button property="${currentAttributeIndex}-" value="-" style="display:inline" onclick="changeAttributeValue(this)" styleId="${currentAttributeIndex}-"/>
											</td>
										</logic:equal>
										<td id="attributeTd${currentAttributeIndex}">
											<bean:write name="currentAttribute" property="value.lvl" format="##"/>
										</td>
										<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
											<td>
												<html:button property="${currentAttributeIndex}+" value="+" onclick="changeAttributeValue(this)" style="display:inline" styleId="${currentAttributeIndex}+"/>&nbsp;
											</td>
										</logic:equal>
									<%if((currentAttributeIndex + 1) % 2 == 0){%>
									</tr>
									<%}%>
								</nested:iterate>
								<td colspan="4" align="center">
									<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
										<html:submit value="Speichern" property="save"/>
									</logic:equal>
									&nbsp;
								</td>
							</tr> 
						</table>
					</td>
					</logic:equal>
				</tr>
				<logic:equal name="avatarForm" property="ownerOfAvatar" value="true">
				<tr>
					<td colspan="2">
						<table>
							<tr>
								<td colspan="2" style="text-decoration: underline; font-weight:bold" align="center">
									Fähigkeiten
								</td>
							</tr>
							<tr>
								<td valign="top">
									<table>
										<tr>
											<td style="text-decoration: underline;" align="center" colspan="4">
												Handwerk:
											</td>
										</tr>
										<logic:iterate name="avatarForm" property="currentAvatar.workingSkills" id="currentSkill" indexId="currentSkillIndex">
											<%if((currentSkillIndex + 1) % 2 == 1){%>
											<tr>
											<%}%>
												<td>
													<bean:write name="currentSkill" property="value.nameForJsp"/><logic:equal name="currentSkill" property="value.specialization" value="true">*</logic:equal>
												</td>
												<td>
													<bean:write name="currentSkill" property="value.lvl" format="##"/>
												</td>
											<%if((currentSkillIndex + 1) % 2 == 0){%>
											</tr>
											<%}%>
										</logic:iterate>
									</table>
								</td>
								<td valign="top">
									<table>
										<tr>
											<td style="text-decoration: underline;" align="center" colspan="4">
												Kampf:
											</td>
										</tr>
										<logic:iterate name="avatarForm" property="currentAvatar.fightingSkills" id="currentSkill" indexId="currentSkillIndex">
											<%if((currentSkillIndex + 1) % 2 == 1){%>
											<tr>
											<%}%>
												<td>
													<bean:write name="currentSkill" property="value.nameForJsp"/>
												</td>
												<td>
													<bean:write name="currentSkill" property="value.lvl" format="##"/>
												</td>
											<%if((currentSkillIndex + 1) % 2 == 0){%>
											</tr>
											<%}%>
										</logic:iterate>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="avatarForm" property="ownerOfAvatar" value="false">
				<tr>
					<td>
						<table>
							<tr>
								<td>
									<html:submit value="Angreiffen" property="attack"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				</logic:equal>
			</table>
		</html:form>
	</body>
</html>
<script type="text/javascript">
function setupAttributeChangeButtons()
{
	var attributeCounter = 0;
	var attributePointsSum = 0;
	var avatarLevel = parseInt(document.getElementById("avatarLevelHidden").value);
	while(document.getElementById(attributeCounter + "OldLevel") != null)
		{
			var newLevel = parseInt(document.getElementById(attributeCounter + "NewLevel").value);
			var oldLevel = parseInt(document.getElementById(attributeCounter + "OldLevel").value);
			if(oldLevel == newLevel)
				{
				document.getElementById(attributeCounter + "-").style.display="none";
				}
			else
			{
				document.getElementById(attributeCounter + "-").style.display="inline";
			}
			attributePointsSum = attributePointsSum + newLevel;
			attributeCounter++;
		}
	if (attributePointsSum < (avatarLevel-1) * 3 + 7)
	{
		attributeCounter = 0;
		while(document.getElementById(attributeCounter + "OldLevel") != null)
		{
			document.getElementById(attributeCounter + "+").style.display="inline";
			attributeCounter++;
		}
	}
	else
	{
		attributeCounter = 0;
		while(document.getElementById(attributeCounter + "OldLevel") != null)
		{
			document.getElementById(attributeCounter + "+").style.display="none";
			attributeCounter++;
		}
	}
}
function changeAttributeValue(button)
{
	var selectedAttributeIndex = parseInt(button.name);
	var newLevel = parseInt(document.getElementById(selectedAttributeIndex + "NewLevel").value);
	var oldLevel = parseInt(document.getElementById(selectedAttributeIndex + "OldLevel").value);
	var attributePointsToSpend = document.getElementById("attributePointsToSpend").value;
	if(button.value == "+")
	{
		newLevel++;
		attributePointsToSpend--;
	}
	if(button.value == "-")
	{
		newLevel--;
		attributePointsToSpend++;
	}
	document.getElementById(selectedAttributeIndex + "NewLevel").value = newLevel;
	document.getElementById("attributePointsToSpend").value = attributePointsToSpend;
	document.getElementById("attributeTd"+selectedAttributeIndex).innerText = oldLevel + "("+newLevel+")";
	document.getElementById("attrbiuteTitelTd").innerText = "Eigenschaften ("+attributePointsToSpend+" Punkte verfügbar)";
	setupAttributeChangeButtons();
}
window.onload = setupAttributeChangeButtons();
</script>
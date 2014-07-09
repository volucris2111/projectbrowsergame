<%@ page import="com.browsergame.project.terrain.constants.TerrainActionsEnum"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<table>
	<tr>
		<td>
			<table style="border-collapse: collapse;">
				<logic:iterate name="adventureForm" property="adventureMap" id="currentAdventureMapRow">
					<tr style="padding: 0;">
						<logic:iterate name="currentAdventureMapRow" property="fieldsOfRow" id="currentField">
						<td style="background-image: url(${currentField.backgroundImageUrl}); background-width=100px; background-height=100px; padding: 0;">
							<html:image onclick="ajaxRefreshMapWithMove(${currentField.positionX}, ${currentField.positionY})" src="/projectbrowsergame/${currentField.fieldObjectImageUrl}"></html:image>
						</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			</table>
		</td>
		<td valign="top">
			<table>
				<tr>
					<td>
						<bean:write name="adventureForm" property="currentField.terrain.terrainForDisplay"/> (<bean:write name="adventureForm" property="currentField.positionX" format="###"/>/<bean:write name="adventureForm" property="currentField.positionY" format="###"/>)
					</td>
				</tr>
				<logic:notEqual name="adventureForm" property="currentField.buildingId" value="0">
					<tr>
						<td>
							<bean:write name="adventureForm" property="currentField.building.buildingType.nameForJsp"/><logic:notEmpty name="adventureForm" property="currentField.building.name">"<bean:write name="adventureForm" property="currentField.building.name"/>"</logic:notEmpty>
						</td>
					</tr>
				</logic:notEqual>
				<logic:equal name="adventureForm" property="actionsAvailable" value="true">
					<tr>
						<td>
							<html:form action="AdventureWorldPostAction.do">
								<table>
									<tr>
										<td>
											Mögliche Aktionen:
										</td>
									</tr>
									<logic:equal value="true" name="adventureForm" property="currentField.buildable">
										<logic:iterate name="adventureForm" property="currentField.terrain.actions" id="currentAction">
											<tr>
												<td>
													<html:button styleId="terrainActionButton" property="" onclick="<%=TerrainActionsEnum.valueOf(currentAction.toString()).getJspMethodCall()%>" value="<%=TerrainActionsEnum.valueOf(currentAction.toString()).getButtonText()%>">
													</html:button>
												</td>
											</tr>
										</logic:iterate>
									</logic:equal>
									<logic:equal value="true" name="adventureForm" property="currentField.buildable">
										<tr>
											<td>
												
												<html:submit value="Bauen" property="build"></html:submit>
											</td>
										</tr>
									</logic:equal>
									<logic:notEqual value="0" name="adventureForm" property="currentField.buildingId">
										<logic:equal value="false" name="adventureForm" property="userLoggedInHero.inside">
										<tr>
											<td>
												<html:submit value="${adventureForm.currentField.building.buildingType.nameForJsp} betrachten" property="buildingWatch"></html:submit>
											</td>
										</tr>
										<tr>
											<td>
												<html:submit value="${adventureForm.currentField.building.buildingType.nameForJsp} betreten" property="buildingEnter"></html:submit>
											</td>
										</tr>
										</logic:equal>
										<logic:equal value="true" name="adventureForm" property="userLoggedInHero.inside">
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.building.buildingType.nameForJsp} details" property="buildingDetails"></html:submit>
												</td>
											</tr>
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.building.buildingType.nameForJsp} verlassen" property="buildingLeave"></html:submit>
												</td>
											</tr>
										</logic:equal>
									</logic:notEqual>
									<logic:notEqual value="0" name="adventureForm" property="currentField.locationId">
										<logic:equal value="false" name="adventureForm" property="userLoggedInHero.inside">
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.location.locationType.nameForJsp} betrachten" property="locationWatch"></html:submit>
												</td>
											</tr>
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.location.locationType.nameForJsp} betreten" property="locationEnter"></html:submit>
												</td>
											</tr>
										</logic:equal>
										<logic:equal value="true" name="adventureForm" property="userLoggedInHero.inside">
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.location.locationType.nameForJsp} details" property="locationDetails"></html:submit>
												</td>
											</tr>
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.location.locationType.nameForJsp} verlassen" property="locationLeave"></html:submit>
												</td>
											</tr>
										</logic:equal>
										<logic:equal value="${adventureForm.currentField.location.ownerAvatarId}" name="adventureForm" property="userLoggedInHero.avatarId">
											<tr>
												<td>
													<html:submit value="${adventureForm.currentField.location.locationType.nameForJsp} verwalten" property="locationManage"></html:submit>
												</td>
											</tr>	
										</logic:equal>
									</logic:notEqual>
									<logic:notEmpty name="adventureForm" property="currentField.allAvatarsOutside">
										<tr>
											<td>
												<table>
													<tr>
														<td>
															Andere Helden:
															<html:hidden property="selectedAvatarId" value="0" styleId="selctedAvatarId"/>
														</td>
													</tr>
													<logic:iterate id="currentAvatar" name="adventureForm" property="currentField.allAvatarsOutside">
														<tr>
															<td>
																<html:submit value="${currentAvatar.name}" property="avatarDetails" onclick="setSelectedAvatarId(${currentAvatar.avatarId})"></html:submit>
															</td>
														</tr>
													</logic:iterate>
												</table>
											</td>
										</tr>
									</logic:notEmpty>
									
								</table>
							</html:form>
						</td>
					</tr>
				</logic:equal>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<bean:write name="adventureForm" property="message"/>
		</td>
	</tr>
</table>
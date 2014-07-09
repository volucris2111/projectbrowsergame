<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<body>
			<table>
				<tr>
					<td>
						<logic:notEmpty name="adventureForm" property="buildableBuildings">
							<logic:iterate name="adventureForm" property="buildableBuildings" id="currentBuildingType">
								<html:form action="BuildingCreatePostAction.do" enctype="multipart/form-data">
									<table>
										<tr>
											<td colspan="10">
												<font style="text-decoration:underline"><bean:write name="currentBuildingType" property="nameForJsp"/></font>
											</td>
										</tr>
										<tr>
											<td>
												Kosten:
											</td>
											<logic:equal name="adventureForm" property="userLoggedInHero.firstBuilding" value="false">
												<logic:iterate name="currentBuildingType" property="costs" id="currentResource">
													<td>
														<bean:write name="currentResource" property="key.resourceForJsp"/>:
													</td>
													<td>
														<bean:write name="adventureForm" property="userLoggedInHero.inventory.valueOfResource(${currentResource.key})" format="##"/>/<bean:write name="currentResource" property="value" format="##"/>
													</td>
												</logic:iterate>
											</logic:equal>
											<logic:equal name="adventureForm" property="userLoggedInHero.firstBuilding" value="true">
											<td>
												Erstes Gebäude ohne Kosten!
											</td>
											</logic:equal>
										</tr>
										<tr>
											<td>
												<logic:equal name="currentBuildingType" property="enoughResources" value="true">
													<html:hidden name="adventureForm" property="selectedBuildingTypeId" value="${currentBuildingType.buildingTypeId}"/><html:submit value="Bauen" property="build"/>
												</logic:equal>
												<logic:equal name="adventureForm" property="userLoggedInHero.firstBuilding" value="true">
													<html:hidden name="adventureForm" property="selectedBuildingTypeId" value="${currentBuildingType.buildingTypeId}"/><html:submit value="Bauen" property="build"/>
												</logic:equal>
												<logic:equal name="adventureForm" property="userLoggedInHero.firstBuilding" value="false">
													<logic:equal name="currentBuildingType" property="enoughResources" value="false">
														Nicht genug Rohstoffe!
													</logic:equal>
												</logic:equal>
											</td>
										</tr>
									</table>
								</html:form>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="adventureForm" property="buildableBuildings">
							Du kannst hier nichts bauen.
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td>
						<html:form action="BuildingCreatePostAction.do" enctype="multipart/form-data">
							<html:submit value="Zurück" property="back"/><logic:equal name="adventureForm" property="loggedInUser.name" value="Dome"><html:submit value="Camp erzeugen" property="createCamp"/></logic:equal>
						</html:form>
					</td>
				</tr>
			</table>
	</body>
</html>
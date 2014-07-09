<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="LocationDetailsPostAction.do">
		<table>
			<tr>
				<td>
					Lager <logic:notEmpty name="adventureForm" property="currentField.location.name">"<bean:write name="adventureForm" property="currentField.location.name"/>"</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td>
					Einwohner: <bean:write name="adventureForm" property="currentField.location.population" format="##"/>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<html:submit value="Marktplatz" property="locationFreeMarket"/>
							</td>
							<td>
								<html:submit value="Taverne" property="tavern"/>
							</td>
							<td>
								<html:submit value="Verwaltung" property="townHall"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<html:submit value="Stadtmauern(Aussicht)" property="map"/>
							</td>
							<td>
								<html:submit value="Stadttor(Verlassen)" property="leave"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>			
				<td>
					<table>
						<logic:empty name="adventureForm" property="currentField.allAvatarsInside">
							<tr>
								<td>
									Es befinden sich keine anderen Charakteren in diesem Ort!
								</td>
							</tr>
						</logic:empty>
						<logic:notEmpty name="adventureForm" property="currentField.allAvatarsInside">
							<tr>
								<td>
									Folgende Charakteren befinden sich in diesem Ort:
								</td>
							</tr>
							<logic:iterate name="adventureForm" property="currentField.allAvatarsInside" id="currentAvatar">
								<tr>
									<td>
										<bean:write name="currentAvatar" property="name"/>
									</td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</html>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html>
	<html:form action="LocationTavernPostAction.do">
		<table>
			<tr>
				<td colspan="2">
					Taverne
				</td>
			</tr>
			<logic:notEmpty name="adventureForm" property="message">
			<tr>
				<td colspan="2">
					<bean:write name="adventureForm" property="message"/>
				</td>
			</tr>
			</logic:notEmpty>
			<logic:notEmpty name="adventureForm" property="location.tavern.shownMaster">
			<tr>
				<td>
					<table>
						<tr>
							<td colspan="2">
								<bean:write name="adventureForm" property="location.tavern.shownMaster.name"/>
							</td>
						</tr>
						<tr>
							<td>
								 Geschlecht:
							</td>
							<td>
								<logic:equal name="adventureForm" property="location.tavern.shownMaster.male" value="true">
									männlich
								</logic:equal>
								<logic:equal name="adventureForm" property="location.tavern.shownMaster.male" value="false">
									weiblich
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								 Beruf:
							</td>
							<td>
								<logic:equal name="adventureForm" property="location.tavern.shownMaster.male" value="true">
									<bean:write name="adventureForm" property="location.tavern.shownMaster.avatarClass.nameForJspMale"/>
								</logic:equal>
								<logic:equal  name="adventureForm" property="location.tavern.shownMaster.male" value="false">
									<bean:write  name="adventureForm" property="location.tavern.shownMaster.avatarClass.nameForJspFemale"/>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								Kosten:
							</td>
							<td>
								<bean:write  name="adventureForm" property="location.tavern.shownMaster.costs" format="##"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<html:submit value="Anwerben" property="recrute"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</logic:notEmpty>
			<tr>
				<td>
					<html:submit value="Nach Meister Suchen" property="findMaster"/>
				</td>
				<td>
					(Kosten: 500 Münzen)
				</td>
			</tr>
			<tr>
				<td>
					Meister verfügbar:
				</td>
				<td>
					<bean:write name="adventureForm" property="location.tavern.amountOfMasters" format="##"/>
				</td>
			</tr>
		</table>
	</html:form>
</html>
<%@ page language="java" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<html:html lang="true">
<body style="background-image:url(../images/bg/HG.png)">
	  <table>
	  	<tr>
	  		<td colspan="2">
	    		<tiles:insert attribute="head"/>
	    	</td>
	    </tr>
	    <tr>
		    <td colspan="2">
		    	<tiles:insert attribute="firstNav"/>
		    </td>
	    </tr>
	    <tr>
		    <td align="left" valign="top">
			    <tiles:insert attribute="secNav"/>
		    </td>
		    <td align="left" valign="top">
		    	<tiles:insert attribute="body"/>
		    </td>
	    </tr>
    </table>
  </body>
</html:html>
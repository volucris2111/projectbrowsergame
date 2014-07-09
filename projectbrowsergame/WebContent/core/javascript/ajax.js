
function sendManualAjaxRequest(ajaxUrl, queryString, additionalMethod, messageDiv)
{
	try
	{
		var XMLHTTP = getAjaxRequestObject(ajaxUrl, "", true);
		
		if (XMLHTTP != null) 
		{
			XMLHTTP.onreadystatechange = function alertAJAXResponse() 
			{
				if (XMLHTTP.readyState == 4)
				{
					var response = XMLHTTP.responseText;
					if (response != null && response != "")
					{
						if (messageDiv != null)
						{
							messageDiv.innerHTML = response;
						}
					}
					if (additionalMethod)
					{
						additionalMethod = additionalMethod.replace(/\\$message\\$/,response);
						eval(additionalMethod);
					}
				}
			};
			XMLHTTP.send(queryString);
		}
	}
	catch(e)
	{
		debugObject.append(e);
	}
	return false;
}

function getAjaxRequestObject(remoteFunction, successMessage, overwriteReadyState) 
{
	var XMLHTTP = null;
	if (window.XMLHttpRequest) 
	{
		
		XMLHTTP = new XMLHttpRequest();
	} 
	else 
	{
		if (window.ActiveXObject) 
		{
			try 
			{
				XMLHTTP = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) 
			{
				try 
				{
					XMLHTTP = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) 
				{}
			}
		}
	}
	if (XMLHTTP != null) 
	{
		if (remoteFunction.indexOf("http://") == -1 && remoteFunction.indexOf("https://") == -1 && remoteFunction.indexOf(getContextPath()) == -1)
		{
			remoteFunction = getContextPath() + remoteFunction;
		}
		XMLHTTP.open("POST", remoteFunction);
		XMLHTTP.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		if (!overwriteReadyState)
		{
			XMLHTTP.onreadystatechange = function alertAJAXResponse() 
			{
				if (XMLHTTP.readyState == 4 && successMessage) 
				{
					debugObject.append(successMessage);
				}
			};
		}
	}
	return XMLHTTP;
}
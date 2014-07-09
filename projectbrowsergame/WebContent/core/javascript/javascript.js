function getContextPath()
{
	var contextPath = window.location.pathname;
	contextPath = contextPath.substring(1, contextPath.length);
	contextPath = "/" + contextPath.substring(0, contextPath.indexOf("/") + 1);
	return contextPath;
}
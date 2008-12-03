<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><tiles:getAsString name="title"/></title>
		<link rel="stylesheet" type="text/css" href="style/style.css"/>
	</head>
	<body>
		<div id="container">
			<div id="header">
				<a href="/books"><tiles:getAsString name="title"/></a>
			</div>
			<div id="menu">
				<tiles:insert attribute="menu-position" />
			</div>
			<div id="sidebar">
				<tiles:insert attribute="left-nav-position" />
			</div>
			<div id="main">
				<div id="text">
					<tiles:insert attribute="body-position" />
				</div>
				<div class="clear"></div>
			</div>
			<div id="footer">
				<tiles:insert attribute="foot-position" />
			</div>
		</div>
	</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${!empty username}">
		<a href="#">${username}</a>
	</c:when>
	<c:otherwise>
		<div id="login">
			<label for="login-username">username</label>
			<input type="text" name="username" id="login-username" size="10" />&nbsp;
			<label for="login-password">password</label>
			<input type="password" name="password" id="login-password" size="10" />&nbsp;
			<input type="button" name="login-button" id="login-button" value="login" onclick="addNew()"/>
		</div>
	</c:otherwise>
</c:choose>
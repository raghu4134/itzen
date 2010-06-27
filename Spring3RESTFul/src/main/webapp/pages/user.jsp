<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>User</p>
 
<c:forEach var="user" items="${users}">
	${user.firstName} ${user.lastName}<br/>
	Username: ${user.userName}<br/>
	Preferences:
		<c:forEach var="pref" items="${user.preferences}">
			${perf}
		</c:forEach>
</c:forEach>
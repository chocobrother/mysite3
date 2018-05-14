<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<p class="jr-success">
					회원정보가 변경되었습니다..
					<br><br>
					
				</p>				
			</div>
		</div>
	<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
	<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<c:forEach items="${list}" var = "vo" varStatus = "status">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title}.</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">
							${ vo.content }
							</div>
						</td>
					</tr>
					</c:forEach>
				</table>
				<c:forEach items="${list}" var = "vo" varStatus = "status">
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/list">글목록</a>
					
				<c:if test = "${not empty authUser && sessionScope.authUser.no == vo.user_no}">
					<a href="${pageContext.servletContext.contextPath }/board/modifyform?no=${vo.no}">글수정</a>
					<a href="${pageContext.servletContext.contextPath }/board/replyform?no=${vo.no}&groupNo=${vo.group_no}
					&orderNo=${vo.order_no}&depth=${vo.depth}&user_no=${vo.user_no}">답글달기</a>
				</c:if>
				</div>
				</c:forEach>
			</div>
		</div>
			<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
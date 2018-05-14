<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/reply?groupNo=${map.groupNo}
				&orderNo=${map.orderNo }&depth=${map.depth }&user_no=${map.user_no }&no=${sessionScope.authUser.no} ">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글달기</th>
						</tr>
						<tr>
							<td class="label">답글제목</td>
							<td><input type="text" name="title" value="제목을 써주세요"></td>
						</tr>
						<tr>
							<td class="label">답글내용</td>
							<td>
								<textarea id="content" name="content"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath }/board/list">취소</a>
						<input type="submit" value="등록">
					</div>
				</form>				
			</div>
		</div>
			<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
			<c:import url ="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
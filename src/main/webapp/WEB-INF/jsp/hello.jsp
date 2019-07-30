<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page session = "true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/jsp/include/navigation.jsp" %>

<div class="container">

	<div class="container" id="main">
		<div class="page-header">
			<h1>
				Task Board Manager <small>Task 관리</small>
			</h1>
			<c:if test="${sessionedUser}">
			<a href="/boards/form" class="btn btn-success" style="color:white;"
					role="button">Task Board 추가</a>
			</c:if>
		</div>
		<div class="row">
			<c:forEach var="item" items="${boards}" varStatus="status">
			<div class="col-sm-4 col-md-3">
				<div class="thumbnail">
					<img src="https://image.flaticon.com/icons/svg/1181/1181951.svg" alt="...">
					<div class="caption">
						<h3><c:out value="${item.title}" /></h3>

						<p>
							[등록된 Task <span class="badge"><c:out value="${item.countOfAnswer}" /></span> 개]
						</p>
						<p><c:out value="${item.formattedCreateDate}" /></p>
						<p>
							작성자 <a href="/users/profile/{{writer.id}}" class="author"><c:out value="${item.writer.userId}" /></a>
						</p>
						<p>
							<a href="/boards/{{id}}" class="btn btn-primary" role="button">Board
								이동</a>
						</p>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>

		<div class="row">
			<a href="/?page={{page}}&size=4" class="btn btn-primary">더보기</a>
		
		</div>
</div>


<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
</body>
</html>
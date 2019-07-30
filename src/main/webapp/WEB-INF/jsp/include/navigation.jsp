<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<nav class="navbar navbar-fixed-top header">
	<div class="col-md-12">
		<div class="navbar-header">

			<a href="/" class="navbar-brand">BoardQA</a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse1">
				<i class="glyphicon glyphicon-search"></i>
			</button>

		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse1">
			<form class="navbar-form pull-left">
				<div class="input-group" style="max-width: 470px;">
					<input type="text" class="form-control" placeholder="Search"
						name="srch-term" id="srch-term">
					<div class="input-group-btn">
						<button class="btn btn-default btn-primary" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>

		</div>
	</div>
</nav>

<div class="navbar navbar-default" id="subnav">
	<div class="col-md-12">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#navbar-collapse2">
			<i class="glyphicon glyphicon-align-justify"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbar-collapse2">
			<ul class="nav navbar-nav navbar-right">
				<li class="nav-item"><a href="/">Posts</a></li>
				<sec:authorize access="!isAuthenticated()">
				  Login
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				  Logout
				</sec:authorize>
				<sec:authorize access="hasRole('ADMIN')">
				    Manage Users
				</sec:authorize>
				<c:if test="${!sessionedUser}">
					<li class="nav-item"><a href="/users/login" role="button">로그인</a></li>
					<li class="nav-item"><a href="/users/form" role="button">회원가입</a></li>
				</c:if>
				<c:if test="${sessionedUser}">
					<li class="nav-item"><a href="/users/logout" role="button">로그아웃</a></li>
					<li class="nav-item"><a href="/users/{{id}}/form"
						role="button">개인정보수정</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>


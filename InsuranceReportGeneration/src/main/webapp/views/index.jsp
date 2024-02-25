<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>

	<div class="container">
		<h3 class="pb-3 pt-3">Welcome to Insurence Report Generation</h3>
	

	<form:form modelAttribute="search" action="/search" method="post" >
	<table>
	<tr>
	<td>Plan Name:</td>
	<td>
	<form:select path="planName">
	<form:option value="">-Select</form:option>
	<form:options items="${name}"   />
	</form:select>
	</td>
	<td>Plan Status:</td>
	<td>
	<form:select path="planStatus">
	<form:option value="">-Select</form:option>
	<form:options items="${status}"   />
	</form:select>
	</td>
	<td>Gender:</td>
	<td>
	<form:select path="gender">
	<form:option value="">-Select</form:option>
	<form:option value="Male">Male</form:option>
	<form:option value="Female">Female</form:option>
	</form:select>
	</td>
	</tr>
	
	<tr>
	<td>Start Date:</td>
	<td><form:input path="startDate" type="date" /></td>
	<td></td>
	<td>End Date:</td>
	<td><form:input path="endDate" type="date" /></td>
	</tr>
	
	<tr>
	<td>
	<input  type="submit" value="Search" class="btn btn-primary"  />
	<!-- <input  type="reset" value="Clear" class="btn btn-danger"  /> -->
	<a href="/" class="btn btn-secondary"  >Clear</a>
	</td>
	</tr>
	
	</table>
	
	</form:form>

<hr>
<table class="table table-striped table-hover">
<thead>
<tr>
<th>Id</th>
<th>Holder Name</th>
<th>Gender</th>
<th>Plan Name</th>
<th>Plan Status</th>
<th>Start Date</th>
<th>End Date</th>
<th>Benefit Amt</th>
</tr>
</thead>
<tbody>
<c:forEach items="${plans}" var="plan" varStatus="index" >
<tr>
<td>${index.count}</td>
<td>${plan.name}</td>
<td>${plan.gender}</td>
<td>${plan.planName}</td>
<td>${plan.planStatus}</td>
<td>${plan.startDate}</td>
<td>${plan.endDate}</td>
<td>${plan.benefitAmount}</td>
</tr>
</c:forEach>
<tr>
<c:if test="${empty plans}">
<td colspan="8" style="text-align: center;"  >
No Record Found
</td>
</c:if>
</tr>
</tbody> 
</table>
<hr>

Export :  <span><a href="/excel" >Excel</a> &nbsp </span> <span><a href="/pdf" >PDF</a></span>


	</div>





</body>
</html>
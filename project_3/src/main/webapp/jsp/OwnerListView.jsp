
<%@page import="in.co.rays.project_3.dto.OwnerDTO"%>
<%@page import="in.co.rays.project_3.controller.OwnerListCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.model.RoleModelInt"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>

<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=ORSView.APP_CONTEXT%>/js/ValidateToInput.js"></script>

<script src="<%=ORSView.APP_CONTEXT%>/js/validateInput.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/utilities.js"></script>

<title>Owner List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js">
	<script type="text/javascript">
	function validateMobileNo(event) {
		const input = event.target;
		input.value = input.value.replace(/[^0-9.]/g, '')
		if (input.value.length > 0 && input.value[0] <= '5') {
			input.value = '';
		}
	}
</script>

<script type="text/javascript">
	function numberLength(input) {
		if (input.value.length > 10) {
			input.value = input.value.slice(0, 10);
		}
	}
</script>

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/1234.jpeg');
	background-size: cover;
	background-repeat: no-repeate;
	padding-top: 6%;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>

<body class="hm">
	<%@include file="Header.jsp"%>
	<%@include file="calendar.jsp"%>
	<div></div>
	<div>
		<form class="pb-5" action="<%=ORSView.OWNER_LIST_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.OwnerDTO"
				scope="request"></jsp:useBean>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List list = ServletUtility.getList(request);

				Iterator<OwnerDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-secondary font-weight-bold pt-3">
					<u>Owner List</u>
				</h1>
			</center>



			<%
				HashMap map = (HashMap) request.getAttribute("vehicleIdd");
					HashMap map1 = (HashMap) request.getAttribute("dobb");
			%>

			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-2">
					<input type="text" name="name" placeholder="Enter Name"
						class="form-control"
						 onkeypress="return ValidateInput(event)" size="25"
						oninput="handleLetterInput(this, 'nameError', 200)"
						onblur="validateLetterInput(this, 'nameError', 200)"
						value="<%=ServletUtility.getParameter("name", request)%>">
					<font color="red" class="pl-sm-5" id="nameError"></font>

				</div>



				<div class="col-sm-2">
					<input type="text" class="form-control" name=insuranceAmount
						placeholder=" Enter InsuranceAmount"
						oninput="handleIntegerInput(this, 'insurenceAmountError', 10)"
						onblur="validateIntegerInput(this, 'insurenceAmountError', 10)"
						value="<%=ServletUtility.getParameter("insuranceAmount", request)%>">
					<font color="red" class="pl-sm-5" id="insurenceAmountError"></font>

				</div>



				<%-- <div class="col-sm-2">
							<input type="text" name="vehicleId" placeholder="Enter vehicleId"
								class="form-control" oninput="validateMobileNo(event)"
								value="<%=ServletUtility.getParameter("vehicleId", request)%>">
						</div>

 --%>
				<div class="col-sm-2"><%=HTMLUtility.getList("vehicleId", String.valueOf(dto.getVehicleId()), map)%></div>

				<%-- <div class="col-sm-2">
					<input type="text" id="datepicker" name="dob"
						class="form-control" placeholder=" Enter Dob"
						readonly="readonly"
						value="<%=ServletUtility.getParameter("dob", request)%>">

				</div> --%>

				<div class="col-sm-2"><%=HTMLUtility.getList("dob", String.valueOf(dto.getDob()), map1)%></div>




				<center>
					<div class="col-sm-2">
						<input type="submit" class="btn btn-primary btn-md"
							style="font-size: 15px" name="operation"
							value="<%=OwnerListCtl.OP_SEARCH%>"> &emsp; <input
							type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
							name="operation" value="<%=OwnerListCtl.OP_RESET%>">
					</div>
				</center>

				<div class="col-sm-2"></div>
			</div>

			</br>
			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table table-bordered table-dark table-hover">
					<thead>
						<tr style="background-color: purple;">

							<th width="10%"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th>
							<th width="5%" class="text">S.NO</th>
							<th width="15%" class="text">Name</th>
							<th width="20%" class="text">insurenceAmount</th>
							<th width="15%" class="text">VehicleId</th>
							<th width="15%" class="text">Dob</th>
							<th width="5%" class="text">Edit</th>
						</tr>
					</thead>
					<%
						while (it.hasNext()) {
								dto = it.next();

							//		RoleDTO rbean = rmodel.findByPK(dto.getRoleId()); 
					%>
					<tbody>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto.getId()%>"></td>
							<td class="text"><%=index++%></td>
							<td class="text"><%=dto.getName()%></td>

							<td class="text"><%=dto.getInsuranceAmount()%></td>
							<td class="text"><%=dto.getVehicleId()%></td>
							<td class="text"><%=DataUtility.getDateString(dto.getDob())%></td>

							<td class="text"><a href="OwnerCtl?id=<%=dto.getId()%>">Edit</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						value="<%=OwnerListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>

					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=OwnerListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=OwnerListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-warning btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=OwnerListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table>

			<%
				}
				if (list.size() == 0) {
			%>
			<center>
				<h1 style="font-size: 40px; color: #162390;">Owner List</h1>
			</center>
			</br>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>




				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div style="padding-left: 48%;">
					<input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=OwnerListCtl.OP_BACK%>">
				</div>

				<div class="col-md-4"></div>
			</div>

			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>


	</div>


</body>
<%@include file="FooterView.jsp"%>
</html>
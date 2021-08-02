<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of TODO's</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="30%">Description</th>
						<th width="20%">Created Date</th>
						<th width="20%">Updated Date</th>
						<th width="10%">Completed</th>
						<th width="10%"></th>
						<th width="10%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.description}</td>
								<td>${todo.createdDate}</td>
									<td>${todo.updateDate}</td>
								<c:choose>
								<c:when test="${todo.completed==true}">
									<td><input type="checkbox" id="iscompleted" checked /></td>
								</c:when>
								<c:otherwise>
									<td><input type="checkbox" id="iscompleted" /></td>
								</c:otherwise>
							</c:choose>
							<td><a type="button" id='btn' class="btn btn-success"
								href="/update-todo?id=${todo.id}&done=" onclick="getValue()">Update</a>
							</td>
							<td><a type="button" class="btn btn-warning"
								href="/delete-todo?id=${todo.id}">Delete</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<script>
				function getValue() {
					
					document.getElementById('btn').href = document
							.getElementById('btn').href
							+ document.getElementById('iscompleted').checked;

				}
			</script>
		</div>
	</div>
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-todo">Add Todo</a>
	</div>

</div>
<%@ include file="common/footer.jspf"%>
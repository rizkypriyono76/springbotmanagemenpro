<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-liburan">Add Liburan</a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of LIBURAN's</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="40%">Description</th>
      <th width="40%">Target Date</th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${liburans}" var="liburan">
      <tr>
       <td>${liburan.description}</td>
       <td><fmt:formatDate value="${liburan.targetDate}"
         pattern="dd/MM/yyyy" /></td>
       <td><a type="button" class="btn btn-success"
        href="/update-liburan?id=${liburan.id}">Update</a>
       <a type="button" class="btn btn-warning"
        href="/delete-liburan?id=${liburan.id}">Delete</a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>
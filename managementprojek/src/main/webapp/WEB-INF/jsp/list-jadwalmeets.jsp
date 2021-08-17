<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-jadwalmeet">Add Jadwalmeet</a>
 </div>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of Meeting or Meet's</h3>
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
     <c:forEach items="${jadwalmeets}" var="jadwalmeet">
      <tr>
       <td>${jadwalmeet.description}</td>
       <td><fmt:formatDate value="${jadwalmeet.targetDate}"
         pattern="dd/MM/yyyy" /></td>
       <td><a type="button" class="btn btn-success"
        href="/update-jadwalmeet?id=${jadwalmeet.id}">Update</a>
       <a type="button" class="btn btn-warning"
        href="/delete-jadwalmeet?id=${jadwalmeet.id}">Delete</a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>
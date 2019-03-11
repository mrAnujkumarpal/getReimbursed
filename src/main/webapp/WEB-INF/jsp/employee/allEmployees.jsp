<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
   <head>
          <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
          <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

</head>
    <body>
    <style>
#AddNewBtn{
background: linear-gradient(45deg, #FF5252 0%, #f48fb1 100%);
box-shadow: 0 6px 20px 0 rgba(244, 143, 177, 0.5);
border-radius: 25px;
}
</style>
 <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <div class="container-fluid">
        <div class="row">
            <div class="col s12 m8 l10"><h5  class="margin medium-small">Our Employee List</h5></div>
            <div class="input-field col s12 m4 l2 right-align">
                <a class="btn" id="AddNewBtn"  href="/empRegistration">Add Employee</a>
            </div>
            </div>
        </div>
        <div class="row">
            <div class="col s12 m12 l12">
                           <table class="striped  responsive-table z-depth-1" id="EmpListTable">
                               <thead>
                                   <tr>
                                       <th>Emp ID</th>
                                       <th>Emp Role</th>
                                       <th>Full Name</th>
                                       <th>Mobile No</th>
                                       <th>Email -Id</th>
                                       <th>Location </th>
                                       <th>Team Lead </th>
                                       <th>Events</th>
                                   </tr>
                               </thead>

                               <tbody style="color:#004d40">
                                   <c:forEach var="emp" items="${allEmployeesList}">
                                       <tr>

                                           <td><i>${emp.org_code}${emp.id}</i></td>
                                           <td>
                                             <c:forEach items="${roleList}" var="role">
                                                <c:if test="${role.id==emp.empRole.id}">${role.empRole}</c:if>
                                             </c:forEach>
                                           </td>
                                           <td>${emp.fName} ${emp.lName}</td>
                                           <td>${emp.mobile}</td>
                                           <td>${emp.email}</td>
                                            <td>
                                                <c:forEach items="${locationList}" var="ll">
                                                    <c:if test="${ll.location_id==emp.location.location_id}">${ll.location_name}</c:if>
                                                </c:forEach>
                                            </td>
                                            <td>
                                                <c:forEach var="empl" items="${allEmployeesList}">
                                                    <c:if test="${empl.id==emp.subimitter_To.id}">
                                                         ${empl.fName} ${empl.lName}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <%--
                                            <td>
                                                <c:forEach var="empl" items="${allEmployeesList}">
                                                    <c:if test="${empl.id==emp.approver_To.id}">
                                                         ${empl.fName} ${empl.lName}
                                                    </c:if>
                                                </c:forEach>
                                             </td>
                                             --%>
                                            <td>
                                                <a  href="/viewEmployeeDetails/${emp.id}">
                                                    <i class="material-icons" style="color:Blue;">visibility</i>
                                                </a>

                                               <a  href="/editEmployeeDetails/${emp.id}">
                                                   <i class="material-icons" style="color:#880e4f">edit</i>
                                               </a>


                                               <a id="deleteEmployee" href="<c:url value="deleteEmployee/${emp.id}"/>">
                                                   <i class="material-icons" style="color:#f50057">delete</i>
                                               </a>
                                           </td>

                                       </tr>
                                   </c:forEach>
                               </tbody>
                           </table>
</div>
            </div>
        </div>


            <!-- Floating Action Button -->
            <div class="fixed-action-btn " style="bottom: 50px; right: 19px; ">
                <a class="btn-floating btn-large z-depth-5">
                    <i class="fa fa-user" aria-hidden="true"></i>
                </a>
                <ul>
                    <li><a href="#" class="btn-floating red z-depth-5"><b>A</b></a></li>
                    <li><a href="#" class="btn-floating yellow darken-1 z-depth-5"><b>N</b></a></li>
                    <li><a href="#" class="btn-floating green z-depth-5"><b>U</b></a></li>
                    <li><a href="#" class="btn-floating blue z-depth-5"><b>J</b></a></li>
                </ul>
            </div>

          <!--Import jQuery before materialize.js-->
             <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
             <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
             <script src="assets/js/main.js" type="text/javascript"></script>
<script>
               $(document).ready(function(){
                 $('select').formSelect();
               });
                $('#EmpListTable').on('click', '#deleteEmployee', deleteEmployee);

                function deleteEmployee(e) {
                    e.preventDefault();

                    if (confirm("Do you really want to delete ? ")) {
                        var url = $(this).attr('href');

                        $.ajax({
                            type: 'get',
                            url: url,
                            success: function (response) {
                                if (response.success === "true") {
                                    console.log(location);
                                    location.reload();
                                } else {
                                    console.log("going in case of success===false");
                                    alert(response.message);
                                }
                            },
                            error: function (response) {

                                alert("Server error encountered ");
                            },
                            complete: function (response) {

                            }
                        });
                    }
                }

</script>
         </body>
     </html>
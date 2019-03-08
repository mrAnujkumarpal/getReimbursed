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
#addNewRoleBtn,#editRoleSubmitBtn{background: linear-gradient(45deg, #8e24aa 0%, #ff6e40 100%);}
</style>
 <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
 <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
               <div class="row">
                     <div class="input-field col s12 m6 l8">
                               <p class="margin medium-small"><h5>Roles in organization</h5></p>
                     </div>
                     <div class="input-field col s12 m6 l4 right-align">
                           <button data-target="rolesModelWindow"  id="AddNewBtn" class="btn modal-trigger  addNewRoleBtn">Add New Role</button>
                     </div>
               </div>
                        <div class="row">
                               <div class="col s12 m12">
                                   <table class="striped  responsive-table  z-depth-1" id="orgRolesList">
                                        <thead>
                                           <tr style="color:#4e342e">
                                               <th>ID</th>
                                               <th>Employee Role</th>
                                               <th>Edit Role</th>
                                               <th>Delete Role</th>
                                           </tr>
                                       </thead>

                                       <tbody style="color:#004d40">
                                           <c:forEach var="rlst" items="${roleList}">
                                               <tr>
                                                   <td>${rlst.id} </td>
                                                   <td> ${rlst.empRole} </td>
                                                   <td>
                                                       <a href="" data-target="rolesModelWindow" data-uri="<c:url value="/common/editRole/${rlst.id}"/>"  class="modal-trigger  editRoleBtn"><i class="material-icons"style="color:#880e4f">edit</i> </a>
                                                   </td>
                                                   <td>
                                                        <a href=""  data-uri="<c:url value="/common/deleteRole/${rlst.id}"/>"  class="modal-trigger  deleteOrgRoleBtn"><i class="material-icons red-text">delete</i> </a>
                                                   </td>
                                               </tr>
                                           </c:forEach>
                                       </tbody>
                                   </table>
                               </div>
                         </div>
         </div>

           <!--Import jQuery before materialize.js-->
                      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                      <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
                      <script src="assets/js/main.js" type="text/javascript"></script>
            <script>
             $(document).ready(function(){
                  $('.modal').modal();
                });

                            $('.editRoleBtn').click(function () {
                                 var url = $(this).data('uri');

                                 $.ajax({
                                     type: 'get',
                                     url: url,
                                     success: function (response) {
                                         if (response.success === "true") {
                                            var id = response.id;
                                            var role = response.role;
                                            $(".editRole").attr("value", id);
                                            $(".editableRoleName").attr("value", role);
                                         } else {
                                             alert(response.message);
                                         }
                                     },
                                     error: function (response) {
                                         alert("server not good.");
                                     },
                                     complete: function (response) {

                                     }
                                 });
                                 $(".modeName").html("Edit Role");
                                 $('#addNewRoleBtn').hide();
                                 $('#editRoleSubmitBtn').show();
                             });

                                $('.addNewRoleBtn').click(function () {
                                     $('.modeName').html("Add New Role");
                                     $(".editRole").attr("value", "");
                                     $(".editableRoleName").attr("value", "");
                                     $('#addNewRoleBtn').show();
                                     $('#editRoleSubmitBtn').hide();
                                 });

            </script>
<!-- Modal Structure -->
  <div id="rolesModelWindow" class="modal">
    <div class="modal-content">
        <div class="col s12">
           <h5 class="modal-title modeName"></h5>
       </div>
      <form id="roleModalForm"  method="post">
                 <div class="input-field col s12">
                    <input name="role_Id"   type="text" class="editRole hide">
                 </div>

                <div class="input-field col s12">
                  <input name="role_Name" placeholder="Role Name"  type="text" class="editableRoleName">

                </div>

         <button id="addNewRoleBtn"     data-uri="<c:url value="/roles"/>" class="btn waves-effect waves-light rolesUrl" type="submit" name="action">  <i class="material-icons right">send</i> Add New Role </button>
         <button id="editRoleSubmitBtn" data-uri="<c:url value="/roles"/>" class="btn waves-effect waves-light rolesUrl" type="submit" name="action">  <i class="material-icons right">send</i> Edit Employee Role </button>
      </form>
    </div>
  </div>
<script>


 $('#rolesModelWindow').on('submit', '#roleModalForm', addEditRoleModalForm);

 function addEditRoleModalForm(e) {
    e.preventDefault();
     var uri = $('#roleModalForm .rolesUrl').data('uri');
     var data = $('#roleModalForm').serialize();
     var editableLocId = $(".editRole").val();


     if (editableLocId === null || editableLocId === '')
          var uri = uri.concat("/add");
         else
          var uri = uri.concat("/update");


          $.ajax({
             type: 'post',
             url: uri,
             data: data,
             success: function (response) {
                 if (response.success === "true") {
                     location.reload();
                 } else {
                     alert(response.message);
                 }
             },
             error: function (response) {
                 alert("Server error encountered");
             },
             complete: function (response) {

             }
          });
 };

$('#orgRolesList').on('click', '.deleteOrgRoleBtn', deleteRoleButton);
function deleteRoleButton(e) {

    if (confirm("Do you really want to delete ? ")) {
        var url = $(this).data('uri');
        $.ajax({
            type: 'get',
            url: url,
            success: function (response) {
                if (response.success === "true") {
                    location.reload();
                } else {
                    alert(response.message);
                }
            },
            error: function (response) {
                alert("Server error encountered");
            },
            complete: function (response) {

            }
        });
    }
}


</script>
    </body>
</html>
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
#addNewExpenseTypeBtn,#editExpenseTypeSubmitBtn{background: linear-gradient(45deg, #8e24aa 0%, #ff6e40 100%);}
</style>
 <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
 <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
               <div class="row">
                     <div class="input-field col s12 m6 l8">
                               <p class="margin medium-small"><h5>Expense Type List </h5></p>
                     </div>
                     <div class="input-field col s12 m6 l4 right-align">
                           <button data-target="ExpenseTypeModelWindow" id="AddNewBtn" class="btn modal-trigger addNewExpenseTypeBtn">New Expense Type</button>
                     </div>
               </div>
                        <div class="row">
                                <div class="col s12 m8   offset-m2">
                                   <table class="striped  responsive-table z-depth-1" id="allExpTypList">
                                       <thead>
                                           <tr style="color:#4e342e">
                                               <th>ID</th>
                                               <th>Expense</th>
                                               <th>Edit/Delete</th>
                                           </tr>
                                       </thead>

                                       <tbody style="color:#004d40">
                                           <c:forEach var="etL" items="${expenseTypeList}">
                                               <tr>
                                                   <td>${etL.expType_Id} </td>
                                                   <td> ${etL.expType_Name} </td>
                                                   <td>
                                                    <a href="" data-target="ExpenseTypeModelWindow" data-uri="<c:url value="/editExpenseType/${etL.expType_Id}"/>"  class="modal-trigger  editExpenceTypeBtn">
                                                        <i class="material-icons" style="color:#880e4f">edit</i>
                                                    </a>

                                                    <a href="" id="deleteExpenseTypeBtn"  class="modal-trigger  deleteExpenseTypeBtn" data-uri="<c:url value="/deleteExpenseType/${etL.expType_Id}"/>">
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

           <!--Import jQuery before materialize.js-->
                      <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                      <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
                      <script src="assets/js/main.js" type="text/javascript"></script>
            <script>

                $(document).ready(function(){
                  $('.modal').modal();
                });

                            $('.editExpenceTypeBtn').click(function () {
                                 var url = $(this).data('uri');

                                 $.ajax({
                                     type: 'get',
                                     url: url,
                                     success: function (response) {
                                         if (response.success === "true") {
                                            var id = response.id;
                                            var name = response.exptype;
                                            $(".editableExpenseTypeId").attr("value", id);
                                            $(".editableExpenseTypeName").attr("value", name);
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

                                 $(".modeName").html("Edit Expense");
                                 $('#addNewExpenseTypeBtn').hide();
                                 $('#editExpenseTypeSubmitBtn').show();
                             });




                                $('.addNewExpenseTypeBtn').click(function () {
                                     $('.modeName').html("Add Expense Type");
                                     $(".editableExpenseTypeId").attr("value", "");
                                     $(".editableLocationName").attr("value", "");
                                     $('#addNewExpenseTypeBtn').show();
                                     $('#editExpenseTypeSubmitBtn').hide();
                                 });

            </script>
<!-- Modal Structure -->
  <div id="ExpenseTypeModelWindow" class="modal">
    <div class="modal-content">
           <h5 class="modal-title modeName"></h5>

      <form id="expenseTypeModalForm"  method="post">
                 <div class="input-field col s12">
                    <input name="expType_Id"   type="text" class="editableExpenseTypeId hide">
                 </div>

                <div class="input-field col s12">
                  <input name="expType_Name" placeholder="Expense Type Name"  type="text" class="editableExpenseTypeName">
                </div>

         <button id="addNewExpenseTypeBtn"     data-uri="<c:url value="/expenseType"/>" class="btn waves-effect waves-light etEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Add New Expense Type </button>
         <button id="editExpenseTypeSubmitBtn" data-uri="<c:url value="/expenseType"/>" class="btn waves-effect waves-light etEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Edit Expense Type</button>
      </form>
    </div>
  </div>
<script>


 $('#ExpenseTypeModelWindow').on('submit', '#expenseTypeModalForm', addEditETModalForm);

 function addEditETModalForm(e) {
    e.preventDefault();
     var uri = $('#expenseTypeModalForm .etEventUrl').data('uri');
     var data = $('#expenseTypeModalForm').serialize();
     var editableETId = $(".editableExpenseTypeId").val();

     if (editableETId === null || editableETId === '')
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



$('#allExpTypList').on('click', '.deleteExpenseTypeBtn', deleteLocButton);
function deleteLocButton(e) {

    if (confirm("Do you really want to delete ? ")) {
        var url = $(this).data('uri');

        $.ajax({
            type: 'post',
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
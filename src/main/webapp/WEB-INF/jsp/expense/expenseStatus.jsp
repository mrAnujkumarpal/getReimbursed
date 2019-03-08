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
#addNewExpenseStatusBtn,#editExpenseStatusSubmitBtn{background: linear-gradient(45deg, #8e24aa 0%, #ff6e40 100%);}
</style>
 <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
 <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
               <div class="row">
                     <div class="input-field col s12 m6 l8">
                               <p class="margin medium-small"><h5>All Expense Status</h5></p>
                     </div>
                     <div class="input-field col s12 m6 l4 right-align">
                           <button data-target="ExpenseStatusModelWindow" id="AddNewBtn"  class="btn modal-trigger addNewExpenseStatusBtn">New Expense Status</button>
                     </div>
               </div>
                        <div class="row">
                                <div class="col s10 m6 l4  offset-s1  offset-m3 offset-l4">
                                   <table class="striped  responsive-table z-depth-1" id="allExpTypList">
                                       <thead>
                                           <tr style="color:#4e342e">
                                               <th>ID</th>
                                               <th></th>
                                               <th>Expense Status</th>
                                                <th>Edit/Delete</th>
                                           </tr>
                                       </thead>

                                       <tbody style="color:#004d40">
                                           <c:forEach var="esL" items="${expenseStatusList}">
                                               <tr>
                                                   <td>${esL.expStatus_Id} </td>
                                                   <td></td>
                                                   <td> ${esL.expStatus_Name} </td>


                                                   <td>
                                                       <a href="" data-target="ExpenseStatusModelWindow" data-uri="<c:url value="/editExpenseType/${esL.expStatus_Id}"/>"  class="modal-trigger  editExpenceTypeBtn"><i class="material-icons" style="color:#880e4f;">edit</i> </a>

                                                        <a href=""  data-uri="<c:url value="/deleteExpenseType/${esL.expStatus_Id}"/>"  class="modal-trigger red-text deleteExpenseTypeBtn"><i class="material-icons">delete</i> </a>
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

                            $('.editExpenseStatusSubmitBtn').click(function () {
                                 var url = $(this).data('uri');

                                 $.ajax({
                                     type: 'get',
                                     url: url,
                                     success: function (response) {
                                         if (response.success === "true") {
                                            var id = response.id;
                                            var name = response.exptype;
                                            $(".editableExpenseStatusId").attr("value", id);
                                            $(".editableExpenseStatusName").attr("value", name);
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

                                 $(".modeName").html("Edit Expense Status");
                                 $('#addNewExpenseStatusBtn').hide();
                                 $('#editExpenseStatusSubmitBtn').show();
                             });




                                $('.addNewExpenseStatusBtn').click(function () {
                                     $('.modeName').html("Add Expense Status");
                                     $(".editableExpenseStatusId").attr("value", "");
                                     $(".editableExpenseStatusName").attr("value", "");
                                     $('#addNewExpenseStatusBtn').show();
                                     $('#editExpenseStatusSubmitBtn').hide();
                                 });

            </script>
<!-- Modal Structure -->
  <div id="ExpenseStatusModelWindow" class="modal">
    <div class="modal-content">
        <div class="col s12">
           <h5 class="modal-title modeName"></h5>
       </div>
      <form id="expenseStatusModalForm"  method="post">
                 <div class="input-field col s12">
                    <input name="expType_Id"   type="text" class="editableExpenseStatusId hide">
                 </div>

                <div class="input-field col s12">
                  <input name="expType_Name" placeholder="Expense Type Name"  type="text" class="editableExpenseStatusName">
                </div>

         <button id="addNewExpenseStatusBtn"     data-uri="<c:url value="/expenseStatus"/>" class="btn waves-effect waves-light esEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Add New Expense Status </button>
         <button id="editExpenseStatusSubmitBtn" data-uri="<c:url value="/expenseStatus"/>" class="btn waves-effect waves-light esEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Edit Expense Status</button>
      </form>
    </div>
  </div>
<script>


 $('#ExpenseStatusModelWindow').on('submit', '#expenseStatusModalForm', addEditESModalForm);

 function addEditESModalForm(e) {
    e.preventDefault();
     var uri = $('#expenseStatusModalForm .esEventUrl').data('uri');
     var data = $('#expenseStatusModalForm').serialize();
     var editableESId = $(".editableExpenseStatusId").val();

     if (editableESId === null || editableESId === '')
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


</script>
    </body>
</html>
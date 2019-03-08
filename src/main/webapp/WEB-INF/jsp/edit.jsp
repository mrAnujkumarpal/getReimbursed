<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head><title>SpringBoot</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
</head>
<body>
<hr/>
<form method="post" action="/save">
  <input type="hidden" name="id" value="${lists.id}"/><br/>
  First name:<br>
  <input type="text" name="firstname" value="${lists.firstName}"/>
  <br>
  Last name:<br>
  <input type="text" name="lastname" value="${lists.lastName}">
  <br><br>
  <input type="submit" value="Submit">
</form>

                                     <div class="col s12 z-depth-1"  id="addVendorDiv" style="display:none; margin-top: 10px; ">
                                         <div class="form-group">
                                             <label class="control-label "  style="font-size:11px;margin-left:0px; font-style:italic;color:00888A;" >You selected others option in vendors name so you have to fill the following details:</label>
                                             <div class="col s6">
                                                 <input type="text" name="vendor.vendor_name"   class="form-control" placeholder="Enter the vendor name" />
                                             </div>
                                             <div class="col s6">
                                                 <input type="text" name="vendor.vendor_phno"  class="form-control" placeholder="Enter the vendor Phone" />
                                             </div>
                                             <div class="col s6">
                                                 <input type="text" name="vendor.vendor_address"   class="form-control" placeholder="Enter the vendor Address" />
                                             </div>
                                             <div class="col s6">
                                                  <select class="browser-default" name="vendor.location" >
                                                            <c:forEach items="${locationList}" var="ll">
                                                                  <option value="${ll.location_id}"> ${ll.location_name}</option>
                                                             </c:forEach>
                                                  </select>
                                             </div>
                                         </div>
                                     </div>



       <button   data-uri="<c:url value="/submitExpense"/>"  class="btn modal-trigger  changeStatusExpenseBtn"><i class="material-icons" style="color:purple;">trending_up</i> </button>


                                                   <td>
                                                       <button data-target="ExpenseTypeModelWindow" data-uri="<c:url value="/editExpenseType/${etL.expType_Id}"/>"  class="btn   red modal-trigger  editExpenceTypeBtn"><i class="material-icons">edit</i> </button>
                                                   </td>



</body>
</html>
https://www.cleverstudentlets.com/wp-content/uploads/2015/11/nobill-clear.png

<html>
    <head>
    </head>
    <body>
        <div style="font-family:Verdana; background-color: #eeeeee; padding:20px;">

            <div style="background-color:#fff;padding:15px;text-align:center;">
               <span style="font-size:20px; padding:10px 0px;">Hi Anuj Kumar Pal,</span>
                <h3 style="color:gray;"> Expense #00531</h3>
            </div>

            <div style="background-color:#fff;padding:10px;margin:2px 0px;font-size:12px;">
                You created <b>Expense #00531</b> with name <b>My expense name</b> at <i>12/12/2018</i> <br><br>
                Your amount <b>Rs. 354.00</b>
                <h5>Please submit this expense to get reimburshed in next reimburshment cycle.</h5>

            </div>
            <div style="background-color:#fff;padding:15px; border-top:1px solid #eeeeee;">
                <h6>Wish you all the best</h6>
                <h5>Thanks & Regards<br/>IT Logic Lab</h5>
                <img src="http://www.dreamzcard.com/new/assets/img/hot/itlogiclab.png" style="max-height:40px;">
            </div>
            <div style="padding:10px;text-align:center;">
                <small style="font-size:10px;" >© 2018 ItlogicLab(P)Ltd.India, <br> All rights recived.</small>
            </div>
        </div>
    </body>
</html>
<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <title>Particular Expense | EMS</title> 

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
    <script src="assets/js/main.js" type="text/javascript"></script>
    <style type="text/css"> body { background-color:#f1f3e9;} #rejectBtn{background-color:#fff; border:1px solid #757575; color:#757575; border-radius:7px;} </style>
    <body>

        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <div class="container-fluid">
            <c:choose>
                <c:when test="${aboutExpense ne null && not empty aboutExpense}">
                    <div class="row">
                        <div class="col s12 m12 l10  offset-l1">
                            <div class="row card">
                                <div class="col s12 m12 l12  z-depth-1">
                                    <h5>View  Expense :  &nbsp; #00-${aboutExpense.exp_id}
                                        <small class="right-align">  </small>
                                    </h5>
                                </div>
                                <div class="col s12 m6 l6 z-depth-2">
                                    <div class="" style="padding:10px 0px; margin:10px 0px;">
                                        <div class="row"><div class="col l3  m4 s5"><strong> Expense Date     </strong></div><div class="col l9  m8 s7">   <fmt:formatDate   dateStyle = "long"  value = "${aboutExpense.exp_Date}"/> </div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong> Expense Name     </strong></div><div class="col l9  m8 s7">   ${aboutExpense.exp_name}  </div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong> Created By        </strong></div><div class="col l9  m8 s7">  ${aboutExpense.exp_createdBy}<br>  </div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong> Vendor            </strong></div><div class="col l9  m8 s7">   ${aboutExpense.vendor.vendor_name}</div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Amount          </strong></div><div class="col l9  m8 s7">   <strong class="purple-text"><i class="fa fa-inr"></i> <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${aboutExpense.exp_amount}"/> </strong></div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Type            </strong></div><div class="col l9  m8 s7">   ${aboutExpense.expenseType.expType_Name}  </div></div>

                                        <c:choose>
                                            <c:when test="${aboutExpense.expenseStatus.expStatus_Id == 6}">

                                                <div class="row"><div class="col l3  m4 s5"><strong>  Status  </strong></div><div class="col l9  m8 s7"><span  class="red-text">   ${aboutExpense.expenseStatus.expStatus_Name} </span>  </div></div>
                                                <div class="row">
                                                    <div class="col l12  m12 s12">
                                                        <table style="border:1px solid #e6e7e9; font-size:11px;">
                                                            <thead  style="background-color:#f1f3e9;">
                                                                <tr>
                                                                    <th>Reject Date</th>
                                                                    <th>Reject By</th>
                                                                    <th>Reject Reason</th>
                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                                <c:if test="${rejextExpDetails ne null && not empty rejextExpDetails}">
                                                                    <c:forEach items="${rejextExpDetails}" var="erD">
                                                                        <tr>
                                                                            <td><fmt:formatDate dateStyle = "long"  value = "${erD.exp_reject_Date}"/></td>
                                                                            <td>${erD.exp_reject_By}</td>
                                                                            <td>${erD.exp_reject_reason}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </c:if>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="row">
                                                    <div class="col l3  m4 s5"><strong>  Status          </strong></div>
                                                    <div class="col l9  m8 s7">
                                                        ${aboutExpense.expenseStatus.expStatus_Name}
                                                        <%--
                                                        <div class="hidden">
                                                                                                                    <table style="font-size:11px;">
                                                                                                                    <thead> <tr><th>Approved By</th> <th>Auditted By</th></tr> </thead>
                                                                                                                        <tbody>
                                                                                                                            <tr>
                                                                                                                            <td> ${aboutExpense.getEmployee().getSubimitter_To().getfName()}
                                                                                                                                 ${aboutExpense.getEmployee().getSubimitter_To().getlName()}</td>
                                                                                                                                <td> ${aboutExpense.getEmployee().getApprover_To().getfName()}
                                                                                                                                 ${aboutExpense.getEmployee().getApprover_To().getlName()} </td>

                                                                    </tr>
                                                                </tbody>
                                                            </table>
</div>--%>


                                                    </div>
                                                </div>

                                            </c:otherwise>
                                        </c:choose>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Location        </strong></div><div class="col l9  m8 s7">   ${aboutExpense.location.location_name} </div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Pay By        </strong></div><div class="col l9  m8 s7">   ${aboutExpense.paymentMode.pay_type} </div></div>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Bills        </strong></div>
                                            <div class="col l9  m8 s7">
                                                <c:choose>
                                                    <c:when test="${aboutExpense.billable eq true}">Yes </c:when>
                                                    <c:otherwise>Not</c:otherwise>
                                                </c:choose>Attached.
                                            </div>
                                        </div>
                                        <div class="row"><div class="col l3  m4 s5"><strong>  Description        </strong></div><div class="col l9  m8 s7">   ${aboutExpense.exp_description} </div></div>
                                    </div>


                                    <c:if test="${rejextExpDetails eq null &&  empty rejextExpDetails}">

                                        <div class="row"   Style="padding:20px 0px; border-top:1px solid #f1f3e9;">
                                            <div class="col s12 l12  m12">
                                                <div class="right-align">
                                                    <c:if test="${aboutExpense.expenseStatus.expStatus_Id != 1}">
                                                        <a href="" data-target="ExpenseTypeModelWindow"  style="background: linear-gradient(to right, #FF5252 0%, #EB7077 50%, #f48fb1 100%); margin: 10px 0px;"  data-uri="<c:url value="/rejectExpense/${aboutExpense.exp_id}"/>"  class="btn-large modal-trigger  editExpenceTypeBtn">
                                                            <b>Reject</b>
                                                        </a>

                                                    </c:if>
                                                    <a href="/submitPerticularExpense/${aboutExpense.exp_id}" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn-large waves-effect waves-light"><b>Approve  ${nextAction}</b> </a>
                                                </div>
                                            </div>

                                            <div class="col s12 l12  m12">
                                                <div class="" id="rejectReasonForm" style="display:none">
                                                    <form  method="GET" action="RejectExpenseDone">
                                                        <div class="row">
                                                            <input  name="expense_id" type="hidden" value="${aboutExpense.exp_id}">

                                                            <div class="input-field col s12">
                                                                <textarea id="textarea" name="reject_reason" class="materialize-textarea" data-length="120"  value=""></textarea>
                                                                <label for="textarea">Kindly mention reject reason </label>

                                                                <button href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);" class="btn waves-effect waves-light col s4">Rejected <i class="material-icons right">send</i></button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>

                                    </c:if>

                                </div>

                                <div class="col s12 m6 l6 z-depth-1">
                                    <div class="row" style="padding:10px 1px;">
                                        <c:choose>
                                            <c:when test="${expAttachedBills ne null && not empty expAttachedBills}">
                                                <c:forEach items="${expAttachedBills}" var="eaB">
                                                    <div class="col s12 m6 l6">
                                                        <img class="responsive-img" src="data:image/jpeg;base64,${eaB.base64}">-
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col s12 m12 l12 center-align " id="panelBck">
                                                    <div class="center-align">
                                                        <img class="responsive-img center-align "  src="">
                                                    </div>
                                                    <h6>OOps, You don not Attached any bills with this expense.</h5>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <div class="col s12 m8 l6  offset-m2  offset-l3  z-depth-5 card-panel center-align " id="panelBck">
                            <div class="center-align">
                                <img class="responsive-img center-align "   src="http://insidetimeshare.com/wp-content/uploads/2018/03/not_available.jpg">
                            </div>
                            <h6>OOps, You are on wrong track.</h5>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Modal Structure -->
        <div id="ExpenseTypeModelWindow" class="modal">
            <div class="modal-content">
                <form  method="post" id="expenseTypeModalForm" >

                    <input  name="expense_id" type="text" class="expenseId hide" value="${aboutExpense.exp_id}">
                    <div class="input-field col s12">
                        <textarea id="textarea" name="reject_reason" class="materialize-textarea" data-length="120"  value=""></textarea>
                        <label for="textarea">Kindly mention reject reason </label>

                        <button  data-uri="<c:url value="/RejectExpenseDone"/>" href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);" class="btn waves-effect waves-light col s4 exRejectUrl">Rejected <i class="material-icons right">send</i></button>
                    </div>

                </form>
            </div>
        </div>


        <!--Import jQuery before materialize.js-->



        <script type="text/javascript">

            $(document).ready(function () {
                $('.modal').modal();
            });


            $(document).ready(function () {
                $('.dropdown-trigger').dropdown();
                $('select').formSelect();

            });

            $('.editExpenceTypeBtn').click(function () {
                var url = $(this).data('uri');
                $.ajax({
                    type: 'get',
                    url: url,
                    success: function (response) {
                        if (response.success === "true") {
                            var id = response.id;
                            $(".editableExpenseTypeId").attr("value", id);
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

            });



            $('#ExpenseTypeModelWindow').on('submit', '#expenseTypeModalForm', addEditETModalForm);

            function addEditETModalForm(e) {
                e.preventDefault();
                var uri = $('#expenseTypeModalForm .exRejectUrl').data('uri');
                var data = $('#expenseTypeModalForm').serialize();

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
            }
            ;

        </script>



    </body>
</html>
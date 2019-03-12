<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
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
            #rejectExpSubmitBtn{background: linear-gradient(45deg, #8e24aa 0%, #ff6e40 100%);}
        </style>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
         <c:choose>
          <c:when test="${expenses ne null && not empty expenses}">
            <div class="row">
                <div class="col s12 m12 l12"><h5 class="left-align">Expenses ${reportName}</h5></div>
            </div>
            <div class="row">
                <div class="col s12 m12">
                    <table class="striped  responsive-table z-depth-1" id="allLocationList">
                        <thead>
                            <tr style="color:#fff; background-color:00888A;">
                                <th>
                                    <input type="checkbox"id="allCheckBxCK"   value="all" class="selectAllExpense">
                                    <label for="allCheckBxCK"></label>
                                </th>
                                <th>Expense Name</th>
                                <th>Expense Date</th>
                                <th>Amount</th>
                                <th>Expense Type</th>
                                <th>Location</th>
                                <th>Payment Mode</th>
                                <th>Expense Status</th>
                                <th>Bills</th>
                                <th>Actions</th>
                            </tr>
                        </thead>

                        <tbody style="color:#004d40">
                            <c:forEach items="${expenses}" var="exp">
                                <tr>
                                    <td>
                                        <input type="checkbox" class="checkbox" value="${exp.exp_id}" lass="selectExpense"   name="selectedExpense"/>
                                        <label for="selectedExpense"></label>
                                    </td>
                                    <td>${exp.exp_name}</td>
                                    <td><fmt:formatDate type = "date"  value = "${exp.exp_Date}" /></td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${exp.exp_amount}" /></td>
                                    <td>
                                        <c:forEach items="${expenseTypeList}" var="et">
                                            <c:if test="${et.expType_Id==exp.expenseType.expType_Id}">${et.expType_Name}</c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach items="${locationList}" var="ll">
                                            <c:if test="${ll.location_id==exp.location.location_id}">${ll.location_name}</c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                  ${exp.paymentMode.pay_type}
                                    </td>
                                    <td>${reportName}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${exp.billable eq true}">Yes </c:when>
                                            <c:otherwise> Not</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/viewPerticularExpense/${exp.exp_id}">
                                            <i class="material-icons" style="color:Blue;">visibility</i>
                                        </a>
                                        <a href="" data-target="rejectResonModelWindow" data-uri="<c:url value="/expense/findExpenseForReject/${exp.exp_id}"/>"  class="modal-trigger  rejetcExpBtn">
                                          <i class="material-icons" style="color:red;">reply</i>
                                        </a>
                                        <a  href="/submitPerticularExpense/${exp.exp_id}">
                                            <i class="material-icons" style="color:purple;">trending_up</i>
                                        </a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
          </c:when>
                        <c:otherwise>
                            <div class="row">
                                <div class="col s10 m8 l6 offset-s1 offset-l3  z-depth-5 card-panel center-align " id="panelBck">
                                    <div class="center-align">
                                        <img class="responsive-img center-align "  src="http://insidetimeshare.com/wp-content/uploads/2018/03/not_available.jpg">
                                    </div>
                                    <h6>OOps, You Don't have any ${reportName} expense history.</h5>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
        <script src="assets/js/main.js" type="text/javascript"></script>
        <script>

            $(document).ready(function () {
                $('.modal').modal();
            });

            $('.rejetcExpBtn').click(function () {
                var url = $(this).data('uri');
                $.ajax({
                    type: 'get',
                    url: url,
                    success: function (response) {
                        if (response.success === "true") {
                            var id = response.id;
                            var name = response.name;
                            $(".rejectableExpId").attr("value", id);

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
        </script>
        <!-- Modal Structure -->
        <div id="rejectResonModelWindow" class="modal">
            <div class="modal-content">
                <form id="rejectReasonModalForm"  method="post">
                    <div class="input-field col s12">
                        <input name="expense_id"   type="text" class="rejectableExpId hide">

                        <input name="reject_reason"  type="text">
                        <label for="reject_reason">Kindly mention reject reason</label>

                        <button id="rejectExpSubmitBtn" data-uri="<c:url value="/RejectExpenseDone"/>" class="btn waves-effect waves-light rejectExpURL" type="submit" name="action">  <i class="material-icons right">send</i> Reject  </button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            $('#rejectResonModelWindow').on('submit', '#rejectReasonModalForm', rejectExpenseDone);
            function rejectExpenseDone(e) {
                e.preventDefault();
                var uri = $('#rejectReasonModalForm .rejectExpURL').data('uri');
                var data = $('#rejectReasonModalForm').serialize();


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
<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

    </head>
    <body>




        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>

        <div class="container-fluid">
            <c:choose>
                <c:when test="${expenses ne null && not empty expenses}">
                    <div class="row">
                        <div class="col s12 m12 l12"><h5 class="left-align">Expenses ${reportName}</h5></div>
                    </div>
                    <div class="row">
                        <div class="col s12 m12 l12">
                            <table class="striped  responsive-table z-depth-1" id="EmpListTable">
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
                                        <th>Event</th>
                                    </tr>
                                </thead>

                                <c:if test="${expenses ne null && not empty expenses}">
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
                                            <c:choose>
                                                <c:when test="${exp.exp_paymentMode == '10'}">Cash Payment</c:when>
                                                <c:when test="${exp.exp_paymentMode == '20'}">Debit Card</c:when>
                                                <c:when test="${exp.exp_paymentMode == '30'}">Cradit Card</c:when>
                                                <c:when test="${exp.exp_paymentMode == '40'}">Net Banking</c:when>
                                            </c:choose>
                                        </td>
                                        <td>${reportName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${exp.billable eq true}">Yes </c:when>
                                                <c:otherwise> Not</c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a  href="/viewPerticularExpense/${exp.exp_id}">
                                                <i class="material-icons" style="color:Blue;">visibility</i>
                                            </a>
                                             <a  href="/submitPerticularExpense/${exp.exp_id}">
                                                <i class="material-icons" style="color:purple;">trending_up</i>
                                             </a>
                                            <c:choose>
                                                <c:when test = "${exp.expenseStatus.expStatus_Id == 1}">
                                                    <a  href="#">
                                                        <i class="material-icons" style="color:#880e4f">edit</i>
                                                    </a>
                                                </c:when>
                                            </c:choose>
                                        </td>
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
                        <div class="col s10 m8 l6 offset-s1 offset-m2  offset-l3  z-depth-5 card-panel center-align " id="panelBck">
                            <div class="center-align">
                                <img class="responsive-img center-align "  src="http://shikaenglish.com/images/not_found.png">
                            </div>
                            <h6>OOps, You Don't have any ${reportName} expense history.</h5>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
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
            $(document).ready(function () {
                $('select').formSelect();
            });



        </script>
    </body>
</html>
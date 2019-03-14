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
        body{
        background-color:#f5f5f5 ;
        }
        </style>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="col s12 m4 l3">
                    <div class="card" >
                        <div class="card-content">
                            <form  action="/activityMonitoring"  method="post">
                                <select name="performedActivity" class="browser-default"Style="border:none; border-bottom:1px solid #111111;" >
                                    <option value="3">Approved by me</option>
                                    <option value="4">Audit by me</option>
                                    <option value="5">Reimbursed by me </option>
                                </select>
                                <br/>
                                <h6 class="green-text">From Date </h6>
                                <input type="date" name="to_date" class="datepicker" value="">
                                <h6 class="green-text">To Date</h6>
                                <input type="date" name="from_date" class="datepicker" value="">

                                <button href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn waves-effect waves-light col s12">Show me</button>

                                <%--
                                <button href="#"  data-target="monitorActivitySubmitForm" data-uri="<c:url value="/activityMonitoring"/>"
                                class="btn btn-block col s12 waves-effect waves-light ckActivityURL"
                                style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);" >View</button>
                                --%>
                            </form>
                        </div>
                        <br/>
                    </div>
                </div>
                <div class="col s12 m8 l9">
                    <div class="card" >
                        <div class="card-content">
                            <c:choose>
                                <c:when test="${expenses ne null && not empty expenses}">
                                    <table class="striped  responsive-table">
                                        <thead>
                                            <tr style="color:#fff; background-color:00888A;">
                                                <th>Expense No. </th>
                                                <th>Expense Date</th>
                                                <th>Created By</th>
                                                <th>Expense Name</th>
                                                <th>Amount</th>
                                                <th>Expense Type</th>
                                                <th>Availed Bill</th>
                                            </tr>
                                        </thead>

                                        <tbody style="color:#004d40">
                                            <c:forEach items="${expenses}" var="exp">
                                                <tr>
                                                    <td>#00-${exp.exp_id}</td>
                                                    <td><fmt:formatDate type = "date"  value = "${exp.exp_Date}" /></td>
                                                    <td>${exp.exp_createdBy}</td>
                                                    <td>${exp.exp_name}</td>
                                                    <td><fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${exp.exp_amount}" /></td>
                                                    <td>
                                                        <c:forEach items="${expenseTypeList}" var="et">
                                                            <c:if test="${et.expType_Id==exp.expenseType.expType_Id}">${et.expType_Name}</c:if>
                                                        </c:forEach>
                                                    </td>

                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${exp.billable eq true}">Yes </c:when>
                                                            <c:otherwise> Not</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div class="right-align">
                                    <a href="/getXLSXFile/${ckActivity}/${from_date}/${to_date}"
                                    style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"
                                    class="btn-large waves-effect waves-light">
                                             <b>Download as xls</b> </a>

                                    <a href="/getPDFFile/${ckActivity}/${from_date}/${to_date}"    style="background: linear-gradient(to right, #FF5252 0%, #EB7077 50%, #f48fb1 100%); margin: 10px 0px;"  class="btn-large modal-trigger  editExpenceTypeBtn">
                                            <b>Download as PDF</b></a>
                                    </div>


                                </c:when>
                                <c:otherwise>

                                    <div class="center-align">
                                        <img class="responsive-img center-align "  src="http://insidetimeshare.com/wp-content/uploads/2018/03/not_available.jpg">
                                    </div>
                                    <h6>OOps, You Don't have any ${reportName} expense history.</h5>

                                    </c:otherwise>
                                </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
        <script src="assets/js/main.js" type="text/javascript"></script>
        <script>

            $(document).ready(function () {
                $('.modal').modal();
            });


            $('#downloadXlxs').on('submit', '#monitorActivityForm', monitorActivity);

            function monitorActivity(e) {
                e.preventDefault();

                var uri = $('#monitorActivityForm .ckActivityURL').data('uri');
                var data = $('#monitorActivityForm').serialize();

                alert(uri + " -" + data);
                $.ajax({
                    type: 'post',
                    url: uri,
                    data: data,
                    success: function (response) {

                        alert("come back here. ");

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
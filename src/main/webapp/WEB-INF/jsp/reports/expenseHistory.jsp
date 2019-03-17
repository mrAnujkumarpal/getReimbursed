<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
    <head><title>Expense History | EMS</title></head>
    <style>
        .custom-checkbox {
            padding: 0 3rem;
            display: inline-block;
        }
        #submitControlBtn{
            background-color:#fff;
            color:#ff3366;
            border:1px solid #ff3366;
        }
        #submitControlBtn:hover{
            background-color:#ff3366;
            color:#fff;
            font-weight:bold;
        }
        body { background-color:#fafafa;}
    </style>

    <body>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <div class="container-fluid">
            <c:choose>
                <c:when test="${expenses ne null && not empty expenses}">
                    <div class="row">
                        <div class="input-field col s12 m8 l10">
                            <h5 class="margin medium-small">All ${reportName} Expenses</h5></p>
                        </div>
                        <div class="input-field col s12 m4 l2 right-align">
                            <button class="btn waves-light" id="submitControlBtn" >Submit</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col s12 m12 l12">
                            <table class="striped responsive-table z-depth-1"  id="myExpenseListTable">
                                <thead>
                                    <tr style="color:#fff; background-color:00888A;">
                                        <th><input type="checkbox"  value="all" class="selectAllExpense"></th>
                                        <th>Expense ID</th>
                                        <th>Expense Name</th>
                                        <th>Expense Date<br/> <small><i> (yyyy-dd-mm) </i></small> </th>
                                        <th>Amount</th>
                                        <th>Expense Type</th>
                                        <th>Expense<br>Location</th>
                                        <th>Payment Mode</th>
                                        <th>Expense<br>Status</th>
                                        <th>Bills</th>
                                        <th>Action
                                            <c:if test = "${reportName == 'Created'}"></c:if>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${expenses ne null && not empty expenses}">
                                        <c:forEach items="${expenses}" var="exp">
                                            <tr>
                                                <td><input type="checkbox" name="selectedExpense" value="${exp.exp_id}" class="selectExpense"></td>
                                                <td>#00-${exp.exp_id}</td>
                                                <td>${exp.exp_name}</td>
                                                <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${exp.exp_Date}"/></td>
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
                                                    <c:forEach items="${payModeList}" var="pML">
                                                        <c:if test="${pML.pay_Id == exp.paymentMode.pay_Id}">${pML.pay_type}</c:if>
                                                    </c:forEach>
                                                </td>
                                                <td>${exp.expenseStatus.expStatus_Name}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${exp.billable eq true}"> Yes  </c:when>
                                                        <c:otherwise>Not</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a  href="/viewPerticularExpense/${exp.exp_id}">
                                                        <i class="material-icons" style="color:Blue;">visibility</i>
                                                    </a>
                                                    <c:choose>
                                                        <c:when test = "${exp.expenseStatus.expStatus_Id == 1}">
                                                            <a  href="/editExpenseDetails/${exp.exp_id}">
                                                                <i class="material-icons" style="color:#880e4f;">edit</i>
                                                            </a>
                                                            <a id="deleteExpense" href="">
                                                                <i class="material-icons" style="color:#f50057;">delete</i>
                                                            </a>
                                                        </c:when>
                                                    </c:choose>


                                                    <c:if test = "${reportName == 'Created'}">
                                                        <a href=""   id="approveMyExpenseBtn" data-uri="<c:url value="/expense/approved/${exp.exp_id}"/>">
                                                            <i class="material-icons" style="color:purple;">trending_up</i></i>
                                                        </a>
                                                    </c:if>
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

        <!-- Modal Structure -->
        <div id="locationModelWindow" class="modal">
            <div class="modal-content">
                <h4>Modal Header</h4>
                <p>A bunch of text</p>
            </div>
            <div class="modal-footer">
                <a href="#!" class="modal-close waves-effect waves-green btn-flat">Agree</a>
            </div>
        </div>



        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.min.js"></script>
        <script src="assets/js/main.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $(".button-collapse").sideNav();
                $('select').formSelect();
                $('.dropdown-trigger').dropdown();


            });
            $('.dropdown-trigger').dropdown();



            $('#myExpenseListTable').on('click', '#approveMyExpenseBtn', approveMyExpNow);

            function approveMyExpNow(e) {

                e.preventDefault();
                var url = $(this).data('uri');

                Swal.fire({
                    title: 'Are you sure ?',
                    text: "You are approving this expense by your hand !",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#4CAF50',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, Approved it!'
                }).then((result) => {
                    if (result.value) {
                        $.ajax({
                            type: 'post',
                            url: url,
                            contentType: "application/json; charset=utf-8",
                            success: function (response) {
                                if (response.success === "true") {
                                    swal.fire({
                                        text: 'Congratulation approved successfully !!',
                                        type: 'success',
                                        icon: 'success'
                                    }).then(function () {
                                        location.reload();

                                    });

                                } else {
                                    swal("Oops", response.message, "error");
                                    // alert(response.message);
                                }
                            },
                            error: function (response) {
                                swal("Oops", "We couldn't connect to the server!", "error");
                            },
                            complete: function (response) {

                            }
                        });
                    }
                });

            }
            ;


        </script>
    </body>
</html>
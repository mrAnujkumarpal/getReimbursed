<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head><title>Vendors | EMS</title></head>
    <body>

        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="input-field col s12 m6 l8">
                    <p class="margin medium-small"><h5>All Vendors </h5></p>
                </div>
            </div>
            <div class="row">
                <div class="col s12 m12">
                    <table class="striped  responsive-table z-depth-1" id="vendorsListTable">
                        <thead>
                            <tr  style="color:#fff; background-color:#00888A;">
                                <th>ID</th>
                                <th>Vendor Name</th>
                                <th>Vendor Address</th>
                                <th>Vendor Location</th>
                                <th>Vendor Phone</th>
                                <th>Register By</th>
                                <th>Event</th>
                            </tr>
                        </thead>
                        <tbody style="color:#004d40">
                            <c:forEach var="aVL" items="${allVendorsList}">
                                <tr>
                                    <td>${aVL.vendor_Id}</td>
                                    <td>${aVL.vendor_name}</td>
                                    <td>${aVL.vendor_address}</td>
                                    <td>
                                        <c:forEach items="${locationList}" var="ll">
                                            <c:if test="${ll.location_id==aVL.location.location_id}">${ll.location_name}</c:if>
                                        </c:forEach>
                                    </td>
                                    <td>${aVL.vendor_phno}</td>
                                    <td>${aVL.created_By}</td>
                                    <td>

                                        <a  href="/editVendorDetails/${aVL.vendor_Id}">
                                            <i class="material-icons" style="color:#880e4f">edit</i>
                                        </a>


                                        <a id="deleteVendor" href="<c:url value="/deleteVendor/${aVL.vendor_Id}"/>">
                                            <i class="material-icons" style="color:#f50057">delete</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>



                    <!--Import jQuery before materialize.js-->

                    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
                    <script src="assets/js/main.js" type="text/javascript"></script>
                    <script type="text/javascript">
                        $(".button-collapse").sideNav();
                    </script>
                    <script>
                        $(document).ready(function () {
                            $('select').formSelect();
                        });
                        $('.dropdown-trigger').dropdown();
                        $('#vendorsListTable').on('click', '#deleteVendor', deleteVendor);
                        function deleteVendor(e) {
                            e.preventDefault();
                            var url = $(this).attr('href');
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
                                        type: 'get',
                                        url: url,
                                        success: function (response) {
                                            if (response.success === "true") {
                                                swal.fire({
                                                    text: 'Congratulation vendor disable successfully !!',
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
                                            alert("Server error encountered ");
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
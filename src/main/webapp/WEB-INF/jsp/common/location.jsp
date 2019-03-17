<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head><title>Location | EMS</title></head>
    <body>
        <style>
            #AddNewBtn{
                background: linear-gradient(45deg, #FF5252 0%, #f48fb1 100%);
                box-shadow: 0 6px 20px 0 rgba(244, 143, 177, 0.5);
                border-radius: 25px;
            }
            #editLocationSubmitBtn,#addNewLocationBtn{background: linear-gradient(45deg, #8e24aa 0%, #ff6e40 100%);}
        </style>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="input-field col s12 m6 l8">
                    <p class="margin medium-small"><h5>Our Location List </h5></p>
                </div>
                <div class="input-field col s12 m6 l4 right-align">
                    <button data-target="locationModelWindow" id="AddNewBtn" class="btn modal-trigger addNewLocationBtn">Add New Location</button>
                </div>
            </div>
            <div class="row">
                <div class="col s12 m8   offset-m2">
                    <table class="striped  responsive-table z-depth-1" id="allLocationList">
                        <thead>
                            <tr style="color:#4e342e">
                                <th>ID</th>
                                <th>Location </th>
                                <th>Edit/Delete</th>
                            </tr>
                        </thead>

                        <tbody style="color:#004d40">
                            <c:forEach var="all" items="${locationList}">
                                <tr>
                                    <td>${all.location_id} </td>
                                    <td> ${all.location_name} </td>
                                    <td>
                                        <a href="" data-target="locationModelWindow" data-uri="<c:url value="/common/editLocation/${all.location_id}"/>"  class="modal-trigger editLocationBtn"><i class="material-icons"style="color:#880e4f;">edit</i> </a>

                                        <a href=""  data-uri="<c:url value="/common/deleteLocation/${all.location_id}"/>"  class="modal-trigger red-text  deleteLocationBtn"><i class="material-icons">delete</i> </a>
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

            $(document).ready(function () {
                $('.modal').modal();
            });

            $('.editLocationBtn').click(function () {
                var url = $(this).data('uri');

                $.ajax({
                    type: 'get',
                    url: url,
                    success: function (response) {
                        if (response.success === "true") {
                            var id = response.id;
                            var name = response.name;
                            $(".editableLocationId").attr("value", id);
                            $(".editableLocationName").attr("value", name);
                        } else {
                            alert(response.message);
                        }
                    },
                    error: function (response) {
                        swal("Oops", "server not good.", "error");
                        // alert("server not good.");
                    },
                    complete: function (response) {
                    }
                });

                $(".modeName").html("Edit Location");
                $('#addNewLocationBtn').hide();
                $('#editLocationSubmitBtn').show();


            });




            $('.addNewLocationBtn').click(function () {
                $('.modeName').html("Add New Location");
                $(".editableLocationId").attr("value", "");
                $(".editableLocationName").attr("value", "");
                $('#addNewLocationBtn').show();
                $('#editLocationSubmitBtn').hide();
            });

        </script>
        <!-- Modal Structure -->
        <div id="locationModelWindow" class="modal">
            <div class="modal-content">
                <div class="col s12">
                    <h5 class="modal-title modeName"></h5>
                </div>
                <form id="locationModalForm"  method="post">
                    <div class="input-field col s12">
                        <input name="location_Id"   type="text" class="editableLocationId hide">

                    </div>

                    <div class="input-field col s12">
                        <input name="location_name" placeholder="Location Name"  type="text" class="editableLocationName">

                    </div>

                    <button id="addNewLocationBtn"   data-uri="<c:url value="/locationEvent"/>" class="btn waves-effect waves-light locEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Add New Location </button>
                    <button id="editLocationSubmitBtn" data-uri="<c:url value="/locationEvent"/>" class="btn waves-effect waves-light locEventUrl" type="submit" name="action">  <i class="material-icons right">send</i> Edit Location </button>
                </form>
            </div>
        </div>

        <script>
            $('#locationModelWindow').on('submit', '#locationModalForm', addEditLocationModalForm);

            function addEditLocationModalForm(e) {
                e.preventDefault();
                var uri = $('#locationModalForm .locEventUrl').data('uri');
                var data = $('#locationModalForm').serialize();
                var editableLocId = $(".editableLocationId").val();

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
                            swal("Oops", response.message, "error");
                            // alert(response.message);
                        }
                    },
                    error: function (response) {
                        swal("Oops", "Server error encountered", "error");
                        //alert("Server error encountered");
                    },
                    complete: function (response) {

                    }
                });
            }
            ;

            $('#allLocationList').on('click', '.deleteLocationBtn', deleteLocButton);
            function deleteLocButton(e) {
                e.preventDefault();

                var url = $(this).data('uri');

                Swal.fire({
                    title: 'Are you sure ?',
                    text: "Do you really want to delete this ?",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#4CAF50',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, Approved it!'
                }).then((result) => {

                    $.ajax({
                        type: 'get',
                        url: url,
                        success: function (response) {
                            if (response.success === "true") {
                                swal.fire({
                                    text: 'Congratulation deleted successfully !!',
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


                });

            }
            ;
        </script>
    </body>
</html>
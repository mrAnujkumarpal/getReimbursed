<%--
    Document   : Add new vendor
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Vendor Registration | EMS</title>
    </head>


    <style type="text/css">
        .error {
            background-color: #f2dede;
            color: #b71c1c;
            padding: 10px 0;
            text-align: center;
        }
        .sucess{
            background-color:#dff0d8;
            color: #1b5e20 ;
            padding: 10px 0;
            text-align: center;
        }

        .input-field input[type=date]:focus + label,
        .input-field input[type=text]:focus + label,
        .input-field input[type=email]:focus + label,
        .input-field input[type=password]:focus + label {
            color: #e91e63;
        }

        .input-field input[type=date]:focus,
        .input-field input[type=text]:focus,
        .input-field input[type=email]:focus,
        .input-field input[type=password]:focus {
            border-bottom: 2px solid #e91e63;
            box-shadow: none;
        }
        .input-field>label, .browser-default>option{color:#20ac76;}
        #panelBck{box-shadow: 0 6px 20px 0 rgba(244, 143, 177, 0.5);}
        h5{color:#8d2929;}
    </style>
    <body>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>

        <div class="container-fluid">
            <div class="row">
                <div class="col s12 m8   offset-m2   z-depth-4 card-panel" id="panelBck">
                    <div class="input-field col s12 center">
                        <h5 class="center login-form-text">${mode} Vendor</h5>
                    </div>

                    <form  method="post" action="/addNewVendor">
                        <div class="row">
                            <div class="input-field col s10 offset-s1">
                                <c:if test="${success ne null}">
                                    <c:if test="${success eq  'true'}">
                                        <div class="sucess text-center ">${message} </div>
                                    </c:if>
                                    <c:if test="${success eq  'false'}">
                                        <div class="error alert-info text-center ">${message} </div>
                                    </c:if>
                                </c:if>
                            </div>
                            <div class="row">
                                <input  name="vendor_Id" type="hidden" value="${vendor.vendor_Id}">

                                <div class="input-field col s6">
                                    <input name="vendor_name" type="text" class="validate"  value="${vendor.vendor_name}">
                                    <label for="vendor_name">Vendor Name</label>
                                </div>

                                <div class="input-field col s6">
                                    <input name="vendor_phno" type="text" class="validate"  value="${vendor.vendor_phno}" data-length="10">
                                    <label for="vendor_phno">Vendor contact no.</label>
                                </div>


                                <div class="input-field col s12">
                                    <textarea id="textarea" name="vendor_address" class="materialize-textarea" data-length="120"  value="${vendor.vendor_address}"></textarea>
                                    <label for="textarea">Vendor Address</label>
                                </div>

                                <div class="col s12">
                                    <select class="browser-default" name="location" >
                                        <c:forEach items="${locationList}" var="ll">
                                            <option value="${ll.location_id}"<c:if test="${ll.location_id== vendor.location.location_id}">selected</c:if>> ${ll.location_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="input-field col s12">
                                    <button href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn waves-effect waves-light col s12">${mode} Vendor</button>
                                </div>

                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>

        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
        <script src="assets/js/main.js" type="text/javascript"></script>
        <script>



            $(document).ready(function () {
                $('select').formSelect();
                M.updateTextFields();
            });
            $('.dropdown-trigger').dropdown();
        </script>
    </body>
</html>
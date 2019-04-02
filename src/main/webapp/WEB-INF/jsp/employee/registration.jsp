<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head><title>Employee Registration | EMS</title></head>


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
        body { background-color:#f1f3e9;}
    </style>


    <body>

        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="col s12 m8   offset-m2   z-depth-1 card-panel" id="panelBck">

                    <div class="input-field col s12 center">
                        <h5 class="center login-form-text"> Employee Registration</h5>
                    </div>

                    <form    method="post" action="/empRegistration">
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
                                <input  name="id" type="hidden"   value="${employee.id}">

                                <div class="input-field col s6  ">
                                    <input name="fName" type="text" class="validate"  value="${employee.fName}">
                                    <label for="fName">First Name</label>
                                </div>

                                <div class="input-field col s6  ">
                                    <input name="lName" type="text" class="validate"  value="${employee.lName}">
                                    <label for="lName">Last Name</label>
                                </div>

                                <div class="input-field col s12  ">
                                    <input name="mobile" type="text" class="validate"  value="${employee.mobile}" data-length="10">
                                    <label for="mobile">Mobile no.</label>
                                </div>

                                <div class="input-field col s6  ">
                                    <input name="email" type="email" class="validate"  value="${employee.email}">
                                    <label for="email">Email</label>
                                </div>

                                <div class="input-field col s6  ">
                                    <input type="password" name="password" />
                                    <label for="Password">Password</label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s6">
                                    <select class="browser-default" name="location" >
                                        <option value="0">Choose employee location</option>
                                        <c:forEach items="${locationList}" var="ll">
                                            <option value="${ll.location_id}"<c:if test="${ll.location_id== employee.location.location_id}">selected</c:if>> ${ll.location_name}</option>
                                        </c:forEach>
                                    </select>
                                </div>


                                <div class="col s6">
                                    <select class="browser-default"   name="empRole">
                                        <option value="0">Choose employee role</option>
                                        <c:forEach items="${roleList}" var="role">
                                            <option value="${role.id}"<c:if test="${role.id==employee.empRole.id}">selected</c:if>>${role.empRole} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col s6">
                                    <select class="browser-default"   name="subimitter_To">
                                        <option value="0">Choose submitter to</option>
                                        <c:forEach var="empl" items="${submitterToList}">
                                            <option value="${empl.id}"<c:if test="${empl.id==employee.subimitter_To.id}">selected</c:if>>${empl.fName} ${empl.lName} </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col s6">
                                    <select class="browser-default"   name="approver_To">
                                        <option value="0">Choose approver to</option>
                                        <c:forEach var="empl" items="${approverToList}">
                                            <option value="${empl.id}"<c:if test="${empl.id==employee.approver_To.id}">selected</c:if>>${empl.fName} ${empl.lName} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12">
                                <button href="#" style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);"  class="btn waves-effect waves-light col s12">Add Employee</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="input-field col s6 m6 l6">
                            <p class="margin medium-small"><a href="/login">Login </a></p>
                        </div>
                        <div class="input-field col s6 m6 l6">
                            <p class="margin right-align medium-small"><a href="#">change Password </a></p>
                        </div>
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
            $('select').formSelect();
        });
        $('.dropdown-trigger').dropdown();
    </script>
</body>
</html>
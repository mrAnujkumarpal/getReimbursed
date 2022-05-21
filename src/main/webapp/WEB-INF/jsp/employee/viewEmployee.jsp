<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>

    <head><title>Home Page | EMS</title></head>
    <body>
        <style>
            .card .card-content {
                padding: 24px;
                border-radius: 0 0 2px 2px;
            }
            .gradient-45deg-red-pink {
                background: #ff5252;
                background: -webkit-linear-gradient(45deg, #ff5252, #f48fb1) !important;
                background: -moz- oldlinear-gradient(45deg, #ff5252, #f48fb1) !important;
                background: -o-linear-gradient(45deg, #ff5252, #f48fb1) !important;
                background: linear-gradient(45deg, #ff5252, #f48fb1) !important;
            }
            .gradient-45deg-amber-amber {
                background: #ff6f00;
                background: -webkit-linear-gradient(45deg, #ff6f00, #ffca28) !important;
                background: -moz- oldlinear-gradient(45deg, #ff6f00, #ffca28) !important;
                background: -o-linear-gradient(45deg, #ff6f00, #ffca28) !important;
                background: linear-gradient(45deg, #ff6f00, #ffca28) !important;
            }
            .gradient-45deg-green-teal {
                background: #43a047;
                background: -webkit-linear-gradient(45deg, #43a047, #1de9b6) !important;
                background: -moz- oldlinear-gradient(45deg, #43a047, #1de9b6) !important;
                background: -o-linear-gradient(45deg, #43a047, #1de9b6) !important;
                background: linear-gradient(45deg, #43a047, #1de9b6) !important;
            }
            .profile-card-i {
                position: relative;
                top: 6px;
                margin-right: 10px;
            }
        </style>

        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>

        <div class="container-fluid"   style="text-transform: capitalize">
            <div class="row" style="margin-top: 10px;">

                <div class="col s12">

                    <div class="col s12 m5 l3">
                        <div class="row">
                            <div class="z-depth-1">
                                <div class="center-align">
                                    <c:choose>
                                        <c:when test="${empImage ne null && not empty empImage}">
                                            <img  class="circle responsive-img activator z-depth-5" width="150" style="max-height:160px; min-height: 150px;" src="data:image/jpeg;base64,${empImage.base64}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img class="circle  responsive-img z-depth-5"  src="/assets/img/noImage.jpg" style='max-height:200px;'>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="card">
                                    <div class="card-content" style="border-top:1px solid #f1f3e9;">

                                        <span class="card-title  flow-text left-align">${employeeData.fName} ${employeeData.lName} </span>

                                        <p><i class="material-icons profile-card-i">perm_identity</i>    ${employeeRole}</p>
                                        <p><i class="material-icons profile-card-i">perm_phone_msg</i>    ${employeeData.mobile} </p>
                                        <p><i class="material-icons profile-card-i">email</i>  <span  style="text-transform: none; color:blue;">  ${employeeData.email} </span></p>
                                        <p><i class="material-icons profile-card-i">location_on</i>   ${employeeData.location.location_name} </span></p>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col s12 m7 l9">

                        <div class="row">
                            <div class="col s12 m6 l4">
                                <a href="/expenseHistory/1" style="color:white;">
                                    <div class="card gradient-shadow gradient-45deg-red-pink border-radius-3">
                                        <div class="card-content center">
                                            <h6 class="white-text lighten-4">
                                                <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${crTDAmount}"/>
                                            </h6>
                                            <p class="white-text lighten-4">Expense Created</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col s12 m6 l4">
                                <a href="#" style="color:white;">
                                    <div class="card gradient-shadow gradient-45deg-amber-amber border-radius-3">
                                        <div class="card-content center">
                                            <h6 class="white-text lighten-4">
                                                <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${penDNGAmnt}"/>
                                            </h6>
                                            <p class="white-text lighten-4">Expense InProgress</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col s12 m12 l4">
                                <a href="/expenseHistory/5" style="color:white;">
                                    <div class="card gradient-shadow gradient-45deg-green-teal border-radius-3">
                                        <div class="card-content center">
                                            <h6 class="white-text lighten-4">
                                                <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${rmbSDAmount}"/>
                                            </h6>
                                            <p class="white-text lighten-4">Expense Reimbursed</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col s12 m12 l12">
                                <c:if test="${tlNotification != 0}">
                                    <div class="col s12 m6 l4">
                                        <div class="card">
                                            <div class="collapsible-header"> ${tlNotification}<i class="material-icons">notifications</i>Approval Pending</div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${mngrNotification != 0}">
                                    <div class="col s12 m6 l4">
                                        <div class="card">
                                            <div class="collapsible-header">${mngrNotification}<i class="material-icons">notifications</i>Audit Pending</div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${finNotification != 0}">
                                    <div class="col s12 m12 l4">
                                        <div class="card">
                                            <div class="collapsible-header">${finNotification}<i class="material-icons">notifications</i>Reimburse Pending</div>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col s12 m12 l12">

                                <div class="card-panel">
                                    <div class="card-content">
                                        <a href="/myTeamMembers/${employeeData.id}" class="right"><small> ${employeeData.fName} ${employeeData.lName} team</small></a>

                                        <c:if test="${showUploadDpForm eq true}">
                                            <a id="uploadMyDp" class="waves-effect waves-light btn-small"><small>Change my DP </small></a>
                                        </c:if>
                                        <c:if test="${showUploadDpForm ne true}">
                                            Hello

                                        </c:if>
                                    </div>
                                </div>

                                <c:if test="${showUploadDpForm eq true}">
                                    <div class="col s12 m12 l12" id="changeDPForm" style="display:none;">
                                        <form method="post" action="doUploadEmpDP" enctype="multipart/form-data">
                                           <div class="col s12 m12 l11">
                                            <div class = "file-field input-field">
                                              <div class="col s12 m12 l10">
                                                <div class="btn"><span>Browse</span>  <input name="empPhoto" type = "file"/></div>
                                                <div class = "file-path-wrapper">
                                                    <input class="file-path validate" type="text" placeholder="Choose a photo to upload"/>
                                                </div>
                                                </div>
                                                <div class="col s12 m12 l2" class="right">
                                                <button   class="waves-effect waves-light btn gradient-45deg-red-pink z-depth-4 mr-1 mb-2" type="submit" value="Upload">
                                                    <i class="large material-icons">cloud_upload</i> Upload
                                                </button>
                                                </div>
                                            </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--Import jQuery before materialize.js-->




            <script>
                $(document).ready(function () {
                    $('select').formSelect();
                });
                $('.dropdown-trigger').dropdown();

                $("#uploadMyDp").click(function () {
                    $("#changeDPForm").toggle();
                });
            </script>

        </div>

    </body>
</html>
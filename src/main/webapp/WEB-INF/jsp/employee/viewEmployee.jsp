<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


    </head>

    <body>
    <%@include file="/WEB-INF/jsp/employee/header.jsp"%>

        <div class="container-fluid">
           <div class="row">
                <div class="col s12 m6 l4   offset-m3 offset-l4">
                      <div class="card z-depth-2">
                           <div class="card-image waves-effect waves-block waves-light">
                              <c:choose>
                                  <c:when test="${empImage ne null && not empty empImage}">
                                        <img class="responsive-img" style='max-height:700px;'  src="data:image/jpeg;base64,${empImage.base64}">
                                        <span class="card-title">  ${employeeData.id}</span>
                                  </c:when>
                                  <c:otherwise>
                                  <img class="responsive-img"  src="/assets/img/noImage.jpg" style='max-height:320px;'>
                                  </c:otherwise>
                              </c:choose>
                           </div>
                           <div class="card-content" style="border-top:1px solid #f1f3e9;">
                                <a href="/myTeamMembers/${employeeData.id}" class="text-capitalize right"><small> ${employeeData.fName} team</small></a>
                                 <span class="card-title text-capitalize flow-text left-align">${employeeData.fName} ${employeeData.lName}</span>

                                <p><i class="material-icons">perm_identity</i>  :${showUploadDpForm eq true}-- ${showUploadDpForm eq true}   ${employeeRole}</p>
                                <p><i class="material-icons">perm_phone_msg</i> :  ${employeeData.mobile} </p>
                                <p><i class="material-icons">email</i> :  ${employeeData.email}</p>

                                   <ul id="tabs-swipe-demo" class="tabs">
                                      <li class="tab col s3 m4 l4"><a href="#test-swipe-Created">Created</a></li>
                                      <li class="tab col s3 m4 l4"><a class="active" href="#test-swipe-Pending">Pending</a></li>
                                      <li class="tab col s6 m4 l4"><a href="#test-swipe-Rembrsd">Reimbursed</a></li>
                                    </ul>
                                    <div id="test-swipe-Created" class="col s12"> <h6 class="left-align blue-text">
                                    <i class="fa fa-inr"></i> <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${crTDAmount}"/></h6></div>
                                    <div id="test-swipe-Pending" class="col s12"><h6 class="center-align red-text">
                                     <i class="fa fa-inr"></i> <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${penDNGAmnt}"/></h6></div>
                                    <div id="test-swipe-Rembrsd" class="col s12"><h6 class="right-align green-text">
                                     <i class="fa fa-inr"></i> <fmt:formatNumber type="number" maxFractionDigits="2"  minFractionDigits="2" value="${rmbSDAmount}"/></h6></div>
                                    <p> &nbsp; </p>
                           </div>
                      </div>
               </div>
               <c:if test="${showUploadDpForm eq true}">
               <div class="col s10 m6 l4   offset-m3 offset-l4  offset-s1">
                     <form method="post" action="doUploadEmpDP" enctype="multipart/form-data">
                         <div class = "file-field input-field">
                             <div class="btn"><span>Browse</span><input name="empPhoto" type = "file"/></div>
                               <div class = "file-path-wrapper">
                                 <input class="file-path validate" type="text" placeholder="Choose a photo to upload"/>
                             </div>
                         </div>

                          <div class="input-field">
                             <button   class="waves-effect waves-light btn" type="submit" value="Upload">
                                <i class="large material-icons">cloud_upload</i>
                             </button>
                          </div>
                     </form>
                </div>
                </c:if>
           </div>
        </div>


            <<!--Import jQuery before materialize.js-->

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
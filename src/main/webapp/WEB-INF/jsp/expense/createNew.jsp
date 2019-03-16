<%--
    Document   : homePage
    Author     : Anuj
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
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
            #upLadedBillsName{height:40px;}
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
        <script type="text/javascript">
            $(document).ready(function () {
                $('.datepicker').datepicker();
            });
            $(function () {
                $("input[name='billable']").click(function () {
                    if ($("#inlineRadio1").is(":checked")) {
                        $("#uploadBillsDiv").show();
                    } else {
                        $("#uploadBillsDiv").hide();
                    }
                });
            });
            function checkFileSize(inputFile) {
                var max = 2 * 1024 * 1024; // 2MB
                if (inputFile.files && inputFile.files[0].size > max) {
                    alert("Please upload bill image which have less than 2 MB size"); // Do your thing to handle the error.
                    inputFile.value = null; // Clear the field.
                }
            }
            ;
        </script>
        <div class="container-fluid" id="pageBody">
            <div class="row" style="margin-top: 10px;">
                <form    method="post" action="/createEditExpense" enctype="multipart/form-data">
                    <div class="col s12 m6 l6">
                        <div class="z-depth-1">
                            <div class="input-field col s12 center">
                                <h5 class="center login-form-text">Create New Expense</h5>
                            </div>
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
                                    <input  name="id" type="hidden"   value="${expense.exp_id}">
                                    <div class="input-field col s6">
                                        <input name="exp_name" type="text" class="validate"  value="${expense.exp_name}" data-length="30">
                                        <label for="exp_name">Expense Name</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input type="date" class="datepicker" value="${expense.exp_Date}"  name="expenseDate" placeholder="Select delivery time slot">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s6" Style="margin-top:-2px;">
                                        <input name="exp_amount" type="text" class="validate"  value="${expense.exp_amount}">
                                        <label for="exp_amount">Expense Amount</label>
                                    </div>
                                    <div class="col s6">
                                        <select name="paymentMode" class="browser-default"Style="border:none; border-bottom:1px solid #111111;" >
                                           <option value="0">Payment mode</option>
                                           <c:forEach items="${payModeList}" var="pML">
                                               <option value="${pML.pay_Id}" <c:if test="${pML.pay_Id == expense.paymentMode.pay_Id}">selected</c:if> > ${pML.pay_type}</option>
                                           </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s6">
                                        <select class="browser-default" name="expenseType" >
                                            <option value="0">Expense Type</option>
                                            <c:forEach items="${expenseTypeList}" var="etL">
                                                <option value="${etL.expType_Id}" <c:if test="${etL.expType_Id == expense.expenseType.expType_Id}">selected</c:if> > ${etL.expType_Name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col s6">
                                        <select class="browser-default"   name="vendor"  onchange="admSelectCheck(this);">
                                            <option value="0">Select Vendor </option>
                                            <c:forEach items="${allVendorsList}" var="avL">
                                                <option value="${avL.vendor_Id}" <c:if test="${avL.vendor_Id == expense.vendor.vendor_Id}">selected</c:if> > ${avL.vendor_name}</option>
                                            </c:forEach>
                                            <option id="otherSelected"  value="2685">Others </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <textarea  id="textarea" name="exp_description"  class="materialize-textarea" data-length="120"  value="${expense.exp_description}"></textarea>
                                        <label for="exp_description">Expense Description</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s12">
                                        <label for="genter_select" style="font-size: 1rem;color:#20ac76;">Upload Bills.</label>
                                        <span>
                                            <span style="margin-right:20px;">
                                                <input name="billable" type="radio" id="inlineRadio1" value="1"/>
                                                <label for="inlineRadio1">Yes </label>
                                            </span>
                                            <span style="margin-left: 20px;">
                                                <input name="billable" type="radio" id="inlineRadio2"  value="0" checked="checked" />
                                                <label for="inlineRadio2">No</label>
                                            </span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <button href="#" class="btn waves-effect waves-light col s12"style="background: linear-gradient(to right, #018647 0%, #008570 50%, #008685 100%);" >Add Expense</button>
                                </div>
                            </div>
                            <h5>&nbsp;</h5>
                        </div>
                    </div>
                    <div class="col s12 m6 l6"   id="uploadBillsDiv" style="height:auto; display: none;">
                        <div class="row z-depth-1">
                            <div class="col s12 m12 l12">
                                <div class="help-block photodiv" style="text-align:center; margin-bottom: 10px;">  Upload snapshot or any photo format of bill.</div>
                                <div class="form-group"    style="padding: 10px 0px; margin: 10px 0px; border-top: 1px dotted #8d2929;">
                                    <div class="">
                                        <input id="fileupload" name="pictureName" type="file" multiple="multiple" style='background-color:yellowgreen;margin:0px 10px;'>
                                    </div>
                                </div>
                                <div id="dvPreview"></div>
                                <br/>
                                <br/>
                                <div id="">
                                    <select class="browser-default" id="upLadedBillsName">
                                        <option value="0"> --------------------Bills name------------------------  </option>
                                    </select>
                                </div>
                                <br />
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script type="text/javascript">
(function($){
    $(function(){
      $('.button-collapse').sideNav();
      $('.parallax').parallax();
    });
   })(jQuery);


             $('#textarea').trigger('autoresize');

            $("#fileupload").change(function () {
                if (typeof (FileReader) != "undefined") {
                    var dvPreview = $("#dvPreview");
                    dvPreview.html("");
                    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp|.pdf)$/;
                    $($(this)[0].files).each(function () {
                        var file = $(this);
                        $('#upLadedBillsName').append('<option value="' + file[0].name.toLowerCase() + '">' + file[0].name.toLowerCase() + '</option>');
                        console.log('---> ' + file[0].size);
                        if (regex.test(file[0].name.toLowerCase())) {
                            var reader = new FileReader();
                            reader.onload = function (e) {
                                var img = $("<img />");
                                img.attr("style", "height:150px;width: 144px; margin:5px; \n\
                                                   border:1px solid #e6e7e9; padding:3px;");
                                img.attr("src", e.target.result);
                                dvPreview.append(img);
                            };
                            reader.readAsDataURL(file[0]);
                        } else {
                            alert(file[0].name + " is not a valid image file.");
                            dvPreview.html("");
                            return false;
                        }
                    });
                } else {
                    alert("This browser does not support HTML5 FileReader.");
                }
            });
            function admSelectCheck(nameSelect)
            {
                if (nameSelect) {
                    admOptionValue = document.getElementById("otherSelected").value;
                    if (admOptionValue == nameSelect.value) {
                        document.getElementById("addVendorDiv").style.display = "block";

                    } else {
                        document.getElementById("addVendorDiv").style.display = "none";

                    }
                } else {
                    document.getElementById("addVendorDiv").style.display = "none";

                }
            }
            ;
        </script>
    </body>
</html>
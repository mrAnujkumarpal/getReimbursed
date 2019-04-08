
<!--Comman materalizedcss-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="/css/materialize.min.css"  media="screen,projection"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">

<!-- Sweet Alert -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.min.js"></script>


<style>

    .error {
        background-color: #f2dede;
        color: #b71c1c;
        padding: 10px 0;
        text-align: center;
    }
    #panelBck{box-shadow: 0 6px 20px 0 rgba(244, 143, 177, 0.5);}
</style>

<nav class="navbar-fixed"  style="background:  linear-gradient(to right, #6A1B58 0%, #6A1B58 40%, #300D2A 100%);">
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo" style="margin:10px;">
            <img class="responsive-img" style="max-height:65px;min-height:50px; margin-top:-12px;" src="/assets/img/myLogo.png"/>
        </a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/createEditExpense">Create Expense</a></li>
                <c:if test="${employeeRoleId == 5 ||employeeRoleId == 6 }">
                <li><a class='dropdown-button' href='#' data-activates='feature-dropdown' data-belowOrigin="true" data-constrainWidth="false">Employee<i class="material-icons right">arrow_drop_down</i> </a></li>
                <!-- Dropdown Structure -->
                <ul id='feature-dropdown' class='dropdown-content'>
                    <li><a href="/empRegistration">Add Employee</a></li>
                    <li><a href="/viewAllEmployees">All Employees</a></li>
                    <li><a href="/viewAllRoles">Employee Roles</a></li>
                </ul>
            </c:if>
            <c:if test="${employeeRoleId == 4 || employeeRoleId == 5 ||employeeRoleId == 6 }">
                <li><a href="/expenseStatus">Expense Status</a></li>
                <li><a href="/viewAllLocations">Location</a></li>
                <li><a href="/expenseType">Expense Type</a></li>
                </c:if>
            <li><a href="/activityMonitoring">Monitoring</a></li>
            <li><a class='dropdown-button' href='#' data-activates='vendor-dropdown' data-belowOrigin="true" data-constrainWidth="false">Vendor<i class="material-icons right">arrow_drop_down</i> </a></li>
            <!-- Dropdown Structure -->
            <ul id='vendor-dropdown' class='dropdown-content'>
                <c:if test="${employeeRoleId == 4||employeeRoleId == 5 ||employeeRoleId == 6}">
                    <li><a href="/addNewVendor">Add Vendor</a></li>
                    </c:if>
                <li><a href="/allVendors">View Vendors</a></li>
            </ul>

            <c:if test="${employeeRoleId == 2 || employeeRoleId == 3 ||employeeRoleId == 4||employeeRoleId == 5||employeeRoleId == 6}">
                <li><a class='dropdown-button' href='#' data-activates='submittedReports-dropdown' data-belowOrigin="true" data-constrainWidth="false">Pending Reports<i class="material-icons right">arrow_drop_down</i> </a></li>
                <!-- Dropdown Structure -->
                <ul id='submittedReports-dropdown' class='dropdown-content'>
                    <li><a href="/pendingExpenseforApproval">Pending for Approval</a></li>
                        <c:if test="${employeeRoleId == 3 || employeeRoleId == 4 || employeeRoleId == 5 || employeeRoleId == 6}">
                        <li><a href="/pendingExpenseforAudit">Pending for Audit</a></li>
                        </c:if>
                        <c:if test="${employeeRoleId == 5 || employeeRoleId == 6}"><li><a href="/pendingExpenseforRemburse">Pending for Reimburse</a></li></c:if>
                    </ul>
            </c:if>


            <li>
                <a class='dropdown-button' href='#' data-activates='reports-dropdown' data-belowOrigin="true" data-constrainWidth="false">My Reports<i class="material-icons right">arrow_drop_down</i> </a>
            </li>
            <!-- Dropdown Structure -->
            <ul id='reports-dropdown' class='dropdown-content'>
                <li><a href="/expenseHistory/1">My Created Expense</a></li>
                <li><a href="/expenseHistory/2">My Submitted Expense</a></li>
                <li><a href="/expenseHistory/3">My Approved Expense</a></li>
                <li><a href="/expenseHistory/4">My Audited Expense</a></li>
                <li><a href="/expenseHistory/5">My Reimbursed  Expense</a></li>
                <li><a href="/expenseHistory/6">My Rejected  Expense</a></li>
            </ul>


            <li  Style="border-left:1px solid #e6e7e9;">
                <a class='dropdown-button' href='#' data-activates='profile-dropdown' data-belowOrigin="true" data-constrainWidth="false">My Profile<i class="material-icons right">arrow_drop_down</i> </a>
            </li>
            <!-- Dropdown Structure -->
            <ul id='profile-dropdown' class='dropdown-content'>
                <li><a href="/default">My Profile Page</a></li>
                <li><a href="/changePwd">Change Password</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>

        </ul>

        <ul class="side-nav" id="mobile-demo">
            <li class="active"><a href="/empRegistration">A Registration</a></li>
            <li><a href="/viewAllEmployees">A Employees</a></li>
            <li><a href="/viewAllLocations">Location</a></li>
            <li><a href="/viewAllRoles">A Roles</a></li>
            <li><a href="/uploadDp">Upload photo</a></li>
            <li><a href="/404">Not found</a></li>
            <li><a href="/500">Server</a></li>
            <li><a href="/addNewVendor">Add Vendor</a></li>
            <li><a href="/allVendors">Vendors</a></li>
        </ul>

    </div>
</nav>

<script>
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, options);
  });

  // Or with jQuery

  $(document).ready(function(){
    $('.sidenav').sidenav();
  });

</script>

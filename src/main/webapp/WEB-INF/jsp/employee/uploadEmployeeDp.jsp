

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
   <head>
          <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
          <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
   </head>
  <body>
     <%@include file="/WEB-INF/jsp/employee/header.jsp"%>
     <%@include file="/WEB-INF/jsp/employee/footer.jsp"%>
           <div class="container-fluid">
               <div class="row">
                   <div class="col s12 m12 l12">
                       <table class="striped  responsive-table z-depth-1" id="allLocationList">
                           <thead>
                              <tr style="color:#4e342e">
                                   <th>Employee ID</th>
                                   <th>Image ID/Name</th>
                                   <th>Image Type</th>
                                   <th>Image</th>
                              </tr>
                           </thead>
                           <tbody style="color:#004d40">
                              <tr>
                                 <td>${dpData.employee_id} </td>
                                 <td>${dpData.empDPId} / ${dpData.empDPName}</td>
                                 <td>${dpData.empDPType}</td>
                                 <td>
                                    <a href="/default">
                                         <img class="z-depth-5" src="data:image/jpeg;base64,${dpData.base64}" style='max-height:100px;'>
                                    </a>
                                 </td>
                              </tr>
                           </tbody>
                       </table>
                   </div>
               </div>
           </div>
     <!--Import jQuery before materialize.js-->
     <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
     <script src="https://cdn.jsdelivr.net/materialize/0.98.2/js/materialize.min.js"></script>
     <script src="assets/js/main.js" type="text/javascript"></script>
  </body>
</html>
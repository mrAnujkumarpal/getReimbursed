/* This is a mobile view sidebar navigation JS function */
//JavaScript code should be executed in "strict mode"


$(".button-collapse").sideNav();
$('select').formSelect();
$('.dropdown-trigger').dropdown();


$('#allExpTypList').on('click', '.deleteExpenseTypeBtn', deleteButton);


function deleteButton(e) {

    if (confirm("Do you really want to delete ? ")) {
        var url = $(this).data('uri');
        alert(url);
        $.ajax({
            type: 'get',
            url: url,
            success: function (response) {
                if (response.success === "true") {
                    location.reload();
                } else {
                    alert(response.message);
                }
            },
            error: function (response) {
                alert("Server error encountered");
            },
            complete: function (response) {

            }
        });
    }
}
;

document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems, options);
});

// Or with jQuery

$(document).ready(function () {
    $('.sidenav').sidenav();
});
     
$(document).ready(function () {
    const token = getCookie('token');

    if (token == "") {
        location.replace("/login/sign-in.html");
    }

    $("#logOut").on('click', function () {
        logOff();
    });

    $("#createSearchButton").on('click', function () {
        createNewSearch();
    });

});


function createNewSearch() {
    let searchText = $("#searchText").val();
    let selectFrequency = $("#selectFrequency").val();

}

function validateFields(searchText, selectFrequency) {

}

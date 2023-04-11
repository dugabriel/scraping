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

    listSearches();
});

function createNewSearch() {
    let searchText = $("#searchText").val();
    let selectFrequency = $("#selectFrequency").val();

    if (validateFields(searchText, selectFrequency)) {
        createSearch(searchText, selectFrequency);
        $("#searchText").val("");
        listSearches();
    }
}

function removeRow(element) {
    console.log(element);
}

function validateFields(searchText, selectFrequency) {

    if (searchText == "" || selectFrequency == "") {
        alert('Preencha os campos para cadastrar uma busca');
        return false;
    }
    return true;
}

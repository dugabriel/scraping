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

    const urlRegex = new RegExp(/^https:\/\/olx\.com\.br\/?$/, "gm");

    if (!urlRegex.test(searchText)) {
        alert('Url inválida')
        return false;
    }

    if (validateFields(searchText, selectFrequency)) {
        createSearch(searchText, selectFrequency);
        $("#searchText").val("");
        listSearches();
    }
}

function removeRow(element) {
    removeSearch(element.id, element);
}

function validateFields(searchText, selectFrequency) {

    if (searchText == "" || selectFrequency == "") {
        alert('Preencha os campos para cadastrar uma busca');
        return false;
    }
    return true;
}

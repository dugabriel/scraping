$(document).ready(function () {
    $("#login").click(function () {
        login();
    });
});

function login() {
    let email = $("#email").val();
    let password = $("#password").val();

    if (validateFields(email, password)) {
        if (getToken(email, password)) {
            location.replace("/");
        } else {
            $("#password").val("");
        }
    }
}
function validateFields(email, password) {
    if (email == "" || password == "") {
        alert('Insira o e-mail e senha');
        return false;
    }

    return true;
}
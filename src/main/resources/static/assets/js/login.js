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
            let password = $("#password").val("");
        }
    }
}

function parseJWT(token) {
    try {
        return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
        return null;
    }
}

function validateFields(email, password) {
    if (email == "" || password == "") {
        alert('Insira o e-mail e senha');
        return false;
    }

    return true;
}
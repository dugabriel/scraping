$(document).ready(function () {

    $("#login").click(function () {
        login();
    });
});


function login() {
    let email = $("#email").val();
    let password = $("#password").val();

    if (validateFields(email, password)) {
        const token = getToken(email, password);

        if (token != "" || token != null) {
            const jwt = parseJWT(token);
            console.log(jwt);

            document.cookie = "token=Bearer "+token+"; max-age=86400; path=/;";
            //location.replace("/");
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
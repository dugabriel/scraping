const baseURL = "https://backend-scraping.herokuapp.com";

function register(name, password, email) {
    var settings = {
        "url": baseURL + "/api/v1/user",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "accept": "*/*",
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "name": name,
            "email": email,
            "password": password
        }),
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
            return true;
        })
        .fail(function (response) {
            console.log(response);
            alert('Ocorreu um erro ao efetuar o cadastro');
            return false;
        });
}

function getToken(email, password) {
    var settings = {
        "url": baseURL + "/api/v1/auth/token",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "accept": "*/*",
            "Content-Type": "application/json"
        },
        "data": JSON.stringify({
            "username": email,
            "password": password
        }),
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
            document.cookie = "token=Bearer "+token+"; max-age=86400; path=/;";
            return true;
        })
        .fail(function (response) {
            console.log(response);
            alert('Login ou senha inválida')
            return false;
        });
}

function logOff() {
    document.cookie = "token=; max-age=-1; path=/;";
    location.replace("/register/sign-up.html");
}
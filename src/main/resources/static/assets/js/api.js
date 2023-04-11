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
            location.replace("/login/sign-in.html");
        })
        .fail(function (response) {
            console.log(response);
            alert('Ocorreu um erro ao efetuar o cadastro');
        });
}

function setToken(email, password) {
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
            document.cookie = "token=Bearer "+response.token+"; max-age=86400; path=/;";
            location.replace("/");
        })
        .fail(function (response) {
            console.log(response);
            document.cookie = "token=; max-age=-1; path=/;";
            $("#password").val("");
            alert('Login ou senha inválida')
        });
}

function logOff() {
    document.cookie = "token=; max-age=-1; path=/;";
    location.replace("/login/sign-in.html");
}
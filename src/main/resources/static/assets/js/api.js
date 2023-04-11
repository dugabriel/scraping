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
            document.cookie = "token=Bearer " + response.token + "; max-age=86400; path=/;";
            location.replace("/");
        })
        .fail(function (response) {
            console.log(response);
            document.cookie = "token=; max-age=-1; path=/;";
            $("#password").val("");
            alert('Login ou senha inválida')
        });
}

function createSearch(searchExpression, frequency) {
    var settings = {
        "url": baseURL + "/api/v1/search",
        "method": "POST",
        "timeout": 0,
        "headers": {
            "accept": "*/*",
            "Content-Type": "application/json",
            "Authorization": getCookie('token')
        },
        "data": JSON.stringify({
            "source": "OLX",
            "searchExpression": searchExpression,
            "frequency": "DAY"
        }),
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);
        })
        .fail(function (response) {
            console.log(response);
            alert('Erro ao cadastrar busca');
        });
}

function logOff() {
    document.cookie = "token=; max-age=-1; path=/;";
    location.replace("/login/sign-in.html");
}

function getCookie(token) {
    let name = token + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
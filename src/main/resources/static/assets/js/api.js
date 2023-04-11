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

function listSearches() {
    var settings = {
        "url": baseURL + "/api/v1/search",
        "method": "GET",
        "timeout": 0,
        "headers": {
            "accept": "*/*",
            "Authorization": getCookie('token')
        },
    };

    $.ajax(settings)
        .done(function (response) {
            console.log(response);

            for (var i in response) {
                let frequency = response[i].frequency;
                let frequencySanitized = "";

                switch (frequency) {
                    case "DAY":
                        frequencySanitized = "1 dia";
                        break;
                    case "MIDDAY":
                        frequencySanitized = "12 horas";
                        break;
                    case "HOUR":
                        frequencySanitized = "1 hora";
                        break;
                    case "HALF":
                        frequencySanitized = "30 minutos";
                        break;
                    case "TEST":
                        frequencySanitized = "5 mintutos";
                        break;
                    default:
                        frequencySanitized = "Desconhecido";

                        $('#tableListSearches > tbody:last-child').append(
                            `<tr>
                                <td width="5%">
                                    <div class="d-flex px-2">
                                        <div>
                                            <img src="./assets/images/olx.svg" class="avatar avatar-sm rounded-circle me-2">
                                        </div>
                                    </div>
                                </td>
                                <td width="10%">
                                    <p class="text-sm font-weight-bold mb-0">${frequencySanitized}</p>
                                </td>
                                <td width="80%">
                                    <p class="text-sm font-weight-bold mb-0">${response[i].searchExpression}</p>
                                </td>
                                <td class="align-middle" width="5%">
                                    <button class="btn btn-icon btn-2 btn-primary" type="button" onClick="removeRow(this)">
                                        <svg class="text" width="12px" height="12px" viewBox="0 0 42 44" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"> <title>basket</title> <g id="Basic-Elements" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd"> <g id="Rounded-Icons" transform="translate(-1869.000000, -741.000000)" fill="#FFFFFF" fill-rule="nonzero"> <g id="Icons-with-opacity" transform="translate(1716.000000, 291.000000)"> <g id="basket" transform="translate(153.000000, 450.000000)"> <path class="color-background" d="M34.080375,13.125 L27.3748125,1.9490625 C27.1377583,1.53795093 26.6972449,1.28682264 26.222716,1.29218729 C25.748187,1.29772591 25.3135593,1.55890827 25.0860125,1.97535742 C24.8584658,2.39180657 24.8734447,2.89865282 25.1251875,3.3009375 L31.019625,13.125 L10.980375,13.125 L16.8748125,3.3009375 C17.1265553,2.89865282 17.1415342,2.39180657 16.9139875,1.97535742 C16.6864407,1.55890827 16.251813,1.29772591 15.777284,1.29218729 C15.3027551,1.28682264 14.8622417,1.53795093 14.6251875,1.9490625 L7.919625,13.125 L0,13.125 L0,18.375 L42,18.375 L42,13.125 L34.080375,13.125 Z" opacity="0.595377604"></path> <path class="color-background" d="M3.9375,21 L3.9375,38.0625 C3.9375,40.9619949 6.28800506,43.3125 9.1875,43.3125 L32.8125,43.3125 C35.7119949,43.3125 38.0625,40.9619949 38.0625,38.0625 L38.0625,21 L3.9375,21 Z M14.4375,36.75 L11.8125,36.75 L11.8125,26.25 L14.4375,26.25 L14.4375,36.75 Z M22.3125,36.75 L19.6875,36.75 L19.6875,26.25 L22.3125,26.25 L22.3125,36.75 Z M30.1875,36.75 L27.5625,36.75 L27.5625,26.25 L30.1875,26.25 L30.1875,36.75 Z"></path> </g> </g> </g> </g> </svg>
                                    </button>
                                </td>
                            </tr>`
                        );
                }
            }


        })
        .fail(function (response) {
            console.log(response);
            alert('Erro ao carregar a lista de buscas')
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
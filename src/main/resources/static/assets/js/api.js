const baseURL = "https://backend-scraping.herokuapp.com/"

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
        })
        .fail(function(response) {
            console.log(response);
            alert('Ocorreu um erro ao efetuar o cadastro')
        });
}
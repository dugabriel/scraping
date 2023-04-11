$(document).ready(function () {
    const token = getCookie('token');

    if (token == "") {
        location.replace("/login/sign-in.html");
    }

    $("#logOut").on('click', function () {
        logOff();
    });

});

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
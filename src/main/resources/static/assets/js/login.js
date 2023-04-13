$(document).ready(function () {
    $("#login").click(function () {
        login();
    });
});

function login() {
    let email = $("#email").val();
    let password = $("#password").val();

    if (validateFields(email, password)) {
        setToken(email, password);
    }
}
function validateFields(email, password) {
    if (email == "" || password == "") {
        alert('Insira o e-mail e senha');
        return false;
    }
    return true;
}

function handleEnter(e) {
    if(e.keyCode === 13){
        login();
    }
  }
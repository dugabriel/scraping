$(document).ready(function () {

    $("#submitButton").click(function () {
        createUser();
    });
});


function createUser() {
    let name = $("#name").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let repassword = $("#repassword").val();


    if (validateFields(name, email, password, repassword)) {
        if (register(name, password, email)) {
            location.replace("/login/sign-in.html");
        }
    }
}

function validateFields(name, email, password, repassword) {
    if (name == "" || email == "" || password == "" || repassword == "") {
        alert('Preencha todos os campos');
        return false;
    }

    const emailRegex = new RegExp(/^[A-Za-z0-9_!#$%&'*+\/=?`{|}~^.-]+@[A-Za-z0-9.-]+$/, "gm");

    if (!emailRegex.test(email)) {
        alert('Email inválido')
        return false;
    }

    if (password != repassword) {
        alert('A senha não coincide')
        return false;
    }

    return true;
}

function cleanFields() {
    let name = $("#name").val("");
    let email = $("#email").val("");
    let password = $("#password").val("");
    let repassword = $("#repassword").val("");
}

window.addEventListener("DOMContentLoaded", function () {
    $("#submitBtn").attr("disabled", "disabled");

    let apikey = document.querySelector('meta').content;
    const client = filestack.init(apikey); // filestack is not defined

    const options = {
        onUploadDone: (res) => {
            $("#profilePicUrl").val(res.filesUploaded[0].url);
            console.log(res);
        },
        accept: ["image/*"],
        uploadConfig: {
            retry: 5,
            timeout: 60000
        },
        transformations: {
            crop: true,
            circle: true,
            rotate: true
        }
    }

    $("#buttonUpload").click(function () {
        client.picker(options).open();
    })

    $("#password").keyup(function () {
        let password = $("#password").val();
        if (password.length > 7) {
            $("#8char").css("color", "green");
        } else {
            $("#8char").css("color", "");
        }
        if (password.match(/([A-Z])/)) {
            $("#uppercase").css("color", "green");
        } else {
            $("#uppercase").css("color", "");
        }
        if (password.match(/([a-z])/)) {
            $("#lowercase").css("color", "green");
        } else {
            $("#lowercase").css("color", "");
        }
        if (password.match(/(\d)/)) {
            $("#num").css("color", "green");
        } else {
            $("#num").css("color", "");
        }
        if (password.match(/([!%&@#$^*?_~])/)) {
            $("#special").css("color", "green");
        } else {
            $("#special").css("color", "");
        }
    });

    $("#password").focusout(function () {
        let password = $("#password").val();
        if (!(password.length > 7)) {
            $("#8char").css("color", "red");
        } else {
            $("#8char").css("color", "green");
        }
        if (!password.match(/([A-Z])/)) {
            $("#uppercase").css("color", "red");
        } else {
            $("#uppercase").css("color", "green");
        }
        if (!password.match(/([a-z])/)) {
            $("#lowercase").css("color", "red");
        } else {
            $("#lowercase").css("color", "green");
        }
        if (!password.match(/(\d)/)) {
            $("#num").css("color", "red");
        } else {
            $("#num").css("color", "green");
        }
        if (!password.match(/([!%&@#$^*?_~])/)) {
            $("#special").css("color", "red");
        } else {
            $("#special").css("color", "green");
        }
    });

    $('#confirmPass').focusout(function () {
        let password = $("#password").val();
        let confirmPass = $("#confirmPass").val();
        if (password !== confirmPass) {
            $("#confirmPassMsg").html("Passwords do not match.").css("color", "red");
        } else {
            $("#confirmPassMsg").html("");
        }
    });

    $("#registerForm").keyup(function () {
        let password = $("#password").val();
        let confirmPass = $("#confirmPass").val();
        const pattern = new RegExp(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*.,?]).+$"
        );
        if (pattern.test(password) && password === confirmPass) {
            $("#submitBtn").removeAttr("disabled");
        } else {
            $("#submitBtn").attr("disabled", "disabled");
        }
    })
})
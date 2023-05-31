function updateInfo() {
    const name = document.getElementById("name");
    const city = document.getElementById("city");
    const phone = document.getElementById("phone");
    const file = document.getElementById("avatar");
    var avatar = file.files[0];
    var formData = new FormData();
    formData.append("file", avatar);

    $.ajax({
        url: "/my-account",
        data: {
            action: 'updateInfo',
            txtName: name.value,
            sltCity: city.value,
            txtPhone: phone.value,
            fileAvt: formData,
        },
        traditional: true,
        encType: "multipart/form-data",
        processData: false,
        contentType: false,
        type: "post",
        success: function (response) {
            console.log(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}


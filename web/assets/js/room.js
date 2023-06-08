/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function editPlainText() {
    var canEdit = document.querySelectorAll('.can-edit');
    if (canEdit != null) {
        canEdit.forEach(e => {
            e.addEventListener('click', function () {
                e.removeAttribute('readonly');
                e.classList.remove('form-control-plaintext');
                e.classList.add('form-control');
            })

            e.addEventListener('keypress', function (event) {
                if (event.keyCode === 13) { // Kiểm tra nút Enter
                    e.setAttribute('readonly', true); // Gán lại thuộc tính readonly
                    e.classList.add('form-control-plaintext');
                    e.classList.remove('form-control');

                    var roomid = $(e).attr('room-id');
                    var roomname = e.value;
                    update(roomid, roomname, "update"); // ! Gửi dữ liệu vào server Ajax
                }
            })
        })
    }
}

function update(id, name, action) {
    $.ajax({
        url: "/cinema/room",
        data: {
            id: id,
            name: name,
            action: action
        },
        type: "post",
        success: function (response) {
            $('tbody').html(response);
            $('#deleteRow').click(function () {
                var id = $(this).prev().prev().attr('room-id');
                update(id, null, "delete");
            })
             editPlainText();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}


$('#insertRow').click(function () {
    update(null, null, "add");
})

$('.deleteRow').click(function () {
    var id = $(this).closest('tr').find('.form-control-plaintext.can-edit').attr('room-id');
    update(id, null, "delete");
   
})



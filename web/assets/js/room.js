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
                    roomCrud(roomid, roomname, "update");
                }
            })
        })
    }
}

function roomCrud(id, name, action) {
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
            deleteRoom();
            editPlainText();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}


$('#insertRoom').click(function(e) {
    e.preventDefault();
    roomCrud(null, null, "add");
})

function deleteRoom() {
    $('.deleteRoom').click(function (e) {
        e.preventDefault();
        if (confirm('Bạn có chắc chắn muốn xóa phòng này?')) {
            var id = $(this).closest('tr').find('.can-edit').attr('room-id');
            roomCrud(id, null, "delete");
        }
    })
}

$(document).ready(function() {
    deleteRoom();
    editPlainText();
})


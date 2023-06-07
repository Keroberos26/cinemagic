function showList() {
    var date = $('#showdate').val();
    var room = $('#sortRoom').val();
    var time = $('#sortTime').val();
    var title = $('#sortMovie').val(); 

    $.ajax({
        url: "/cinema/showtimes",
        data: {
            showDate: date,
            room: room,
            time: time,
            title: title,
        },
        type: "post",
        success: function (response) {
            $('table tbody').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

$('.filter').change(function(e) {
    e.preventDefault();

    showList();
})

showList();

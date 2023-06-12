function countCol() {
    var seatRow = document.querySelector('.seats-row');
    var seatCol = seatRow.querySelectorAll('li');
    return seatCol.length;
}

function countRow() {
    return $('.seats-row').length;
}

$('#insertRow').click(function(e) {
    e.preventDefault();

    var newRow = $('<ul class="seats-row">');
    var row = countRow() + 1;
    var seats = '';
    for (let i = 0; i < countCol(); i++) {
        seats += '<li class="space" seat-row="' + row + '" seat-col="' + (i + 1) + '"></li>';
    }
    newRow.append(seats);
    $('.seats-map').append(newRow);
    seatSelected();
})

$('#insertCol').click(function(e) {
    e.preventDefault();

    var col = countCol() + 1;
    var row = 1;
    $('.seats-row').each(function () {
        var newCol = $(this);
        seat = '<li class="space" seat-row="' + row++ + '" seat-col="' + col + '"></li>';
        newCol.append(seat);
    })
    $('.seats-wrapper').scrollLeft($('.seats-map').width());
    seatSelected();
})

// function addCol() {
//     $('.seats-row').each(function () {
//         var row = $(this);
//         seat = '<li class="seat"></li>';
//         row.append(seat);
//     })
// }

// function deleteRow() {
//     var map = $('.seats-map');
//     map.children(":last-child").remove();
// }

// function deleteCol() {
//     $('.seats-row').each(function () {
//         var row = $(this);
//         row.children(":last-child").remove();
//     })
// }

function seatSelected() {
    $('.seats-map li').click(function (e) {
        e.preventDefault();

        $('.seats-map li').removeClass('selected');
        $(this).addClass('selected');

        var url = new URL(window.location.href);
        var searchParams = new URLSearchParams(url.search);

        var id = searchParams.get('id');
        var row = $(this).attr('seat-row');
        var col = $(this).attr('seat-col');

        $.ajax({
            url: "/cinema/seats",
            data: {
                id: id,
                row: row,
                col: col
            },
            type: "get",
            success: function (response) {
                $('.seat-form').html(response);
                
                $('#isSeat').change(function (e) {
                    var isSeat = $(this).prop('checked');
                    var seatNum = $('#seatNum');
                    var type = $('input[name="type"]');
            
                    if (isSeat) {
                        seatNum.prop('disabled', false);
                        type.prop('disabled', false);
                    } else {
                        seatNum.prop('disabled', true);
                        type.prop('disabled', true);
                    }
                })
                $('.btn[type="submit"]').prop('disabled', false);
            },
            error: function (xhr) {
                console.log("ERROR Ajax");
            }
        });
    })
}

$(document).ready(function () {
    seatSelected();

    $('.btn[type="submit"]').click(function (e) {
        e.preventDefault();
    
        var url = new URL(window.location.href);
        var searchParams = new URLSearchParams(url.search);
    
        var roomid = searchParams.get('id');
        var seatid = $('.seats-map li.selected').attr('seat-id');
        var row = $('.seats-map li.selected').attr('seat-row');
        var col = $('.seats-map li.selected').attr('seat-col');
        var isSeat = $('#isSeat').prop('checked');
        var seatNum = $('#seatNum').val();
        var seatType = $('input[name="type"]:checked').val();
    
        $.ajax({
            url: "/cinema/seats",
            data: {
                roomid: roomid,
                seatid: seatid,
                row: row,
                col: col,
                isSeat: isSeat,
                seatNum: seatNum,
                seatType: seatType,
            },
            type: "post",
            success: function (response) {
                $('.seats-map').html(response);
                seatSelected();
            },
            error: function (xhr) {
                console.log("ERROR Ajax");
            }
        });
    })
})

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
    $('.seats-map li[seat-row="'+ row +'"]').click(seatSelect);
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
    $('.seats-map li[seat-col="'+ col +'"]').click(seatSelect);
})

function seatSelect() {
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
}

$(document).ready(function () {
    $('.seats-map li').click(seatSelect);

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
    
        var next = parseInt(col) + 1;
        var nextSpace = $('.seats-map li[seat-row="' + row + '"][seat-col="' + next + '"]');
        var nextSpaceElm = '<li class="space" seat-row="' + row + '" seat-col="' + next + '"></li>';


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
                var seat = JSON.parse(response);
                var seatBox = $('.seats-map li[seat-row="' + row + '"][seat-col="' + col + '"]');
                if (seat != null) {
                    if(seat.id != null) {
                        $('.seats-map li').removeClass('selected');
                        var classType = 'selected seat seat-';
                        
                        
                        switch (seat.type) {
                            case "N":
                                classType += 'normal';
                                if (nextSpace.length == 0 && next <= countCol()) {
                                    seatBox.after(nextSpaceElm);
                                    $('.seats-map li[seat-row="' + row + '"][seat-col="' + next + '"]').click(seatSelect);
                                }
                                break;
                            case "V":
                                classType += 'vip';
                                if (nextSpace.length == 0 && next <= countCol()) {
                                    seatBox.after(nextSpaceElm);
                                    $('.seats-map li[seat-row="' + row + '"][seat-col="' + next + '"]').click(seatSelect);
                                }
                                break;
                            case "C":
                                classType += 'couple';
                                nextSpace.remove();
                                break;
                            default:
                                break;
                        }
                        seatBox.attr({
                            'seat-id': seat.id,
                            'seat-type': seat.type,
                            'class': classType,
                        });
                        seatBox.text(seat.seatNum);
                    } else {
                        if (seat.action == 'delete') {
                            seatBox.removeAttr('seat-id seat-type');   
                            seatBox.attr('class', 'space');
                            seatBox.text('');
                            
                            if (seatType == 'C') {
                                seatBox.after(nextSpaceElm);
                                $('.seats-map li[seat-row="' + row + '"][seat-col="' + next + '"]').click(seatSelect);
                            }
                        }
                    }
                }
            },
            error: function (xhr) {
                console.log("ERROR Ajax");
            }
        });
    })
})

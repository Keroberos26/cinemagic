function countCol() {
    var seatRow = document.querySelector('.seats-row');
    var seatCol = seatRow.querySelectorAll('.seat');
    return seatCol.length;
}

function addRow() {
    var newRow = $('<ul class="seats-row">');
    var seats = '';
    for (let i = 0; i < countCol(); i++) {
        seats += '<li class="seat"></li>';
    }
    newRow.append(seats);
    $('.seats-map').append(newRow);
}

function addCol() {
    $('.seats-row').each(function () {
        var row = $(this);
        seat = '<li class="seat"></li>';
        row.append(seat);
    })
}

function deleteRow() {
    var map = $('.seats-map');
    map.children(":last-child").remove();
}

function deleteCol() {
    $('.seats-row').each(function () {
        var row = $(this);
        row.children(":last-child").remove();
    })
}
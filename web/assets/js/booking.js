$('.seat').click(function () {
    $(this).toggleClass('selected');

    var seatId = $('.seat.selected').map(function () {
        return $(this).attr('seat-id');
    }).get();
    var seatNum = $('.seat.selected').map(function () {
        return $(this).text();
    }).get();
    var seatType = $('.seat.selected').map(function () {
        return $(this).attr('seat-type');
    }).get();
    
    $.ajax({
        url: "/choose-seat",
        data: {
            seatId: seatId,
            seatNum: seatNum,
            seatType: seatType,
        },
        type: "post",
        success: function (response) {
            $('.card.booking-detail .card-text').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });

    if (seatId.length > 0) {
        $('#submitSeats').removeClass('disabled');
    } else {
        $('#submitSeats').addClass('disabled');
    }
})

$('.btn-plus').click(function (e) {
    e.preventDefault();
    var inputField = $(this).prev('input[name="quantity"]');
    var currentValue = parseInt(inputField.val());
    inputField.val(currentValue + 1);

    if (currentValue === 0) {
        $(this).prev().prev('.btn-minus').removeClass('disabled');
    }

    chooseCombo();
});

$('.btn-minus').click(function (e) {
    e.preventDefault();
    var inputField = $(this).next('input[name="quantity"]');
    var currentValue = parseInt(inputField.val());
    if (currentValue > 0) {
        inputField.val(currentValue - 1);
    }

    if (currentValue - 1 === 0) {
        $(this).addClass('disabled');
    }

    chooseCombo();
});

function chooseCombo() {
    var combos = $('input[name="combo"]').map(function () {
        return $(this).val();
    }).get();

    var quan = $('input[name="quantity"]').map(function () {
        return $(this).val();
    }).get();
    
    $.ajax({
        url: "/choose-combo",
        data: {
            combos: combos,
            quantity: quan,
        },
        type: "post",
        success: function (response) {
            $('.card.booking-detail .card-text h4 span').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}


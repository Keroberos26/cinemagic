$('.seat').click(function () {
    $(this).toggleClass('selected');
})

$('#submitSeats').click(function (e) {
    e.preventDefault();

    $('form.d-none').submit();
})

$('form input[name="chkSeats"]').change(function () {
    if ($('input:checked').length > 0) {
        $('#submitSeats').removeClass('disabled');
    } else {
        $('#submitSeats').addClass('disabled');
    }

    var num = $('input:checked').map(function () {
        return $(this).attr('num');
    }).get();

    var seatTotal = '';
    num.forEach(n => {
        if (seatTotal == '')
            seatTotal += n;
        else
            seatTotal += ', ' + n;
    });
    $('.booking-detail .card-text span').text(seatTotal);
});

$('.btn-plus').click(function (e) {
    e.preventDefault();
    var inputField = $(this).prev('input[name="quantity"]');
    var currentValue = parseInt(inputField.val());
    inputField.val(currentValue + 1);

    if (currentValue === 0) {
        $(this).prev().prev('.btn-minus').removeClass('disabled');
    }
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
});


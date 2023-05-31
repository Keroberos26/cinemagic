$('.seat').click(function () {
    $(this).toggleClass('selected');
})

$('#submitSeats').click(function () {
    $('form.invisible').submit();
})

$('form.invisible input').change(function () {
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
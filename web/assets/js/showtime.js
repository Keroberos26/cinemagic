// Click Địa điểm
$('#positionModal .modal-body a').click(function(e) {
    e.preventDefault();
    $('#positionModal .modal-body a').removeClass('active');
    $(this).addClass('active');

    var city = $(this).text();
    $('.btn[data-bs-target="#positionModal"] .city').text(city);
    
    getTheaters();
});

// Click Cinema
$('.cinema').click(function(e) {
    e.preventDefault();

    $('.cinema').removeClass('active');
    $(this).addClass('active');

    getTheaters();
});

// Click ngày
$('.day').click(function(e) {
    e.preventDefault();

    $('.day').removeClass('active');
    $(this).addClass('active');

    getShowtimes();
});

// Lấy rạp chiếu phim
function getTheaters() {
    var city = $('#positionModal .modal-body a.active').text();
    var cinema = $('.cinema.active').attr('cinema-id');

    $.ajax({
        url: "/showtimes",
        data: {
            action: "getTheaters",
            city: city,
            cinema: cinema,
        },
        type: "post",
        success: function (response) {
            $('.box-body .theater').html(response);
            getShowtimes();
            getTheaterInfo();
            theaterEvent();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

// Click rạp chiếu phim
function theaterEvent() {
    $('.theater a').click(function(e) {
        e.preventDefault();
        $('.theater a').removeClass('active');
        $(this).addClass('active');

        getTheaterInfo();
        getShowtimes();
    })
}

// Lấy thông tin rạp phim
function getTheaterInfo() {
    var id = $('.theater a.active').attr('theater-id'); 
    
    $.ajax({
        url: "/showtimes",
        data: {
            action: "getTheaterInfo",
            theater: id,
        },
        type: "post",
        success: function (response) {
            $('.address').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

// Lấy lịch chiếu
function getShowtimes() {
    var id = $('.theater a.active').attr('theater-id');
    var date = $('.day.active').attr('date');

    $.ajax({
        url: "/showtimes",
        data: {
            action: "getShowtimes",
            theater: id,
            date: date,
        },
        type: "post",
        success: function (response) {
            $('.showtimes-movie .list').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

$(document).ready(function() {
    getShowtimes();
    theaterEvent();
})
$('#positionModal .modal-body a').click(function(e) {
    e.preventDefault();
    $('#positionModal .modal-body a').removeClass('active');
    $(this).addClass('active');

    var city = $(this).text();
    $('.btn[data-bs-target="#positionModal"] .city').text(city);
    
    getTheaters();
    
    setTimeout(() => {
        getTheaterInfo();
        getShowtimes();
    }, 500);
});

$('.cinema').click(function(e) {
    e.preventDefault();

    $('.cinema').removeClass('active');
    $(this).addClass('active');

    getTheaters();
    
    setTimeout(() => {
        getTheaterInfo();
        getShowtimes();
    }, 500);
});

$('.day').click(function(e) {
    e.preventDefault();

    $('.day').removeClass('active');
    $(this).addClass('active');

    getShowtimes();
});

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
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

function theaterEvent(theater, e) {
    e.preventDefault();

    $('.theater a').removeClass('active');
    $(theater).addClass('active');

    getTheaterInfo();
    getShowtimes();
}

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

getShowtimes();
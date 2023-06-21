// Click Địa điểm
function cityClick(e) {
    e.preventDefault();
    $('#positionModal .modal-body a').removeClass('active');
    $(this).addClass('active');

    var city = $(this).text();
    $('.btn[data-bs-target="#positionModal"] .city').text(city);
    
    getTheaters();
}

// Click Cinema
$('.cinema').click(function (e) {
    e.preventDefault();

    $('.cinema').removeClass('active');
    $(this).addClass('active');

    getShowtimes();
});

// Click ngày
$('.day').click(function (e) {
    e.preventDefault();

    $('.day').removeClass('active');
    $(this).addClass('active');

    getShowtimes();
});

function getTheaters() {
    getShowtimes()
};

function getShowtimes() {
    var url = new URL(window.location.href);
    var searchParams = new URLSearchParams(url.search);

    var movieid = searchParams.get('id');
    var city = $('#positionModal .modal-body a.active').text();
    var cinema = $('.cinema.active').attr('cinema-id');
    var date = $('.day.active').attr('date');

    $.ajax({
        url: url.pathname,
        data: {
            action: "getShowtimes",
            movieId: movieid,
            city: city,
            cinema: cinema,
            date: date,
        },
        type: "post",
        success: function (response) {
            $('#showtimes-cinema').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

$('#moreReviews').click(function(e) {
    e.preventDefault();

    var url = new URL(window.location.href);
    var searchParams = new URLSearchParams(url.search);

    var movieid = searchParams.get('id');
    var review = $('.review').length;

    $.ajax({
        url: "/movie",
        data: {
            action: "getMoreReviews",
            movieId: movieid,
            count: review,
        },
        type: "post",
        success: function (response) {
            $('.reviews').append(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
})

$(document).ready(function () {
    fetchCities();
})
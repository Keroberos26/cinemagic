function chooseMovie(movie) {   
    $('.custom-option').removeClass('selected');
    $(movie).addClass('selected');

    var id = $(movie).attr('movie-id');
    var title = $(movie).find('.title').text();

    $('button[data-bs-target="#movieModal"]').text(title);
    $('input[name="sltMovie"]').val(id);
}

$('#searchMovie').on('input', function(e) {
    e.preventDefault();

    findMovie();
})

$('#filStatus').change(function (e) {
    e.preventDefault();

    findMovie();
})

function findMovie() {
    var url = new URL(window.location.href);
    var searchParams = new URLSearchParams(url.search);
    
    var showid = searchParams.get('id');
    var title = $('#searchMovie').val().toLowerCase();
    var status = $('#filStatus').val();

    $.ajax({
        url: "/cinema/showtime-form",
        data: {
            id: showid,
            action: "search",
            title: title,
            status: status,
        },
        type: "post",
        success: function (response) {
            $('#movieModal .list').html(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}
function chooseMovie() {   
    $('.custom-option').click(function(e) {
        e.preventDefault();

        $('.custom-option').removeClass('selected');
        $(this).addClass('selected');
    
        var id = $(this).attr('movie-id');
        var title = $(this).find('.title').text();
    
        $('button[data-bs-target="#movieModal"]').text(title);
        $('input[name="sltMovie"]').val(id);
    })
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
            chooseMovie();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

$(document).ready(function() {
    chooseMovie();
})
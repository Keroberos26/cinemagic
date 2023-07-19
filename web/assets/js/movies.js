$(".movie-filter .dropdown-item").click(function (e) {
    e.preventDefault();

    var value = $(this).val();
    var text = $(this).text();

    $(this)
        .closest(".dropdown-menu")
        .find(".dropdown-item")
        .removeClass("active");
    $(this).addClass("active");
    var btn = $(this).closest(".dropdown").children(".dropdown-toggle");
    btn.text(text);
    btn.val(value);

    getMovies(0);
});

$('.movie-filter input[type="search"]').on("input", function (e) {
    e.preventDefault();

    getMovies(0);
});

function pageClick() {
    $(".pagination .page-link").click(function (e) {
        e.preventDefault();

        var page = $(this).val();
        getMovies(page);
    });
}

function getMovies(page) {
    var genre = $("#sltGenre").val();
    var country = $("#sltCountry").val();
    var year = $("#sltYear").val();
    var search = $("#txtSearch").val().toLowerCase();

    var url = new URL(window.location.href);

    $(".movie-list").css('position', 'relative');
    $(".movie-list").append(spinner);

    $.ajax({
        url: url.pathname,
        data: {
            action: "getMovies",
            genre: genre,
            country: country,
            year: year,
            search: search,
            page: page,
        },
        type: "post",
        success: function (response) {
            const resp = response.split('-----');
            $(".movie-list").html(resp[0]);
            $(".paging").html(resp[1]);
            pageClick();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        },
    });
}

$(document).ready(function () {
    getMovies(0);
});

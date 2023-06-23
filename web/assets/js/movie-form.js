function trailerEmbed(link) {
    var id  = ''
    if (link.includes('watch')) {
        var url = new URL(link);
        id = url.searchParams.get('v');
    } else {
        if (link.includes('youtu.be')) {
            id = link.substring(17, 28);
        }
    }
    if (id === '')
        return '';
    else {
        return 'https://www.youtube.com/embed/' + id;
    }
}

$('#trailer').change(function() {
    var iframe = $(this).next().find('iframe');
    var link = $(this).val();

    
    iframe.attr('src', trailerEmbed(link));
    console.clear();
})

$('#genresModal input[type="checkbox"]').change(function(e) {
    var genres = $('#genresModal input[type="checkbox"]:checked').map(function() {
        return $(this).next().text();
    }).get().join(', ');
    
    $('#genre').val(genres);
})

$('.trailer-wrapper iframe').attr('src', trailerEmbed($('#trailer').val()));
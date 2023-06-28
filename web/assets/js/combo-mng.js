$('.update').click(function() {   
    const row = $(this).closest('tr');
    const id = row.attr('combo-id');
    const name = row.find('.name').text();
    const img = row.find('img').attr('src');
    const price = row.find('.price').attr('price');
    const desc = row.find('.mini-text').text();

    const modal = $('#comboModal .modal-dialog');
    modal.find('#comboModalLabel').text('Chỉnh sửa combo');
    modal.find('input[type="hidden"]').attr('value', id);
    modal.find('img').attr('src', img);
    modal.find('#name').attr('value', name);
    modal.find('#price').attr('value', price);
    modal.find('#description').text(desc);
    modal.find('#save').attr('value', 'update');
    modal.find('#delete').removeClass('d-none');
})

$('#addCombo').click(function() {
    const modal = $('#comboModal .modal-dialog');
    modal.find('#comboModalLabel').text('Thêm combo');
    modal.find('img').attr('src', '/assets/img/popcorn.png');
    modal.find('#name').attr('value', '');
    modal.find('#price').attr('value', '');
    modal.find('#description').text('');
    modal.find('#save').attr('value', 'add');
    if (!modal.find('#delete').hasClass('d-none'))
        modal.find('#delete').addClass('d-none');
});

$('#delete').click(function(e) {
    if (confirm('Bạn có chắc chắn xóa combo này không?')) {
        $('#comboModal form').submit();
    } else {
        e.preventDefault();
    }
})
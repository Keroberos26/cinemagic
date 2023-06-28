$('#addCombo').click(function() {
    const modal = $('#partnerModal .modal-dialog');
    modal.find('#partnerModalLabel').text('Thêm đối tác');
    modal.find('img').attr('src', '/assets/img/mascot.png');
    modal.find('#name').attr('value', '');
    modal.find('#account').find('option').prop('selected', false);
    modal.find('#description').text('');
    modal.find('#save').attr('value', 'add');
    if (!modal.find('#delete').hasClass('d-none'))
        modal.find('#delete').addClass('d-none');
});

$('.update').click(function() {   
    const id = $(this).attr('cinema-id');
    const name = $(this).find('h4').text();
    const img = $(this).find('.bg-img').css('background-image').replace('url("', '').replace('")', '');
    const acc = $(this).attr('acc-id');
    const desc = $(this).find('.mini-text').text();

    console.log(id, name, img, acc, desc)

    const modal = $('#partnerModal .modal-dialog');
    modal.find('#partnerModalLabel').text('Chỉnh sửa đối tác');
    modal.find('input[type="hidden"]').attr('value', id);
    modal.find('img').attr('src', img);
    modal.find('#name').attr('value', name);
    modal.find('#account').find('option').prop('selected', false);
    modal.find('#account').find('option').filter(function() {
        return acc === $(this).val();
    }).prop('selected', true);
    modal.find('#description').text(desc);
    modal.find('#save').attr('value', 'update');
    modal.find('#delete').removeClass('d-none');
})

$('#delete').click(function(e) {
    e.preventDefault();
    if (confirm('Bạn có chắc chắn xóa đối tác này không?')) {
        $('#partnerModal form').submit();
    }
})
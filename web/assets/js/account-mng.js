$('#add').click(function() {
    const modal = $('#accModal .modal-dialog');
    modal.find('#accModalLabel').text('Thêm tài khoản');
    modal.find('#email').attr('value', '');
    modal.find('#password').attr('value', '');
    modal.find('#password').attr('disabled', false);
    modal.find('#name').attr('value', '');
    modal.find('#phone').attr('value', '');
    modal.find('#radUser').prop('checked', true);
    modal.find('#save').attr('value', 'add');
    if (!modal.find('#delete').hasClass('d-none'))
        modal.find('#delete').addClass('d-none');
});

$('.update').click(function() {   
    const row = $(this).closest('tr');
    const id = row.attr('acc-id');
    const email = row.find('td.email').text();
    const name = row.find('td.name').text();
    const phone = row.find('td.phone').text();
    const role = row.find('td.role span').attr('role');

    const modal = $('#accModal .modal-dialog');
    modal.find('#accModalLabel').text('Chỉnh sửa tài khoản');
    modal.find('input[type="hidden"]').attr('value', id);
    modal.find('#email').attr({'value': email, 'disabled': true});
    modal.find('#password').attr('disabled', true);
    modal.find('#name').attr('value', name);
    modal.find('#phone').attr('value', phone);
    modal.find('input[name="role"]').each(function() {
        if ($(this).val() == role) {
            $(this).prop('checked', true);
        } else {
            $(this).prop('checked', false);
        }
    })
    modal.find('#save').attr('value', 'update');
    modal.find('#delete').removeClass('d-none');
})

$('#delete').click(function(e) {
    if (confirm('Bạn có chắc chắn xóa tài khoản này không?')) {
        $('#comboModal form').submit();
    } else {
        e.preventDefault();
    }
})
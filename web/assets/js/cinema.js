document.querySelector('.sidebar-button').addEventListener('click', function() {
    var sidebar =  document.querySelector('.sidebar');
    var main = document.querySelector('.main-content');
    sidebar.classList.toggle('show');
    main.classList.toggle('show');
})
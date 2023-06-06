window.onload = function () {
    var preloader = document.querySelector('.preloader');
    if (preloader != null) {
        preloader.style.display = 'none';
    }
    editPlainText();
}

var swiper = new Swiper(".in-theaters .swiper", {
    slidesPerView: 2,
    spaceBetween: 10,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    breakpoints: {
        768: {
            slidesPerView: 3,
            spaceBetween: 20,
        },
        1024: {
            slidesPerView: 4,
            spaceBetween: 30,
        },
        1400: {
            slidesPerView: 5,
            spaceBetween: 40,
        }
    },
});

var swiper = new Swiper(".coming-soon .swiper", {
    slidesPerView: 2,
    spaceBetween: 10,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    breakpoints: {
        768: {
            slidesPerView: 3,
            spaceBetween: 20,
        },
        1024: {
            slidesPerView: 4,
            spaceBetween: 30,
        },
        1400: {
            slidesPerView: 5,
            spaceBetween: 40,
        }
    },
});

var swiper = new Swiper(".showtimes .box-header .swiper", {
    slidesPerView: "auto",
    spaceBetween: 20,
    freeMode: true,
    navigation: {
        nextEl: ".showtimes .box-header .swiper-button-next",
        prevEl: ".showtimes .box-header .swiper-button-prev",
    },
});

var swiper = new Swiper(".showtimes .box-body .swiper", {
    slidesPerView: "auto",
    spaceBetween: 20,
    freeMode: true,
    navigation: {
        nextEl: ".showtimes .box-body .swiper-button-next",
        prevEl: ".showtimes .box-body .swiper-button-prev",
    },
});

// Validate Form
(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })
})()

const confirmPass = document.getElementById('confirm');
if (confirmPass != null) {
    confirmPass.addEventListener('input', function () {
        var pass = document.getElementById('password').value;
        if (this.value !== pass) {
            this.classList.add('is-invalid');
        }
        else {
            this.classList.remove('is-invalid');
        }
    })
}

const sidebarBtn = document.querySelector('.sidebar-button');
if (sidebarBtn != null) {
    sidebarBtn.addEventListener('click', function () {
        var sidebar = document.querySelector('.sidebar');
        var main = document.querySelector('.main-content');
        sidebar.classList.toggle('hide');
        main.classList.toggle('show');
    })
}

function editPlainText() {
    var canEdit = document.querySelectorAll('.can-edit');
    if (canEdit != null) {
        canEdit.forEach(e => {
            e.addEventListener('click', function() {
                e.removeAttribute('readonly');
                e.classList.remove('form-control-plaintext');
                e.classList.add('form-control');
            })
    
            e.addEventListener('keypress', function(event) {
                if (event.keyCode === 13) { // Kiểm tra nút Enter
                    e.setAttribute('readonly', true); // Gán lại thuộc tính readonly
                    e.classList.add('form-control-plaintext');
                    e.classList.remove('form-control');
                    // sendData(e.value); // ! Gửi dữ liệu vào server Ajax
                }
            })
        })
    }
}

$('button[value="delete"]').click(function() {
    alert("Bạn đã chắc chắn xóa đối tượng này?");
})

// Insert Room Row
$(function () {

    // Start counting from the third row
    var counter = 11;

    $("#insertRow").on("click", function (event) {
        event.preventDefault();

        var newRow = $("<tr>");
        var cols = '';

        // Table columns
        cols += '<td>' + counter + '</td>';
        cols += '<td><input type="text" class="form-control can-edit" placeholder="Tên phòng"></td>';
        cols += '<td class="text-center"><a href="#" class="btn btn-primary"><span class="icon"><i class="fa-solid fa-couch"></i></span></a></td>';
        cols += '<td class="text-center"><a href="#" class="btn btn-danger" id="deleteRow"><span class="icon"><i class="fa-regular fa-trash-can"></i></span></a></td>';

        // Insert the columns inside a row
        newRow.append(cols);

        // Insert the row inside a table
        $("table").append(newRow);

        editPlainText();

        // Increase counter after each row insertion
        counter++;
    });

    // Remove row when delete btn is clicked
    $("table").on("click", "#deleteRow", function (event) {
        $(this).closest("tr").remove();
        counter -= 1
    });
});

// * Enable tooltips
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

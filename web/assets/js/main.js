window.onload = function () {
    document.querySelector('.preloader').style.display = 'none';
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

document.getElementById('confirm').addEventListener('input', function () {
    var pass = document.getElementById('password').value;
    if (this.value !== pass) {
        this.classList.add('is-invalid');
    }
    else {
        this.classList.remove('is-invalid');
    }
})
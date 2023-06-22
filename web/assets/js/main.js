window.onload = function () {
    var preloader = document.querySelector('.preloader');
    if (preloader != null) {
        preloader.style.display = 'none';
    }
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

// * Enable tooltips
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

// ----------------------------------------
// Function to fetch cities
function fetchCitiesShowtimes() {
    fetch("https://provinces.open-api.vn/api/p/")
        .then(response => response.json())
        .then(data => {
            const city = $('.btn[data-bs-target="#positionModal"] .city').text();
            
            data.forEach(item => {
                const li = $('<li>');
                const link = $('<a>').attr({
                    'href': '#',
                    'data-bs-dismiss': 'modal',
                }).text(item.name).click(cityClick);
                
                if (city === item.name) {
                    link.addClass('active');
                }
                
                li.append(link);
                $('#positionModal .modal-body ul').append(li);
            })

            getTheaters();
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

//Image
$('.imgFile-form input[type="file"]').change(function() {
    const chosenFile = this.files[0];
    const img = $('.imgFile-form img');


    if (chosenFile) {
        const reader = new FileReader();

        reader.addEventListener('load', function() {
            img.attr('src', reader.result);
        })
        reader.readAsDataURL(chosenFile);
    }
})

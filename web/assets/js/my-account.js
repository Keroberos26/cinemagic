const citySelect = document.getElementById("city");
const districtSelect = document.getElementById("district");
const wardSelect = document.getElementById("ward");

// Function to fetch cities
function fetchCities() {
    const city = citySelect.getAttribute("data-city");

    fetch("https://provinces.open-api.vn/api/p/")
            .then(response => response.json())
            .then(data => {
                // Clear existing options
                citySelect.innerHTML = "";

                // Create and append new options based on the fetched data
                data.forEach(item => {
                    const option = document.createElement("option");
                    option.value = item.name;
                    option.text = item.name;
                    option.alt = item.code;
                    if (city === item.name) {
                        option.setAttribute("selected", true);
                    }
                    citySelect.appendChild(option);
                });

                // Fetch districts based on the selected city when the page is loaded
                const selectedCityCode = citySelect.options[citySelect.selectedIndex].alt;
                fetchDistricts(selectedCityCode);
            })
            .catch(error => {
                console.error("Error:", error);
            });
}

// Function to fetch districts based on the selected city
function fetchDistricts(cityCode) {
    const district = districtSelect.getAttribute("data-district");
    fetch(`https://provinces.open-api.vn/api/p/${cityCode}?depth=2`)
            .then(response => response.json())
            .then(data => {
                // Clear existing options
                districtSelect.innerHTML = "";

                // Create and append new options based on the fetched data
                data.districts.forEach(item => {
                    const option = document.createElement("option");
                    option.value = item.name;
                    option.text = item.name;
                    option.alt = item.code;
                    if (district === item.name) {
                        option.setAttribute("selected", true);
                    }
                    districtSelect.appendChild(option);
                });

                // Fetch wards based on the selected district when the page is loaded
                const selectedDistrictCode = districtSelect.options[districtSelect.selectedIndex].alt;
                fetchWards(selectedDistrictCode);
            })
            .catch(error => {
                console.error("Error:", error);
            });
}

// Function to fetch wards based on the selected district
function fetchWards(districtCode) {
    const ward = wardSelect.getAttribute("data-ward");
    fetch(`https://provinces.open-api.vn/api/d/${districtCode}?depth=2`)
            .then(response => response.json())
            .then(data => {
                // Clear existing options
                wardSelect.innerHTML = "";

                // Create and append new options based on the fetched data
                data.wards.forEach(item => {
                    const option = document.createElement("option");
                    option.value = item.name;
                    option.text = item.name;
                    option.alt = item.code;
                    if (ward === item.name) {
                        option.setAttribute("selected", true);
                    }
                    wardSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error("Error:", error);
            });
}

// Event listener for city select change
citySelect.addEventListener("change", function () {
    const selectedCityCode = this.options[this.selectedIndex].alt;
    fetchDistricts(selectedCityCode);
});

// Event listener for district select change
districtSelect.addEventListener("change", function () {
    const selectedDistrictCode = this.options[this.selectedIndex].alt;
    fetchWards(selectedDistrictCode);
});


// Fetch cities when the page is loaded
fetchCities();

function updateInfo() {
    const name = document.getElementById("name");
    const city = document.getElementById("city");
    const phone = document.getElementById("phone");
    const file = document.getElementById("avatar");
    var avatar = file.files[0];
    var formData = new FormData();
    formData.append("file", avatar);

    $.ajax({
        url: "/my-account",
        data: {
            action: 'updateInfo',
            txtName: name.value,
            sltCity: city.value,
            txtPhone: phone.value,
            fileAvt: formData,
        },
        traditional: true,
        encType: "multipart/form-data",
        processData: false,
        contentType: false,
        type: "post",
        success: function (response) {
            console.log(response);
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}


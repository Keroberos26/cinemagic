
// Function to fetch cities
function fetchCities() {
    fetch("https://provinces.open-api.vn/api/p/")
        .then(response => response.json())
        .then(data => {
            const positionModal = document.getElementById("positionModal");
            const ul = positionModal.querySelector("ul");

            // Clear existing options
            ul.innerHTML = "";

            data.forEach(item => {
                // Create a new li element
                const li = document.createElement("li");
                
                // Create a new a element
                const link = document.createElement("a");
                link.href = "#";
                link.textContent = item.name;

                // Append the link to the li element
                li.appendChild(link);

                // Append the li element to the ul
                ul.appendChild(li);
            });
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

// Fetch cities when the page is loaded
fetchCities();

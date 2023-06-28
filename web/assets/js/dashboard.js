const ctx = document.getElementById("revenueChart").getContext('2d');

var lineChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['1', '2', '3', '4', '5'],
            datasets: [{
                label: 'Doanh thu (VNĐ)',
                data: [1, 2, 4, 2, 3],
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
// }

function chartBy() {
    var url = new URL(window.location.href);
    var chart = $("#chartBy").val();

    $.ajax({
        url: url.pathname,
        data: {
            chart: chart,
        },
        type: "post",
        success: function (response) {
            var { labels, data } = JSON.parse(response);
            lineChart.data.labels = labels;
            lineChart.data.datasets[0].data = data;
            lineChart.update();
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        },
    });
}

$("#chartBy").change(chartBy);
chartBy();

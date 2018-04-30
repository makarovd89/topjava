var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function updateTable() {
    $.get(ajaxUrl,
        {
            startDate: $("[name='startDate']").val(),
            startTime: $("[name='startTime']").val(),
            endDate: $("[name='endDate']").val(),
            endTime: $("[name='endTime']").val()
        },
        function (data) {
            console.debug(data);
            console.debug(ajaxUrl);
            datatableApi.clear().rows.add(data).draw();
        });
}

function resetFilter() {
    $("[name='startDate']").val("");
    $("[name='startTime']").val("");
    $("[name='endDate']").val("");
    $("[name='endTime']").val("");
}

var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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
    $(".enabled").click(function () {
        toggle($(this).prop("checked"), $(this).closest('tr').attr("id"));
    });
});

function toggle(is_enabled, id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "POST",
        data: "enabled=" + is_enabled,
        success: function () {
            updateTable();
            successNoty("Updated");
        }
    });
}
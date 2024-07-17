/**
 * 
 */$(document).ready(function() {
    function toggleEditMode() {
        var editSection = $('#editSection');
        var viewSection = $('#viewSection');

        if (editSection.css('display') === 'none') {
            editSection.css('display', 'block');
            viewSection.css('display', 'none');
        } else {
            editSection.css('display', 'none');
            viewSection.css('display', 'block');
        }
    }

    $('.edit-button').on('click', function() {
        toggleEditMode();
    });

    $('.cancel-button').on('click', function() {
        toggleEditMode();
    });
});

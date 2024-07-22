$(document).ready(function() {
    var csrfToken = $('meta[name="_csrf"]').attr('content');
    var csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    function handleAjaxError(xhr, error) {
        console.error('AJAX 오류:', error);
        console.error('응답 상태 코드:', xhr.status);
        console.error('응답 본문:', xhr.responseText);
        alert('요청 처리에 실패했습니다.');
    }

    function updateEvent(event) {
        $.ajax({
            url: `/calendar/${event.id}`,
            method: 'PUT',
            headers: { [csrfHeader]: csrfToken },
            contentType: 'application/json',
            data: JSON.stringify(event),
            success: function() { alert('일정이 수정되었습니다.'); },
            error: handleAjaxError
        });
    }

    $('#calendar').fullCalendar({
        editable: true,
        selectable: true,
        locale: 'ko',
        select: function(start, end) {
            $('#event-modal').show();
            $('#event-start').val(moment(start).format('YYYY-MM-DD'));
            $('#event-end').val(moment(end).format('YYYY-MM-DD'));
            $('#event-title').val('');
            $('#event-description').val('');

            $('#save-event').off('click').on('click', function() {
                var title = $('#event-title').val();
                var description = $('#event-description').val();
                if (title && description) {
                    var newEvent = {
                        title: title,
                        description: description,
                        start: start.format(),
                        end: end.format()
                    };
                    $.ajax({
                        url: '/calendar',
                        method: 'POST',
                        headers: { [csrfHeader]: csrfToken },
                        contentType: 'application/json',
                        data: JSON.stringify(newEvent),
                        success: function(event) {
                            newEvent.id = event.id;
                            $('#calendar').fullCalendar('renderEvent', newEvent, true);
                            $('#event-modal').hide();
                            clearModalFields();
                        },
                        error: handleAjaxError
                    });
                } else {
                    alert('모든 필드를 입력해주세요.');
                }
            });
        },
        eventClick: function(event) {
            $('#event-modal').show();
            $('#event-title').val(event.title);
            $('#event-description').val(event.description);
            $('#event-start').val(moment(event.start).format('YYYY-MM-DD'));
            $('#event-end').val(moment(event.end).format('YYYY-MM-DD'));

            $('#save-event').off('click').on('click', function() {
                if (confirm('수정하시겠습니까?')) {
                    event.title = $('#event-title').val();
                    event.description = $('#event-description').val();
                    event.start = $('#event-start').val();
                    event.end = $('#event-end').val();
                    updateEvent(event);
                    $('#event-modal').hide();
                    clearModalFields();
                }
            });

            $('#delete-event').off('click').on('click', function() {
                if (confirm('삭제하시겠습니까?')) {
                    $.ajax({
                        url: `/calendar/${event.id}`,
                        method: 'DELETE',
                        headers: { [csrfHeader]: csrfToken },
                        success: function() {
                            $('#calendar').fullCalendar('removeEvents', event.id);
                            $('#event-modal').hide();
                            clearModalFields();
                        },
                        error: handleAjaxError
                    });
                }
            });
        },
        eventDrop: updateEvent,
        eventResize: updateEvent
    });

    $('#sidebar .sidebar-menu a').click(function(e) {
        e.preventDefault();
        window.location.href = $(this).attr('href');
    });

    $('.close').click(function() {
        $('#event-modal').hide();
        clearModalFields();
    });

    function clearModalFields() {
        $('#event-title').val('');
        $('#event-description').val('');
        $('#event-start').val('');
        $('#event-end').val('');
    }
});
$(document).ready(function() {
    // CSRF 토큰과 헤더 이름을 meta 태그에서 가져옴
    var csrfToken = $('meta[name="_csrf"]').attr('content');
    var csrfHeader = $('meta[name="_csrf_header"]').attr('content');

    // 캘린더 초기화
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
                        headers: {
                            [csrfHeader]: csrfToken // CSRF 헤더 추가
                        },
                        contentType: 'application/json',
                        data: JSON.stringify(newEvent),
                        success: function(event) {
                            newEvent.id = event.id; // 서버에서 생성된 ID 할당
                            $('#calendar').fullCalendar('renderEvent', newEvent, true);
                            $('#event-modal').hide();
                            clearModalFields();
                        },
                        error: function(xhr, status, error) {
                            console.error('AJAX 오류:', error);
                            console.error('응답 상태 코드:', xhr.status);
                            console.error('응답 본문:', xhr.responseText);
                            alert('일정 저장에 실패했습니다.');
                        }
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
                    $.ajax({
                        url: `/calendar/${event.id}`,
                        method: 'PUT',
                        headers: {
                            [csrfHeader]: csrfToken // CSRF 헤더 추가
                        },
                        contentType: 'application/json',
                        data: JSON.stringify(event),
                        success: function() {
                            $('#calendar').fullCalendar('updateEvent', event);
                            $('#event-modal').hide();
                            clearModalFields();
                        },
                        error: function(xhr, status, error) {
                            console.error('AJAX 오류:', error);
                            console.error('응답 상태 코드:', xhr.status);
                            console.error('응답 본문:', xhr.responseText);
                            alert('일정 수정에 실패했습니다.');
                        }
                    });
                }
            });

            $('#delete-event').off('click').on('click', function() {
                if (confirm('삭제하시겠습니까?')) {
                    $.ajax({
                        url: `/calendar/${event.id}`,
                        method: 'DELETE',
                        headers: {
                            [csrfHeader]: csrfToken // CSRF 헤더 추가
                        },
                        success: function() {
                            $('#calendar').fullCalendar('removeEvents', event.id);
                            $('#event-modal').hide();
                            clearModalFields();
                        },
                        error: function(xhr, status, error) {
                            console.error('AJAX 오류:', error);
                            console.error('응답 상태 코드:', xhr.status);
                            console.error('응답 본문:', xhr.responseText);
                            alert('일정 삭제에 실패했습니다.');
                        }
                    });
                }
            });
        },
        eventDrop: function(event) {
            $.ajax({
                url: `/calendar/${event.id}`,
                method: 'PUT',
                headers: {
                    [csrfHeader]: csrfToken // CSRF 헤더 추가
                },
                contentType: 'application/json',
                data: JSON.stringify(event),
                success: function() {
                    alert('일정이 수정되었습니다.');
                },
                error: function(xhr, status, error) {
                    console.error('AJAX 오류:', error);
                    console.error('응답 상태 코드:', xhr.status);
                    console.error('응답 본문:', xhr.responseText);
                    alert('일정 수정에 실패했습니다.');
                }
            });
        },
        eventResize: function(event) {
            $.ajax({
                url: `/calendar/${event.id}`,
                method: 'PUT',
                headers: {
                    [csrfHeader]: csrfToken // CSRF 헤더 추가
                },
                contentType: 'application/json',
                data: JSON.stringify(event),
                success: function() {
                    alert('일정이 수정되었습니다.');
                },
                error: function(xhr, status, error) {
                    console.error('AJAX 오류:', error);
                    console.error('응답 상태 코드:', xhr.status);
                    console.error('응답 본문:', xhr.responseText);
                    alert('일정 수정에 실패했습니다.');
                }
            });
        }
    });

    $('#sidebar .sidebar-menu a').click(function(e) {
        e.preventDefault();
        var href = $(this).attr('href');
        window.location.href = href;
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

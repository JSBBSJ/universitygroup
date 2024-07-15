$(document).ready(function() {
    // 로컬 스토리지에서 저장된 일정을 불러오기
    var savedEvents = JSON.parse(localStorage.getItem('events')) || [];

    // 캘린더 초기화
    $('#calendar').fullCalendar({
        editable: true, // 일정을 드래그하여 수정 가능
        selectable: true, // 날짜 선택 가능
        locale: 'ko', // 한국어 로케일 설정
        events: savedEvents, // 로드된 일정 추가
        select: function(start, end) {
            // 날짜를 선택하면 모달 창 표시
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
                        start: start,
                        end: end
                    };
                    $('#calendar').fullCalendar('renderEvent', newEvent, true);
                    saveEvents();
                    $('#event-modal').hide();
                    clearModalFields();
                } else {
                    alert('모든 필드를 입력해주세요.');
                }
            });
        },
        eventClick: function(event) {
            // 일정을 클릭하면 수정 모달 창 표시
            $('#event-modal').show();
            $('#event-title').val(event.title);
            $('#event-description').val(event.description);
            $('#event-start').val(moment(event.start).format('YYYY-MM-DD'));
            $('#event-end').val(moment(event.end).format('YYYY-MM-DD'));

            // 저장 버튼 클릭 핸들러 설정
            $('#save-event').off('click').on('click', function() {
                if (confirm('수정하시겠습니까?')) {
                    event.title = $('#event-title').val();
                    event.description = $('#event-description').val();
                    event.start = $('#event-start').val();
                    event.end = $('#event-end').val();
                    $('#calendar').fullCalendar('updateEvent', event);
                    saveEvents();
                    $('#event-modal').hide();
                    clearModalFields();
                }
            });

            // 삭제 버튼 클릭 핸들러 설정
            $('#delete-event').off('click').on('click', function() {
                if (confirm('삭제하시겠습니까?')) {
                    $('#calendar').fullCalendar('removeEvents', event._id);
                    saveEvents();
                    $('#event-modal').hide();
                    clearModalFields();
                }
            });
        },
        eventDrop: function(event) {
            // 일정을 드래그하여 이동하면 수정됨을 알림
            alert('일정이 수정되었습니다.');
            saveEvents();
        },
        eventResize: function(event) {
            // 일정을 크기 조절하면 수정됨을 알림
            alert('일정이 수정되었습니다.');
            saveEvents();
        }
    });

    // 사이드바 메뉴 클릭 시 페이지 이동
    $('#sidebar .sidebar-menu a').click(function(e) {
        e.preventDefault();
        var href = $(this).attr('href');
        window.location.href = href;
    });

    // 모달 창 닫기
    $('.close').click(function() {
        $('#event-modal').hide();
        clearModalFields();
    });

    // 로컬 스토리지에 일정을 저장하는 함수
    function saveEvents() {
        var events = $('#calendar').fullCalendar('clientEvents');
        var eventsToSave = events.map(function(event) {
            return {
                title: event.title,
                description: event.description,
                start: event.start.format(),
                end: event.end ? event.end.format() : null
            };
        });
        localStorage.setItem('events', JSON.stringify(eventsToSave));
    }

    // 모달 필드를 초기화하는 함수
    function clearModalFields() {
        $('#event-title').val('');
        $('#event-description').val('');
        $('#event-start').val('');
        $('#event-end').val('');
    }
});

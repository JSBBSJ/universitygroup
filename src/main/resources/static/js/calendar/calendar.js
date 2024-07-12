$(document).ready(function() {
	let events = [];

	function saveEventsToLocalStorage() {
		localStorage.setItem('events', JSON.stringify(events));
	}

	function loadEventsFromLocalStorage() {
		const storedEvents = localStorage.getItem('events');
		if (storedEvents) {
			events = JSON.parse(storedEvents);
		}
	}

	loadEventsFromLocalStorage();

	function fetchEvents(callback) {
		callback(events);
	}

	function saveEvent(event, callback) {
		events.push(event);
		saveEventsToLocalStorage();
		callback(event);
	}

	function updateEvent(event, callback) {
		const index = events.findIndex(e => e.id === event.id);
		if (index !== -1) {
			events[index] = event;
			saveEventsToLocalStorage();
			callback(event);
		}
	}

	function deleteEvent(eventId, callback) {
		events = events.filter(e => e.id !== eventId);
		saveEventsToLocalStorage();
		callback();
	}

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: true,
		events: function(start, end, timezone, callback) {
			fetchEvents(callback);
		},
		dayClick: function(date) {
			$('#event-modal').show();
			$('#event-start').val(date.format('YYYY-MM-DD'));
			$('#event-end').val(date.format('YYYY-MM-DD'));
		},
		eventDrop: function(event) {
			updateEvent({
				id: event.id,
				title: event.title,
				start: event.start.format(),
				end: event.end ? event.end.format() : event.start.format()
			}, function() {
				$('#calendar').fullCalendar('refetchEvents');
			});
		},
		eventResize: function(event) {
			updateEvent({
				id: event.id,
				title: event.title,
				start: event.start.format(),
				end: event.end ? event.end.format() : event.start.format()
			}, function() {
				$('#calendar').fullCalendar('refetchEvents');
			});
		},
		eventClick: function(event) {
			if (confirm('Delete this event?')) {
				deleteEvent(event.id, function() {
					$('#calendar').fullCalendar('refetchEvents');
				});
			}
		}
	});

	$('#save-event').on('click', function() {
		const title = $('#event-title').val();
		const start = $('#event-start').val();
		const end = $('#event-end').val();
		const id = new Date().getTime().toString(); // Generate a simple unique ID

		saveEvent({
			id: id,
			title: title,
			start: start,
			end: end
		}, function() {
			$('#calendar').fullCalendar('refetchEvents');
			$('#event-modal').hide();
			$('#event-title').val('');
			$('#event-start').val('');
			$('#event-end').val('');
		});
	});
});

function formlists(id){//worklist에서 id를 받아와서



	

	$.ajax({

		url:'http://localhost:8080/findDoc', //컨트롤러 매핑 주소 

		data:  { data: id }, //{ data: id }에서 data는 변수명이고, id는 변수의 값

		type: 'GET',  //get으로 저 url 가져오겠다

		dataType:"html", //가져올 타입은 html

		success : function(result){ //성공하면 결과값으로 

			

			$('#form-form').append(result); //얘를 보여주고

			$('#form-h').hide(); //가려라

			$('#f-form-list').hide(); //가려라

			

			

			

		}

	});		

	

}
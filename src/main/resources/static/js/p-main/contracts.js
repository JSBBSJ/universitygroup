function listup(id) { //코드가 중복되어 id로 가져와서 뿌리기

       var key = document.getElementById(id);

       // 현재 key 요소의 display 상태 확인

       var display = window.getComputedStyle(key).display;

       // 토글 기능 구현

       if (display === "none") {

 		key.style.display = "block"; // 보이기
		
          

       } else {

         key.style.display = "none"; // 숨기기

       }

   }
   
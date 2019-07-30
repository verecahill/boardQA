$(".answer-write input[type=sumbit]").click(addAnswer);
$("#task-add").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	console.log("click me");

	var queryString = $(".answer-write").serialize();
	console.log(queryString);

	var url = $(".answer-write").attr("action");
	console.log("url : " + url);

	$.ajax({
		type : "post",
		url : url,
		data : queryString,
		error : onError,
		success : onSuccess
	});
}

function onError() {
	console.log("error");
}

function onSuccess(data, status) {
	console.log(data);
	if(data.id == null){
		alert("Task 추가 오류");
		$(".answer-write textarea").val("");
		return;
	}
	console.log("data : " + data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userId,
			data.formattedCreateDate, data.contents, data.board.id, data.id, data.title);
	$("#task-list").prepend(template);
	$(".answer-write textarea").val("");
}


//$("a.link-delete-article").on('click', deleteAnswer);
$("body").on("click", "a.link-delete-article", deleteTask);

function deleteTask(e) {
	e.preventDefault();
	
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log(url);

	$.ajax({
		type : 'delete',
		url : url,
		dataType : 'json',
		error : function(xhr, status) {
			console.log("error");
		},
		success : function(data, status) {
			console.log(data);
			if (data.valid) {
				deleteBtn.closest(".card").remove();
			} else {
				alert(data.errorMessage);
			}
		}
	});
}

$("body").on("click", "#delete-board", deleteBoard);

function deleteBoard(e) {
	e.preventDefault();
	
	var deleteBtn = $(this);
	var url = deleteBtn.attr("href");
	console.log(url);

	$.ajax({
		type : 'delete',
		url : url,
		dataType : 'json',
		error : function(xhr, status) {
			alert("소유자만 삭제가 가능합니다.");
		},
		success : function(data, status) {
//			window.location.href = "/";
			console.log("delete success");
		}
	});
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};
$(".answer-write input[type=sumbit]").click(addAnswer);

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
	if(data.userId == null){
		alert("로그인 해야 합니다.");
		$(".answer-write textarea").val("");
		return;
	}
	console.log("data : " + data);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(data.writer.userId,
			data.formattedCreateDate, data.contents, data.question.id, data.id);
	$(".qna-comment-slipp-articles").prepend(template);
	$(".answer-write textarea").val("");
}


//$("a.link-delete-article").on('click', deleteAnswer);
$("body").on("click", "a.link-delete-article", deleteAnswer);

function deleteAnswer(e) {
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
				deleteBtn.closest("article").remove();
			} else {
				alert(data.errorMessage);
			}
		}
	});
}

String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d+)}/g, function(match, number) {
		return typeof args[number] != 'undefined' ? args[number] : match;
	});
};
'use strict';

// 画面ロード時の処理
jQuery(function($) {
	// 更新防炭押下時の処理
	$('#btn-signup').click(function (event) {
		removeValidResult();
		// ユーザー登録
		signupUser();
	})
});

/** ユーザー更新処理. */
function signupUser() {

  // フォームの値を取得
  // CSRFトークンも取得している
  var formData = $('#signup-form').serializeArray();
  
  console.log(formData);

  // ajax通信
  $.ajax({
    type : "post",
    cache : false,
    url : '/user/signup/rest',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	console.log(data);
	
	if (data.result === 0) {
	    // ajax成功時の処理
    	alert('ユーザーを登録しました');
    	// ユーザー一覧画面にリダイレクト
    	window.location.href = '/login';
	} else if (data.result === 90) {
		// 入力エラーあり
		$.each(data.errors, function(key, value) {
			// 直接DOM操作してエラー情報を出力
			reflectValidResult(key, value);
		});
		
		$('#password').val("");

	}


  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('ユーザー登録に失敗しました');
  }).always(function() {
    // 常に実行する処理
  });
}

/** バリデーション結果をクリア */
function removeValidResult() {
  $('.is-invalid').removeClass('is-invalid');
  $('.invalid-feedback').remove();
  $('.text-danger').remove();
}

/** バリデーション結果の反映 */
function reflectValidResult(key, value) {

  // エラーメッセージ追加
  if(key === 'gender') { // 性別の場合
    // CSS適用
    $('input[name=' + key + ']').addClass('is-invalid');
    // エラーメッセージ追加
    $('input[name=' + key + ']')
        .parent().parent()
        .append('<div class="text-danger">' + value + '</div>');

  } else { // 性別以外の場合
    // CSS適用
    $('input[id=' + key + ']').addClass('is-invalid');
    // エラーメッセージ追加
    $('input[id=' + key + ']')
        .after('<div class="invalid-feedback">' + value + '</div>');
  }
}
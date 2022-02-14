'use strict';

let table = null;

// 画面ロード時の処理
jQuery(function($) {
	
   initDataTables();
	
	// 更新防炭押下時の処理
	$('#btn-search').click(function (event) {
		searchUsers();
	})
});

/** ユーザー検索処理. */
function searchUsers() {

  // フォームの値を取得
  const formData = $('#user-search-form').serializeArray();
  const formDataNoCSRF = formData.filter((item) => item.name !== "_csrf");
  
  console.log(formData);
  console.log(formDataNoCSRF);
  
  // ajax通信
  $.ajax({
    type : "get",
    cache : false,
    url : '/user/get/list',
    data: formDataNoCSRF,
    dataType : 'json',
  }).done(function(data) {
	console.log(data);

	if (data.length === 0) {
		initDataTables();
		alert('検索結果無し');
	} else {
		createDataTables(data);		
	}

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax失敗時の処理
    alert('検索に失敗しました');
   initDataTables();
  }).always(function() {
    // 常に実行する処理
  });
}

function initDataTables() {
	createDataTables([]);
}

/** DataTables作成. */
function createDataTables(userData) {

  //既にDataTablesが作成されている場合
  if(table !== null) {
    // DataTables破棄
    table.destroy();
  }

  // DataTables作成
  table = $('#user-list-table').DataTable({
    // 日本語化
    language: {
      url: '/webjars/datatables-plugins/i18n/Japanese.json'
    },
    //表示データ
    data: userData,
    //データと列のマッピング
    columns: [
      { data: 'userId'}, // ユーザーID
      { data: 'userName'}, // ユーザー名
      {
        data: 'birthday', // 誕生日
        render: function ( data, type, row ) {
          var date = new Date(data);
          var year = date.getFullYear();
          var month = date.getMonth() + 1;
          var date = date.getDate();
          return year + '/' + month + '/' + date;
        }
      },
      { data: 'age'}, // 年齢
      {
        data: 'gender', // 性別
        render: function ( data, type, row ) {
          var gender = '';
          if(data === 1) {
            gender = '男性';
          } else {
            gender = '女性';
          }
          return gender;
        }
      },
      {
        data: 'userId', // 詳細画面のURL
        render: function ( data, type, row ) {
          var url = '<a href="/user/detail/' + data + '">詳細</a>';
          return url;
        }
      },
    ]
  });
}
$('#prefecture').on('focusout', function() {
    $('#result').text('通信中...');
    $.ajax({
        url:addressAPIOrigin + "/cityList",
        type:"GET",
        dataType:'json',
        data:{"prefectureName":$('#prefecture').val()},
        timeout: 5000
    })
    .done(function(data) {
        console.log(data);
        $('#result').html('');
        $('#result').append('<h3>市区町村一覧</h3>');

        // 市区町村情報の表示
        for (let i = 0; i < data.length; i++) {
            var htmlStr = `
                ${data[i]['cityName']}:${data[i]['cityNameKana']}<br>
            `
            $('#result').append(htmlStr);
        }

        // 入力した都道府県で該当データなしの場合
        if(!data.length) $('#result').append('<p>該当する都道府県が存在しません。</p>');
    })
    .fail(function(textStatus) {
        $('#result').html('市区町村情報の取得に失敗しました');
        console.log('非同期処理失敗:' + textStatus.statusText);
    })

})
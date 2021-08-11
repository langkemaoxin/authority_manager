
/**
 * 公共弹窗层
 * @param url
 * @param title
 */
function openLayer(url, title) {

    //取消异步模式，设置为同步模式
    $.ajaxSettings.async = false;

    $.get(url, function (res) {
        layer.open({
            type: 1
            , title: title
            , area: ['800px', '450px']
            , content: res
        })
    });

    $.ajaxSettings.async = true;
}

/**
 * 公共监听事件
 * @param filer
 * @param type
 */
function mySubmit(filer, type) {
    //监听submit事件时，使用 lay-filter ==> addSubmit
    layui.form.on('submit('+filer+')', function (data) {
        $.ajax({
            url: data.form.action
            , async: false
            , type: type
            , contentType: 'application/json;charset=utf-8'
            , data: JSON.stringify(data.field)
            , success: function (res) {

                if (res.code == 0) {
                    layer.closeAll();
                    query();
                } else {
                    layer.alert(res.msg);
                }
            }
        })

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
}

/**
 * 公共删除方法
 * @param url
 */
function myDelete(url) {
    $.ajax({
        url: url
        , async: false
        , type: 'DELETE'
        , success: function (res) {
            if (res.code == 0) {
                query();
            } else {
                layer.alert(res.msg);
            }
        }
    })
}


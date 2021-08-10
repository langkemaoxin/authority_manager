var table = layui.table;

//第一个实例
var tableIns = table.render({
    elem: '#customerList'
    , url: '/customer/list' //数据接口
    , page: true //开启分页
    , parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    }
    , cols: [[ //表头
        , {field: 'realName', title: '真实姓名'},
        , {field: 'sex', title: '性别'},
        , {field: 'age', title: '年龄'},
        , {field: 'phone', title: '手机号码'},
        , {field: 'createTime', title: '创建时间'},
        , {title: '操作', toolbar: '#barDemo'}
    ]]
});


function query() {

    //这里以搜索为例
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#realName").val()
            , phone: $("#phone").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });

}


function toAdd() {


    openLayer('/customer/toAdd', '新增客户')

    //渲染页面上的所有元素
    layui.form.render();

    mySubmit('addSubmit','POST')


}

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
 * 工具条事件，传入table的 lay-filter 作为标识
 *
 */
//工具条事件
table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let customerId = data.customerId;

    if(layEvent === 'detail'){ //查看

        //do somehing
    } else if(layEvent === 'del'){ //删除
        layer.confirm('真的删除行么', function(index){
            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
            layer.close(index);
            //向服务端发送删除指令
        });
    } else if(layEvent === 'edit'){ //编辑

        openLayer('/customer/toUpdate/'+customerId, '修改客户')

        //渲染页面上的所有元素
        layui.form.render();

        mySubmit('updateSubmit','PUT');

    }
});

layui.laydate.render({

    elem:'#createTimeRange'
    ,range:true
    //2020-11-12 - 2020-11-20
});


var table = layui.table;


//第一个实例
var tableIns = table.render({
    elem: '#accountList'
    , url: '/account/list' //数据接口
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
        , {field: 'username', title: '用户名'}
        , {field: 'realName', title: '真实姓名'}
        , {field: 'roleName', title: '角色名称'}
        , {field: 'sex', title: '性别'},
        , {field: 'createTime', title: '创建时间'},
        , {title: '操作', toolbar: '#barDemo'}
    ]]
});

function query() {

    //这里以搜索为例
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#realName").val()
            , email: $("#email").val()
            ,createTimeRange:$("createTimeRange").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });

}


function toAdd() {
    openLayer('/account/toAdd','新增用户');

    layui.form.render();

    mySubmit('addSubmit','POST')
}



/**
 * 工具条事件，传入table的 lay-filter 作为标识
 *
 */
//工具条事件
table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let accountId = data.accountId;

    if (layEvent === 'detail') { //查看
        openLayer('/account/toDetail/' + accountId, '账号详情')

        //渲染页面上的所有元素
        layui.form.render();

        //do somehing
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {

            layer.close(index);

            myDelete('/account/' + accountId);

        });
    } else if (layEvent === 'edit') { //编辑

        openLayer('/account/toUpdate/' + accountId, '编辑账号')

        //渲染页面上的所有元素
        layui.form.render();

        mySubmit('updateSubmit', 'PUT');

    }
});



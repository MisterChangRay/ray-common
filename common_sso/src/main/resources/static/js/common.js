/**
 * datagrid 分页函数
 */
function pagerFilter(res){
    var data = {total:0, rows:0};
    if(res && 0 == res.code) {
        data = {
            total: res.pageInfo.count,
            rows: res.data
        };
        var dg = $(this);
        var opts = dg.datagrid('options');
        var pager = dg.datagrid('getPager');
        pager.pagination({
            onSelectPage:function(pageNum, pageSize){
                opts.pageNumber = pageNum;
                opts.pageSize = pageSize;
                pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});
                var param = $.extend({}, opts.queryParams, {page:pageNum, pageSize:pageSize});
                dg.datagrid('reload', param);
            }
        });
        if (!data.originalRows){
            data.originalRows = (data.rows);
        }
        return data;
    } else if("object" == typeof res && res.total) {
        return res;
    }
}

function onBeforeLoad(param){
    console.log(param);
    param["page"] = param.page;
    param["pageSize"] = param.rows;
    delete param.rows;
}

/**
 * 模板替换 使用 {} 作为占位符
 * _tpl("test{},{}", 1, 2)
 * 替换结果 "test1,2"
 * @returns {*}
 * @private
 */
var strFormat = function(){
    var arg = arguments;
    var text = arg[0] + "";
    for(var i=1; i<arg.length; i++) {
        text = text.replace("{}", arg[i])
    }
    return text;
}
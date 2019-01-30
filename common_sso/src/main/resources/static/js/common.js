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
                var param = $.extend({}, opts.queryParams)
                dg.datagrid('load', param);
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
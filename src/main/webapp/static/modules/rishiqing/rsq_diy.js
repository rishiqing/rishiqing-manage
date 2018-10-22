/*	日事清项目相关js存放 */

layer.config({
    extend: '../../vendors/layer/extend/layer.ext.js'
});

/**
 * 充值页面弹窗
 */
function pay(title,url, gridId, infoId ,width,height){
    url=url.replace("{id}",infoId);
    width = '800px';
    height = '530px';
    if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
        width='auto';
        height='auto';
    }else{//如果是PC端，根据用户设置的width和height显示。

    }
    top.layer.open({
        type: 2,
        area: [width, height],
        title: title,
        maxmin: true, //开启最大化最小化按钮
        content: url ,
        btn: ['关闭'],
        cancel: function(index){
            //刷新表单
            refreshTable(gridId);
        }
    });
}


/**
 * 开通试用
 */
function rsq_try(formId){
    layer.confirm('确定要开通试用吗？',{
        btn: ['是','否'] //按钮
    }, function(){
        var res = $("#" + formId).submit();
        console.log(res);
    }, function(){

    });
}

/**
 * 清除所有input值
 */
function myReset() {
    $(":input[type='text']").each(function(){
        $(this).val("")
    });
}



//==============================
//=========查看公司详情===========
//=============================
/**
 * 查看公司详情页面
 */
function openCompany(title,url, gridId, infoId , tipMsg){
    url=url.replace("{id}",infoId);
    window.open(url);
    // width = '100%';
    // height = '100%';
    // if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端，就使用自适应大小弹窗
    //     width='auto';
    //     height='auto';
    // }else{//如果是PC端，根据用户设置的width和height显示。
    //
    // }
    // top.layer.open({
    //     type: 2,
    //     area: [width, height],
    //     title: title,
    //     maxmin: true, //开启最大化最小化按钮
    //     content: url ,
    //     btn: ['关闭'],
    //     cancel: function(index){
    //
    //     }
    // });
}

//=========================
//======登录个人账号
//=========================

/**
 * 查看公司详情页面
 */
function openUser(title,url, gridId, infoId ,width,height){
    url=url.replace("{id}",infoId);
    window.open(url);
}



//==========================
//======修改账号信息==========
//==========================
/** 账号激活 */
function userActive(title,url, gridId, infoId , width,height){
    var rowData = getSelectRowData(title,url, gridId, infoId ,width,height)
    if(rowData){
        alertDialog(title,url,rowData.userId,gridId,"确定激活该账号吗？")
    }
}

/** 账号冻结 */
function userFreeze(title,url, gridId, infoId ,tipMsg, width,height){
    var rowData = getSelectRowData(title,url, gridId, infoId ,width,height)
    if(rowData){
        alertDialog(title,url,rowData.userId,gridId, "确定注销该账号吗？")
    }
}

/** 修改密码 */
function updatePassword(title,url, gridId, infoId ,tipMsg, width,height){
    var rowData = getSelectRowData(title,url, gridId, infoId ,width,height)
    if(rowData){
        top.layer.prompt({title: '请输入新密码，并确认', formType: 1}, function(pass, index){
            top.layer.close(index);
            top.layer.prompt({title: '请再次输入新密码，并确认', formType: 1}, function(passTwo, index){
                top.layer.close(index);
                if(pass == passTwo){
                    url=url.replace("{id}",rowData.id);
                    $.ajax({
                        url : url,
                        type : 'post',
                        data : {
                            pwd : pass
                        },
                        cache : false,
                        success : function(data) {
                            if (data.flag) {
                                top.layer.alert("两次输入的密码是一样一样的！")
                            }else{
                                top.layer.alert("密码修改失败，请联系管理员！！")
                            }
                        }
                    });

                }else{
                    top.layer.alert("两次输入的密码不一致！")
                }
            });
        });
        // url=url.replace("{id}",infoId);
        // top.layer.alert('该功能正在开发!', {icon: 0, title:'友情提示'});
    }
}

/** 绑定账号 */
function bindingAccount(title,url, gridId, infoId ,tipMsg, width,height){
    var rowData = getSelectRowData(title,url, gridId, infoId ,width,height)
    if(rowData){
        url=url.replace("{id}",infoId);
        top.layer.alert('该功能正在开发!', {icon: 0, title:'友情提示'});
    }
}

/** 获取选中行 */
function getSelectRowData(title,url, gridId, infoId ,width,height){
    var rowsData = $("#"+gridId).jqGrid('getGridParam','selarrrow');
    var multiselect=$("#"+gridId).jqGrid('getGridParam','multiselect');
    var rowData= $("#"+gridId).jqGrid('getGridParam','selrow');
    if(!multiselect)
    {
        if(rowData)
        {
            rowsData[0]=rowData;
        }
    }
    if (!rowsData || rowsData.length==0) {
        top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
        return;
    }
    if (rowsData.length>1) {
        top.layer.alert('只能选择一条数据!', {icon: 0, title:'警告'});
        return;
    }
    rowData = $("#"+gridId).jqGrid('getRowData', rowData);
    return rowData
}

/** 确认提示框 */
function alertDialog(title,url,infoid,gridId,tipMsg){
    url=url.replace("{id}",infoid);
    if(tipMsg==undefined||tipMsg==''){
        tipMsg="您确定要执行该操作！";
    }
    swal({
        title: "提示",
        text: tipMsg,
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        closeOnConfirm: false,
        cancelButtonText: "取消",
    }, function () {
        $.ajax({
            url : url,
            type : 'post',
            data : {
                id : infoid
            },
            cache : false,
            success : function(d) {
                if (d.ret==0) {
                    var msg = d.msg;
                    swal("提示！", msg, "success");
                    //刷新表单
                    refreshTable(gridId);
                }else{
                    var msg = d.msg;
                    swal("提示！", msg, "error");
                }
            }
        });
    });
}

// //=======限制输入长度
// $(function(){
//     $("#rsqUserStatisticGridIdGridQuery input[name='name']").attr({maxlength:"7"});
//     $("#rsqUserStatisticGridIdGridQuery input[name='email']").attr({maxlength:"7"});
// });
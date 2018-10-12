/*	日事清项目相关js存放 */

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
function openCompany(title,url, gridId, infoId ,width,height){
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
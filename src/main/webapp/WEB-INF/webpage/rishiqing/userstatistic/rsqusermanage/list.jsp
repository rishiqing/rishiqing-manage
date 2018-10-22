<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>日事清用户数据统计列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="日事清用户数据统计">
<grid:grid id="rsqUserStatisticGridId" url="${adminPath}/userstatistic/rsqusermanage/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="userId" hidden="true"   name="userId" width="100"/>
    <grid:column label="teamId" hidden="true"   name="teamId" width="100"/>

    <grid:column label="姓名"  name="name"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="邮箱"  name="email"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="手机号"  name="phone"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="注册时间"  name="registDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="注册时长"  name="registDay"/>
    <grid:column label="最后登录时间"  name="lastLoginDate"  />
    <grid:column label="登录次数" name="loginCnt" formatter="number" />
    <grid:column label="公司名称"  name="teamName" />
    <grid:column label="公司人数"  name="teamNumber" />
    <grid:column label="数据来源"  name="dateFrom" />
    <grid:column label="渠道"  name="dateChannel" />
    <grid:column label="是否显示"  width="80" dict="sf" formatterClass="0:label label-danger;1:label label-success" name="accountLocked" />
    <grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>

    <grid:toolbar title="账号激活" btnclass="btn-info" icon="fa-file-code-o" function="userActive" url="${adminPath}/userstatistic/rsqusermanage/{id}/userActive"  />
    <grid:toolbar title="账号注销" btnclass="btn-info" icon="fa-file-code-o" function="userFreeze" url="${adminPath}/userstatistic/rsqusermanage/{id}/userFreeze"  />
    <grid:toolbar title="修改密码" btnclass="btn-info" icon="fa-file-code-o" function="updatePassword" url="${adminPath}/common/rsqcommon/{id}/updatePassword"  />
    <grid:toolbar title="绑定账号" btnclass="btn-info" icon="fa-file-code-o" function="bindingAccount" url="${adminPath}/userstatistic/rsqusermanage/bindingAccount"  />
</grid:grid>
    <html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
    <script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
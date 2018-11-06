<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>日事清用户数据统计列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="日事清用户数据统计">
<grid:grid id="rsqUserStatisticGridId" url="${adminPath}/userstatistic/rsquserstatistic/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="teamId" hidden="true"   name="teamId" width="100"/>

    <grid:column label="用户id" name="userId" width="100" query="true"  queryMode="input"  condition="like"/>
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

    <shiro:hasAnyRoles name="admin,manager,employee">
        <grid:column label="公司详情"  name="opt" formatter="button"/>
        <grid:button title="公司详情" groupname="opt" function="openCompany" outclass="btn-warning" url="${adminPath}/common/rsqcommon/openCompany?id={id}" />
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="admin">
        <grid:column label="更多"  name="opt2" formatter="button"/>
        <grid:button title="更多" groupname="opt2" function="openUser"  outclass="btn-warning" url="${adminPath}/common/rsqcommon/openUser?id={id}" />
    </shiro:hasAnyRoles>

    <grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>

</grid:grid>
    <html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
    <script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
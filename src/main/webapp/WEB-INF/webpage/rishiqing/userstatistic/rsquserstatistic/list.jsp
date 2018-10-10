<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>日事清用户数据统计列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="日事清用户数据统计">
<grid:grid id="rsqUserStatisticGridId" url="${adminPath}/userstatistic/rsquserstatistic/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>
<%--	<grid:button groupname="opt" function="delete" />--%>
    <grid:column label="姓名"  name="name"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="邮箱"  name="email"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="手机号"  name="phone"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="注册时间"  name="registDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="注册时长"  name="registDay"/>
    <grid:column label="最后登录时间"  name="lastLoginDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="公司名称"  name="teamName" />
    <grid:column label="公司人数"  name="teamNumber" />
    <grid:column label="数据来源"  name="dateFrom" />
    <grid:column label="渠道"  name="dateChannel" />
<%--	<grid:toolbar function="create"/>--%>
	<grid:toolbar function="update"/>
<%--	<grid:toolbar function="delete"/>--%>
	
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>
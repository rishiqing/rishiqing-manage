<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>充值消费记录列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="充值消费记录">
<grid:grid id="rsqPayRecordGridId" url="${adminPath}/payrecord/rsqpayrecord/ajaxList">
    <grid:column label="sys.common.key" hidden="true"   name="id"/>
    <grid:column label="userId" hidden="true"   name="userId"/>
    <grid:column label="teamId" hidden="true"   name="teamId"/>
    <grid:column label="公司详情"  name="opt" formatter="button" width="60" />
    <shiro:hasAnyRoles name="admin,manager">
        <grid:button groupname="opt" function="openCompany" title="公司详情" outclass="btn-warning" url="${adminPath}/common/rsqcommon/openCompany?id={id}" />
    </shiro:hasAnyRoles>
    <grid:column label="充值时间"  name="payDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="用户类型"  name="payType"  query="true"  queryMode="select"  condition="eq"  dict="payType"/>
    <grid:column label="充值类型"  name="plateform"  query="true"  queryMode="select"  condition="eq"  dict="plateform"/>
    <grid:column label="价格（自付费）"  name="price"  query="true"  queryMode="input"  condition="eq" />
    <grid:column label="团队/个人名称"  name="teamName"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="联系人/用户名"  name="contacts"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="电话"  name="linkedPhone" />
    <grid:column label="充值记录" name="desc" width="250"/>
    <grid:column label="操作人员" name="operator" width="100"/>

	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
<html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
<script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
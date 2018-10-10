<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>充值消费记录列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="充值消费记录">
<grid:grid id="rsqPayRecordGridId" url="${adminPath}/payrecord/rsqpayrecord/ajaxList">
    <grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
<%--	<grid:column label="sys.common.opt"  name="opt" formatter="button" width="100"/>--%>
	<grid:button groupname="opt" function="delete" />
    <grid:column label="充值时间"  name="payDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="用户类型"  name="payType"  query="true"  queryMode="select"  condition="eq"  dict="payType"/>
    <grid:column label="充值类型"  name="plateform"  query="true"  queryMode="select"  condition="eq"  dict="plateform"/>
    <grid:column label="价格（自付费）"  name="price"  query="true"  queryMode="input"  condition="eq" />
    <grid:column label="团队/个人名称"  name="teamName"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="联系人/用户名"  name="contacts"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="电话"  name="linkedPhone" />
    <grid:column label="当前充值后到期时间"  name="deadline" />
	<%--<grid:toolbar function="create"/>--%>
	<%--<grid:toolbar function="update"/>--%>
	<%--<grid:toolbar function="delete"/>--%>
	<grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
</body>
</html>
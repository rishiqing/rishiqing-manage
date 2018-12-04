<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>用户意见列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="用户意见管理">
<grid:grid id="rsqUserAdviceGridId" url="${adminPath}/useradvice/useradvice/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id"/>
    <grid:column label="用户"  name="username"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="评分"  name="star" query="true" queryMode="input"  condition="like"/>
    <grid:column label="意见"  name="remark" />
    <grid:column label="渠道"  name="client" query="true"  queryMode="input"  condition="like"/>
    <grid:column label="创建时间"  name="dateCreated"  query="true"  queryMode="date"  condition="between" />
    <grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
    <html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
    <script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
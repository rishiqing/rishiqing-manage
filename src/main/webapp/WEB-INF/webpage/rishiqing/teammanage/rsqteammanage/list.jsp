<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>团队管理列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="团队管理">
<grid:grid id="rsqTeamManageGridId" url="${adminPath}/teammanage/rsqteammanage/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id"/>
    <grid:column label="userId" hidden="true"   name="userId"/>
    <grid:column label="teamId" hidden="true"   name="teamId"/>
    <shiro:hasAnyRoles name="admin,manager">
        <grid:column label="充值"  name="opt" formatter="button"  width="70"/>
        <grid:button title="充值"  groupname="opt" function="pay"  outclass="btn-primary" url="${adminPath}/teammanage/rsqteammanage/pay?id={id}" />
    </shiro:hasAnyRoles>
    <grid:column label="名称"  name="teamName"  query="true"  queryMode="input"  condition="like" />
    <grid:column label="创建者"  name="createManName" />
    <grid:column label="联系邮箱"  name="linkedEmail" />
    <grid:column label="联系电话"  name="linkedPhone" query="true"  queryMode="input"  condition="like"/>
    <grid:column label="创建时间"  name="createDate"  query="true"  queryMode="date"  condition="between" />
    <grid:column label="成员数"  name="teamMember"  query="true"/>
    <shiro:hasAnyRoles name="admin,manager">
        <grid:column label="公司详情"  name="opt2" formatter="button"  width="70"/>
        <grid:button title="公司详情" groupname="opt2" function="openCompany" outclass="btn-warning" url="${adminPath}/common/rsqcommon/openCompany?id={id}" />
    </shiro:hasAnyRoles>

    <grid:toolbar function="search"/>
	<grid:toolbar function="reset"/>
</grid:grid>
    <html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
    <script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
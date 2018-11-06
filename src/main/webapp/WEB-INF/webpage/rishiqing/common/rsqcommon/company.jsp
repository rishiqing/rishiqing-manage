<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>${rsqTeamManage.teamName}公司详情</title>
    <meta name="decorator" content="form"/>
</head>

<body class="white-bg rsq-pay" >
<div class="col-sm-12">
	<table class="table table-striped">
		<thead>
		<tr>
			<th>团队名称</th>
			<th>创建者</th>
			<th>当前成员数</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${rsqTeamManage.teamName}</td>
			<td>${rsqTeamManage.createManName} </td>
			<td>${rsqTeamManage.teamMember}</td>
		</tr>
		</tbody>
	</table>
</div>
<div class="tabs-container">
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab_try" aria-expanded="true">团队成员</a></li>
		<li class=""><a data-toggle="tab" href="#tab_buy" aria-expanded="false">沟通记录</a></li>
		<li class=""><a data-toggle="tab" href="#tab_renewal" aria-expanded="false">公司设置</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab_try" class="tab-pane active">
			<div class="panel-body">
				<grid:grid id="rsqUserStatisticGridId" url="${adminPath}/userstatistic/rsquserstatistic/ajaxList?teamId=${rsqTeamManage.id}">
					<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
					<grid:column label="userId" hidden="true"   name="userId" width="100"/>
					<grid:column label="teamId" hidden="true"   name="teamId" width="100"/>
					<%--
					<grid:button title="公司详情" groupname="opt" function="openCompany" outclass="btn-warning" url="${adminPath}/common/rsqcommon/openCompany?id={id}" />
--%>

					<grid:column label="姓名"  name="name"  query="true"  queryMode="input"  condition="like" />
					<grid:column label="邮箱"  name="email"  query="true"  queryMode="input"  condition="like" />
					<grid:column label="手机号"  name="phone"  query="true"  queryMode="input"  condition="like" />
					<grid:column label="注册时间"  name="registDate"  query="true"  queryMode="date"  condition="between" />
					<grid:column label="注册时长"  name="registDay"/>
					<grid:column label="最后登录时间"  name="lastLoginDate"/>

					<grid:column label="公司名称"  name="teamName" />
					<grid:column label="公司人数"  name="teamNumber" />
					<grid:column label="数据来源"  name="dateFrom" />
					<grid:column label="渠道"  name="dateChannel" />
					<shiro:hasAnyRoles name="admin">
						<grid:column label="更多"  name="opt" formatter="button" width="70"/>
						<grid:button title="更多" groupname="opt" function="openUser"  outclass="btn-warning" url="${adminPath}/common/rsqcommon/openUser?id={id}" />
					</shiro:hasAnyRoles>
					<%--<grid:toolbar function="search"/>--%>
					<%--<grid:toolbar function="reset"/>--%>
				</grid:grid>
			</div>
		</div>
		<div id="tab_buy" class="tab-pane">
			<div class="panel-body">
				正在开发
			</div>
		</div>
		<div id="tab_renewal" class="tab-pane">
			<div class="panel-body">
				正在开发
			</div>
		</div>
	</div>
</div>

<html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
<script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
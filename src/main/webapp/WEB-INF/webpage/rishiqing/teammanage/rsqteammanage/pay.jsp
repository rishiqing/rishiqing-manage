<%@ page import="com.rishiqing.core.constant.RsqSystemConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
    <title>团队管理</title>
    <meta name="decorator" content="form"/>
</head>

<body class="white-bg rsq-pay" >
<div class="col-sm-12">
	<table class="table table-striped">
		<thead>
		<tr>
			<th width="20%">团队名称</th>
			<th width="15%">创建者</th>
			<th>成员数</th>
			<th>公司状态</th>
			<th>失效日期</th>
			<th>优先级</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${rsqTeamInfoList}" var="it">
			<tr>
				<td width="20%">${rsqTeamManage.teamName}</td>
				<td width="15%">${rsqTeamManage.createManName} </td>
				<td>${rsqTeamManage.teamMember}</td>
				<td>${it.versionName}（${it.expired}）</td>
				<td>${it.deadLine}</td>
				<td>${it.priority}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="tabs-container">
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab_try" aria-expanded="true">开通试用</a></li>
		<li class=""><a data-toggle="tab" href="#tab_buy" aria-expanded="false">购买</a></li>
		<li class=""><a data-toggle="tab" href="#tab_renewal" aria-expanded="false">续费</a></li>
		<li class=""><a data-toggle="tab" href="#tab_add" aria-expanded="false">增加成员</a></li>
		<li class=""><a data-toggle="tab" href="#tab_Upgrade" aria-expanded="false">升级</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab_try" class="tab-pane active">
			<div class="panel-body">
				<form:form id="rsq_try" modelAttribute="rsqTeamManage" action="${adminPath}/teammanage/rsqteammanage/rsqTry" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="<%=RsqSystemConstants.PAY%>">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">开通版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="productName" value="<%=RsqSystemConstants.TEAM_VERSION_TRIAL_PROFESSIONAL%>" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="productName" value="<%=RsqSystemConstants.TEAM_VERSION_TRIAL_ENTERPRISE%>" >企业版
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">人数:</label>
						<div class="col-sm-8">
							<input id="u1" name="userLimit" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">天数:</label>
						<div class="col-sm-8">
							<input id="a1" name="days" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b1" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">操作人员:</label>
						<div class="col-sm-8">
							<input id="c1" name="operator" class="form-control" type="text" step="1" placeholder="输入操作人员姓名">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit" >确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div id="tab_buy" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_buy" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqBuy" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="<%=RsqSystemConstants.PAY%>">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">购买版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="productName" value="<%=RsqSystemConstants.TEAM_VERSION_BASE_PROFESSIONAL%>" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="productName" value="<%=RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE%>" >企业版
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">人数:</label>
						<div class="col-sm-8">
							<input id="a2" name="userLimit" class="form-control" type="number" min="-1" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">天数:</label>
						<div class="col-sm-8">
							<input id="a3" name="days" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b2" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">操作人员:</label>
						<div class="col-sm-8">
							<input id="c2" name="operator" class="form-control" type="text" step="1" placeholder="输入操作人员姓名">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit">确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div id="tab_renewal" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_renewal" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqRenewal" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="<%=RsqSystemConstants.RENEWALS%>"><!-- 续费 -->
					<input name="userLimit" type="hidden" value="0">
					<input name="productName" type="hidden" value="${teamStatusMap.productName}">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">续费天数:</label>
						<div class="col-sm-8">
							<input id="a4" name="days" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b3" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">操作人员:</label>
						<div class="col-sm-8">
							<input id="c3" name="operator" class="form-control" type="text" step="1" placeholder="输入操作人员姓名">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit">确定</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div id="tab_add" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_add" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqAdd" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="<%=RsqSystemConstants.ADD%>">
					<input name="days" type="hidden" value="0">
					<input name="productName" type="hidden" value="${teamStatusMap.productName}">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">增加人数:</label>
						<div class="col-sm-8">
							<input id="a5" name="userLimit" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b4" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">操作人员:</label>
						<div class="col-sm-8">
							<input id="c4" name="operator" class="form-control" type="text" step="1" placeholder="输入操作人员姓名">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit">确定</button>
							<%--<input  class="btn btn btn-warning" type="reset" onclick="myReset()"/>--%>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div id="tab_Upgrade" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_update" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqUpdate" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="<%=RsqSystemConstants.UPDATE%>">
					<input name="days" type="hidden" value="0">
					<input name="userLimit" type="hidden" value="0">
					<input type="hidden" name="productName" value="<%=RsqSystemConstants.TEAM_VERSION_BASE_ENTERPRISE%>">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b5" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">操作人员:</label>
						<div class="col-sm-8">
							<input id="c5" name="operator" class="form-control" type="text" step="1" placeholder="输入操作人员姓名">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit">升级</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid"/>
<script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
</body>
</html>
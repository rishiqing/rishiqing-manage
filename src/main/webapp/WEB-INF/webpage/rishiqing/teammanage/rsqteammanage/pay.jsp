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
			<th>团队名称</th>
			<th>创建者</th>
			<th>当前成员数</th>
			<th>团队信息</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>${rsqTeamManage.teamName}</td>
			<td>${rsqTeamManage.createManName} </td>
			<td>${rsqTeamManage.teamMember}</td>
			<td>${teamStatusMap.versionName}（${teamStatusMap.expired}）</td>
		</tr>
		</tbody>
	</table>
</div>
<div class="tabs-container">
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#tab_try" aria-expanded="true">开通试用</a></li>
		<li class=""><a data-toggle="tab" href="#tab_buy" aria-expanded="false">购买</a></li>
		<li class=""><a data-toggle="tab" href="#tab_renewal" aria-expanded="false">续费</a></li>
		<li class=""><a data-toggle="tab" href="#tab_add" aria-expanded="false">增加成员</a></li>
		<li class=""><a data-toggle="tab" href="#tab_Upgrade" aria-expanded="false">专业版升级企业版</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab_try" class="tab-pane active">
			<div class="panel-body">
				<form:form id="rsq_try" modelAttribute="rsqTeamManage" action="${adminPath}/teammanage/rsqteammanage/rsqTry" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="pay">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">开通版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="buyTypeRadio" value="zy" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="buyTypeRadio" value="qy" >企业版
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">人数:</label>
						<div class="col-sm-8">
							<input id="username" name="teamNumber" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">天数:</label>
						<div class="col-sm-8">
							<input id="a1" name="buyDay" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b1" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit" >确定</button>
							<%--<input  class="btn btn btn-warning" type="reset" onclick="myReset()"/>--%>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div id="tab_buy" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_buy" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqBuy" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="pay">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">购买版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="buyTypeRadio" value="zy" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="buyTypeRadio" value="qy" >企业版
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">人数:</label>
						<div class="col-sm-8">
							<input id="a2" name="teamNumber" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">天数:</label>
						<div class="col-sm-8">
							<input id="a3" name="buyDay" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b2" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
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
		<div id="tab_renewal" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_renewal" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqRenewal" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="renewals">
					<input name="teamNumber" type="hidden" value="0">
					<input name="buyTypeRadio" type="hidden" value="${teamStatusMap.buyTypeRadio}">
<%--					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">购买版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="buyTypeRadio" value="zy" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="buyTypeRadio" value="qy" >企业版
						</div>
					</div>--%>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">续费天数:</label>
						<div class="col-sm-8">
							<input id="a4" name="buyDay" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b3" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
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
		<div id="tab_add" class="tab-pane">
			<div class="panel-body">
				<form:form id="rsq_add" modelAttribute="data" action="${adminPath}/teammanage/rsqteammanage/rsqAdd" method="post" class="form-horizontal">
					<input type="hidden" name="teamId" value="${rsqTeamManage.id}">
					<input type="hidden" name="payType" value="add">
					<input name="buyDay" type="hidden" value="0">
					<input name="buyTypeRadio" type="hidden" value="${teamStatusMap.buyTypeRadio}">
<%--					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">购买版本:</label>
						<div class="col-sm-8">
							<input type="radio" name="buyTypeRadio" value="zy" checked="">专业版
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="buyTypeRadio" value="qy" >企业版
						</div>
					</div>--%>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">增加人数:</label>
						<div class="col-sm-8">
							<input id="a5" name="teamNumber" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b4" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
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
					<input type="hidden" name="payType" value="update">
					<input name="buyDay" type="hidden" value="0">
					<input name="teamNumber" type="hidden" value="0">
					<input type="hidden" name="buyTypeRadio" value="qy">
					<div class="form-group col-sm-12">
						<label class="col-sm-4 control-label">费用:</label>
						<div class="col-sm-8">
							<input id="b5" name="totalFee" class="form-control" type="number" min="0" value="0" step="1">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-5" >
							<button class="btn btn-primary" type="submit">升级</button>
							<%--<input  class="btn btn btn-warning" type="reset" onclick="myReset()"/>--%>
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
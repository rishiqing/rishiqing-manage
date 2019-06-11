<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/WEB-INF/webpage/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>用户意见列表</title>
  <meta name="decorator" content="list"/>
</head>
<body title="用户意见管理">
    <script src="${staticPath}/modules/rishiqing/rsq_diy.js"></script>
    <html:js  name="jquery,bootstrap,jquery-ui,peity,iCheck,sweetalert,Validform,jqgrid,layer"/>
    <grid:grid id="rsqUserAdviceGridId" url="${adminPath}/useradvice/useradvice/ajaxList">
        <grid:column label="id" name="userId" width="10"/>
        <grid:column id="elem_email_id" label="邮箱"  name="email"  query="true"  queryMode="input"  condition="like" width="20"/>
        <grid:column id="elem_phone_number_id" label="电话号"  name="phoneNumber" query="true" queryMode="input"  condition="like" width="20"/>
        <grid:column id="elem_client_id" label="渠道"  name="client" query="true"  queryMode="input"  condition="like" width="7"/>
        <grid:column label="是否会员"  name="vip" width="10"/>
        <grid:column id="elem_star_id" label="评分"  name="star" query="true" queryMode="input"  condition="like" width="7"/>
        <grid:column id="elem_type_id" label="评分类型" name="type" query="true" queryMode="input" condition="like" width="11"/>
        <grid:column label="意见"  name="remark" width="35"/>
        <grid:column id="elem_date_created_id" label="创建时间"  name="dateCreated"  query="true"  queryMode="date"  condition="between" width="20"/>
        <grid:toolbar function="search"/>
        <grid:toolbar function="reset"/>
        <grid:toolbar title="导出数据" btnclass="btn-info" icon="fa-file-code-o" function="exportAdvice" url="${adminPath}/useradvice/useradvice/exportAdvice" />
    </grid:grid>
</body>
</html>
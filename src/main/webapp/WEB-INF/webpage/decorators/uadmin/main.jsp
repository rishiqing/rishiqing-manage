<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="row  border-bottom white-bg dashboard-header">
        <div class="col-sm-12">
           <div class="panel panel-default">
              <div class="panel-body">
                  <!-- /** 共有用户 */ -->
                  共有用户:<span id="totalUser">0</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <!-- /** 个人用户 */ -->
                  个人用户:<span id="singleUser">0</span>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <!-- /** 公司用户 */ -->
                  公司用户:<span id="teamUser">0</span>
<%--                  <!-- /** 邀请未登录人数 */ -->
                  邀请未登录人数:<span id="waitLoginUser">0</span>
                  <!-- /** 共有公司 */ -->
                  共有公司:<span id="totalTeam">0</span>
                  <!-- /** 正式公司 */ -->
                  正式公司:<span id="effectiveTeam">0</span>
                  <!-- /** 失效公司 */ -->
                  失效公司:<span id="invaildTeam">0</span>
                  <!-- /** 总邀请人数 */ -->
                  总邀请人数:<span id="allInviteUser">0</span>
                  <!-- /** 当前在线人数 */ -->
                  当前在线人数:<span id="currentLoginUser">0</span>--%>
              </div>
           </div>
        </div>
    </div>
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-4">
                <div class="portlet box">
                    <div class="portlet-header">
                        <h5>昨日数据统计</h5>
                    </div>
                    <div class="portlet-body">
	                    <p>注册用户：<a id="yesterday_registUser" href="http://www.jeeweb.cn/" target="_blank" >0</a></p>
	                    <p>新建公司：<a id="yesterday_registTeam" href="javascript:;">0</a></p>
<%--	                    <p>登录团队：<a id="yesterday_loginTeam" href="http://git.oschina.net/dataact/jeeweb/"  target="_blank">0</a></p>
                        <p>登录用户：<a id="yesterday_loginUser" href="http://www.jeeweb.cn/" target="_blank">0</a></p>
                        <p>邀请未登陆人数：<a id="yesterday_waitLoginUser" href="javascript:;">0</a></p>
                        <p>总邀请人数：<a id="yesterday_allInviteUser" href="http://git.oschina.net/dataact/jeeweb/"  target="_blank">0</a></p>--%>
	                </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="portlet box">
                    <div class="portlet-header">
                        <h5>今日数据统计</h5>
                    </div>
                    <div class="portlet-body">
                        <p>注册用户：<a id="today_registUser" href="http://www.jeeweb.cn/" target="_blank" >0</a></p>
                        <p>新建公司：<a id="today_registTeam" href="javascript:;">0</a></p>
<%--                        <p>登录团队：<a id="today_loginTeam" href="http://git.oschina.net/dataact/jeeweb/"  target="_blank">0</a></p>
                        <p>登录用户：<a id="today_loginUser" href="http://www.jeeweb.cn/" target="_blank">0</a></p>
                        <p>邀请未登陆人数：<a id="today_waitLoginUser" href="javascript:;">0</a></p>
                        <p>总邀请人数：<a id="today_allInviteUser" href="http://git.oschina.net/dataact/jeeweb/"  target="_blank">0</a></p>--%>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="portlet box">
                    <div class="portlet-header">
                        <h5>钉钉数据统计</h5>
                    </div>
                    <div class="portlet-body">
                         <div class="alert alert-warning">
                             <ol>
                                 <li>该模块暂时不做！</li>
                             </ol>
                         </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/common/path.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑会话流量</title>
<script type="text/javascript" src="${rootPath}resource/js/rl/src/RealLight.js"></script>
<script type="text/javascript">
rl.importCss("nf:std_info");
rl.importJs("nf:Validator");

function toEdit(){
	var mainForm = document.mainForm;
	if(Validator.Validate(mainForm,3)){ 
		mainForm.action = "${rootPath}sessionTrafficConfig_doEdit";
		mainForm.submit();
	}
}
</script>
</head>
<body>
<div class="std_info">
    <div class="page_wrapper limit_770">
    <h3 class="title">编辑会话流量</h3>
	<form name="mainForm" method="post">
    	<input type="hidden" name="sessionId" value="<s:property value="dbModels['default'][0]['session_id']" />" />
		<div class="group">
            <div class="g_body">
            <table cellpadding="0" cellspacing="0" border="0" class="fields_layout">
                <tr>
                    <th class="label">会话通道名称 <span class="asterisk">*</span></th>
                    <td>
                    <input class="field" name="sessionAlias" 
                    value="<s:property value="dbModels['default'][0]['session_alias']" />" dataType="Require" />
                    </td>
                </tr>
                <tr>
                    <th class="label">第一个IP <span class="asterisk">*</span></th>
                    <td>
                    <input class="field" name="firstIp" 
                    value="<s:property value="dbModels['default'][0]['first_ip']" />" dataType="Ip" />
                    </td>
                </tr>
                <tr>
                    <th class="label">第二个IP <span class="asterisk">*</span></th>
                    <td>
                    <input class="field" name="secondIp" 
                    value="<s:property value="dbModels['default'][0]['second_ip']" />" dataType="Ip" />
                    </td>
                </tr>
            </table>
            </div>
        </div>
        <div class="form_action_bar">
            <button type="button" onclick="toEdit();">保 存</button>
            &nbsp;
            <button type="button" onclick="javascript:history.go(-1)">取消</button>
        </div>
	</form>
	</div>
</div>
</body>
</html>
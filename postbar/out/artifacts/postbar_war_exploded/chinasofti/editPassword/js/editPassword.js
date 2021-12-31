function subPassword(){
	var newPassword=$("#newPassword").val();
	var newPasswordCon=$("#newPasswordCon").val()
	var oldPassword=$("#oldPassword").val()
	
	
	if(typeof (newPassword) == 'undefined' || newPassword.trim()==""  ){
		$("#tishi").html("新密码不能为空");
		return;
	}
	if(newPassword.trim().length!=6){
		$("#tishi").html("新密码必须为6位");
		return;
	}
	if(typeof (newPasswordCon) == 'undefined' || newPasswordCon.trim()==""){
		$("#tishi").html("确认密码不能为空");
		return;
	}
	if(newPassword.trim()!=newPasswordCon.trim()){
		$("#tishi").html("新密码与确认密码必须保持一致");
		return;
	}
	
	if(typeof (oldPassword) == 'undefined' || oldPassword.trim()==""  ){
		$("#tishi").html("当前密码不能为空");
		return;
	}
	ajaxLoading();
	
	$.ajax({
        type: "POST",
        url: "/loginController/editPassword",
        async: true,
        data:{
        	oldPassword:oldPassword.trim(),
        	newPassword:newPassword.trim(),
       	 },
        dataType: "json",
        // error: function (XMLHttpRequest, textStatus, errorThrown) {
        // 	$.MsgBox.Alert("消息","当前密码不正确");
        // },
        // success: function (XMLHttpRequest, textStatus, errorThrown) {
        //     $.MsgBox.Alert("成功","密码修改成功");
        // },
        success: function (json) {
        	ajaxLoadEnd();
        	if(json.message!=""){
                alert(json.message);
                window.parent.location.replace("/index.html?menuUserName="+usrname.trim());

        	}else{
                window.parent.location.replace("/chinasofti/login/login.html");
        		//alert(JSON.stringify(json));
                alert("密码修改成功！");
        	}
        	
        }
    });
}
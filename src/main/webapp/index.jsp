<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  <body>
    <form id='form' method="post">
    	url:<input type="text" id='url' value="/TimeSecretaryWeb/"/><br>
    	param:<input type="text" id='param'/><br>
    	json:<input type="text" id='json'/><br>
    	<input type="button" id='submit' value="提交" onclick="submitFrom()">
    </form>
  </body>
  <script type="text/javascript">
  	function post(url, params) {
    var temp = document.createElement("form");
    temp.action = url;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in params) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function post2(url, param,json) {
    var temp = document.createElement("form");
    temp.action = url;
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("input");
    opt.name = param;
    opt.value = json;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
function submitFrom(){
	alert("提交");
	var url = document.getElementById("url").value;
	var param = document.getElementById("param").value;
	var json = document.getElementById("json").value;
	
	post2(url,param,json);
}
  </script>
</html>

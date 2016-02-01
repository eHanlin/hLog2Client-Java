<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form>
    <div><label for="emailInput">email : </label><input id="emailInput" type="email"/></div>
    <div><label for="passwordInput">password : </label><input id="passwordInput" type="password"/></div>
    <button id="loginButton">login</button>
</form>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script>
$(function(){
    $('#loginButton').click(function(event){
        $.post('user/login', {email:$('#emailInput').val(), password:$('#passwordInput').val()}, function(data){
           if(data.success){
               location.href = 'userInfo.html';
           }else{
               alert('login fail');
           }
        });
        return false;
    });
});
</script>

</body>
</html>

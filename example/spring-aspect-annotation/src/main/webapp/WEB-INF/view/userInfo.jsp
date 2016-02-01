<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserInfo</title>
</head>
<body>

<div><label for="emailInput">email : </label><input id="emailInput" type="email"/></div>

<button id="loginButton">logout</button>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script>
$(function(){
    $.get('user/info', function(data){
        if(data.success){
            $('#emailInput').val(data.value.email);
        }else{
            alert('get user info fail');
        }
    });

    $('#loginButton').click(function(event){
        $.post('user/logout', function(data){
            if(data.success){
                location.href = 'login.html';
            }else{
                alert('logout fail')
            }
        });
        return false;
    });
});
</script>

</body>
</html>

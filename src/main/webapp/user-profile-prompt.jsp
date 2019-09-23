<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>
        User Profile Prompt - Servlets With Jsp ;;
    </title>
    <script src="https://raw.githubusercontent.com/Yaffle/EventSource/master/src/eventsource.min.js"></script>
</head>

<body>
    <form action='/app-02/userprofilehandler' method='POST'>
        <input type='text' name='user-name' placeholder='enter your name' /> <br /><br />
        <input type='text' name='user-hobbies' placeholder='enter your hobbies' /> <br /> <br />
        <input type='submit' />
    </form>
</body>

    <script src="/app-21/sse-init.js"></script>

</html>
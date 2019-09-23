<%@ page import="com.babusa.learn.domain.Profile" %>

<%
Profile profile = (Profile) request.getAttribute("profile");
String name = profile.getUserName();
String hobbies = profile.getUserHobbiesList();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>
            User Profile Prompt - Servlets With Jsp
        </title>
        <script src="https://raw.githubusercontent.com/Yaffle/EventSource/master/src/eventsource.min.js"></script>
    </head>
    <body>
        <p> <%= name %> likes <%= hobbies %> </p>
        <a href="/app-21/event-page">Event page</a>
    </body>

        <script src="/app-21/sse-init.js"></script>

</html>
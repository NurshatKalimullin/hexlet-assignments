<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <head>
        <title>Welcome</title>
        <meta charset="UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    </head>
    <p>Do you really want to delete ${user.get("firstName")}?</p>
    <form target="_blank" action="/users/delete?id=${user.get("id")}" method="POST">
              <input onclick="window.location.href = '/users';" type="submit" value="Delete" />
    </form>
<!-- END -->

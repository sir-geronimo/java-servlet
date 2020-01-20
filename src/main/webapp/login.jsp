<%@ page import="jimenez.enger.Index" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>
<c:if test="${sessionScope != null && sessionScope.logged == true}">
    <% response.sendRedirect("Index"); %>
</c:if>

<section class="section">
    <div class="column is-half is-offset-one-quarter">
        <h1 class="title">Hello, please log in.</h1>

        <form method="POST" action="Access">
            <div class="field">
                <label class="label">Username</label>
                <div class="control has-icons-left">
                    <input name="username" class="input" type="text">
                    <span class="icon is-small is-left">
                        <i class="fas fa-user"></i>
                      </span>
                </div>
            </div>

            <div class="field">
                <label class="label">Password</label>
                <div class="control has-icons-left">
                    <input name="password" class="input" type="password">
                    <span class="icon is-small is-left">
                        <i class="fas fa-lock"></i>
                      </span>
                </div>
            </div>

            <div class="field">
                <c:if test="${requestScope.errors[1].toString() != ''}">
                    <c:forEach items="${requestScope.errors}" var="error">
                        <div class="message is-danger">
                            <div class="message-body">
                                <c:out value="${error}"/>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>

            <div class="field">
                <div class="control">
                    <button class="button is-link is-fullwidth">
                        Login
                    </button>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>
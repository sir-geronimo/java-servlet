<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/b09f8a4208.js"></script>
    <style>
        html, body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif;
            font-size: 16px;
            line-height: 1.5;
            height: 100%;
            background: #ECF0F3;
        }
        nav.navbar {
            border-top: 4px solid #276cda;
            margin-bottom: 1rem;
        }
        .navbar-item.brand-text {
            font-weight: 300;
        }
        .navbar-item, .navbar-link {
            font-size: 14px;
            font-weight: 700;
        }
        .columns {
            width: 100%;
            height: 100%;
            margin-left: 0;
        }
        .menu-label {
            color: #8F99A3;
            letter-spacing: 1.3;
            font-weight: 700;
        }
        .menu-list a {
            color: #0F1D38;
            font-size: 14px;
            font-weight: 700;
        }
        .menu-list a:hover {
            background-color: transparent;
            color: #276cda;
        }
        .menu-list a.is-active {
            background-color: transparent;
            color: #276cda;
            font-weight: 700;
        }
        .card {
            box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.18);
            margin-bottom: 2rem;
        }
        .card-header-title {
            color: #8F99A3;
            font-weight: 400;
        }
        .info-tiles {
            margin: 1rem 0;
        }
        .info-tiles .subtitle {
            font-weight: 300;
            color: #8F99A3;
        }
        .hero.welcome.is-info {
            background: #36D1DC;
            background: -webkit-linear-gradient(to right, #5B86E5, #36D1DC);
            background: linear-gradient(to right, #5B86E5, #36D1DC);
        }
        .hero.welcome .title, .hero.welcome .subtitle {
            color: hsl(192, 17%, 99%);
        }
        .card .content {
            font-size: 14px;
        }
        .card-footer-item {
            font-size: 14px;
            font-weight: 700;
            color: #8F99A3;
        }
        .card-footer-item:hover {
        }
        .card-table .table {
            margin-bottom: 0;
        }
        .events-card .card-table {
            max-height: 250px;
            overflow-y: scroll;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope == null || sessionScope.logged != true}">
        <% response.sendRedirect("Access"); %>
</c:if>
<jsp:include page="layout/navbar.jsp"/>
<section class="section">
    <div class="container">
        <h1 class="is-size-1">Create New Product</h1>
        <br/>

        <form action="Product" method="post">
            <input type="hidden" class="readonly" name="Action" value="create">
            <div class="field">
                <label class="label">Name</label>
                <div class="control">
                    <input class="input" type="text" name="Name">
                </div>
            </div>
            <div class="field">
                <label class="label">Description</label>
                <div class="control">
                    <textarea class="textarea" name="Description"></textarea>
                </div>
            </div>
            <div class="field">
                <label class="label">Price</label>
                <div class="control">
                    <input class="input" type="number" name="Price" min="0"
                           value="0" step="0.01" pattern="^\d+(?:\.\d{1,2})?$">
                </div>
            </div>
            <div class="field">
                <label class="label">Quantity</label>
                <div class="control">
                    <input class="input" type="number" name="Quantity" min="0" pattern="^\d+">
                </div>
            </div>
            <div class="field">
                <label class="label">Supplier</label>
                <div class="control">
                    <input class="input" type="text" name="Supplier">
                </div>
            </div>
            <div class="field">
                <label class="label">Status</label>
                <div class="control">
                    <input class="input" type="text" name="Status">
                </div>
            </div>
            <br/>
            <div class="field">
                <div class="buttons">
                    <button class="button is-primary" type="submit">Create</button>
                    <a href="Product" class="button is-danger is-light">
                        <span><i class="fas fa-times-circle"></i></span>
                        <span>&nbsp;Cancel</span>
                    </a>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>

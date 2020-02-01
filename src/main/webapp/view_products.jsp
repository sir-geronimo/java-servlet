<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products</title>
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
        <h1 class="is-size-1">Products</h1>
        <br/>

        <div class="field is-grouped">
            <div class="control">
                <form action="Product" method="get">
                    <input type="hidden" name="Action" value="create">
                    <button class="button is-link" type="submit">
                        <i class="fas fa-plus-circle"></i>
                        &nbsp;Create
                    </button>
                </form>
            </div>
            <div class="control">
                <a href="Index" class="button is-danger is-light">
                    <span><i class="fas fa-times-circle"></i></span>
                    <span>&nbsp;Cancel</span>
                </a>
            </div>
        </div>

        <table class="table is-hoverable is-striped is-fullwidth is-responsive">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Supplier</th>
                        <th>Created Date</th>
                        <th>Status</th>
                        <th><i class="fas fa-bars"></i></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${requestScope.products}">
                        <tr style="text-align: center !important;">
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                            <td>${product.supplier}</td>
                            <td>${product.createdDate}</td>
                            <td>${product.status}</td>
                            <td>
                                <div class="buttons">
                                    <form action="Product" method="get">
                                        <input type="hidden" name="Id" value="${product.id}">
                                        <input type="hidden" name="Action" value="edit">
                                        <button type="submit" class="button is-link">
                                            <i class="fas fa-pencil-alt fa-sm"></i>
                                        </button>
                                    </form>
                                    &nbsp;

                                    <form onsubmit="return confirm('Are you sure you want to delete this?')" action="Product" method="post">
                                        <input type="hidden" name="Id" value="${product.id}">
                                        <input type="hidden" name="Action" value="delete">
                                        <button type="submit" class="button is-danger">
                                            <i class="far fa-trash-alt fa-sm"></i>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </div>
</section>
</body>
</html>
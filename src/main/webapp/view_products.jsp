<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <!-- fontawesome -->
    <script src="https://kit.fontawesome.com/b09f8a4208.js"></script>
</head>
<body>
<c:if test="${sessionScope == null || sessionScope.logged != true}">
    <% response.sendRedirect("Access"); %>
</c:if>

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

        <table class="table is-hoverable is-striped is-fullwidth">
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
                        <tr style="text-align: center">
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>${product.price}</td>
                            <td>${product.quantity}</td>
                            <td>${product.supplier}</td>
                            <td>${product.createdDate}</td>
                            <td>${product.status}</td>
                            <td>
                                <form action="Product" method="get">
                                    <input type="hidden" name="Id" value="${product.id}">
                                    <input type="hidden" name="Action" value="edit">
                                    <button type="submit" class="button is-link">
                                        <i class="fas fa-pencil-alt"></i>
                                    </button>
                                </form>

                                <form action="Product" method="post">
                                    <input type="hidden" name="Id" value="${product.id}">
                                    <input type="hidden" name="Action" value="delete">
                                    <button type="submit" class="button is-danger">
                                        <i class="far fa-trash-alt"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </div>
</section>
</body>
</html>
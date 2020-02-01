<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>New Product</title>
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
                    <input class="input" type="number" name="Quantity" min="0">
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
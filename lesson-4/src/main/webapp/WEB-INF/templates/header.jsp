<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <c:url value="/" var="homeUrl"/>
    <c:url value="/cart" var="cartUrl"/>
    <c:url value="/about" var="aboutUrl"/>
    <c:url value="/order" var="orderUrl"/>
    <a class="navbar-brand" href="${homeUrl}">Home</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${homeUrl}">List of Products</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${cartUrl}">Cart</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${aboutUrl}">About us</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${orderUrl}">Order</a>
            </li>
        </ul>
    </div>
</nav>
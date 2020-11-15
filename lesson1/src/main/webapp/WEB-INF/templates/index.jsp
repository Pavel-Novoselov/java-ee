<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<%@ include file="head.jsp"%>

<body>

<%@ include file="header.jsp"%>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/new" var="productNewUrl"/>
            <a class="btn btn-primary" href="${productNewUrl}">Add Product</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="product" items="${requestScope.products}">
                    <tr>
                        <th scope="row">
                            <c:out value="${product.id}"/>
                        </th>
                        <td>
                            <c:out value="${product.description}"/>
                        </td>
                        <td>
                            <c:out value="${product.price}"/>
                        </td>
                        <td>
                            <c:url value="/edit" var="productEditUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${productEditUrl}"><i class="fas fa-edit"></i></a>
                            <c:url value="/delete" var="productDeleteUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-danger" href="${productDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                            <c:url value="/cart/add" var="addToCartUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${addToCartUrl}"><i class="fas fa-edit"></i></a>
                            <c:url value="/showOne" var="showOneUrl">
                                <c:param name="id" value="${product.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${showOneUrl}"><i class="fas fa-edit"></i></a>
                        </td>
                    </tr>
                    <%-- } --%>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="footer-scripts.jsp" %>

</html>
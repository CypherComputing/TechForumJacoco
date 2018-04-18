<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <form method="POST" enctype="multipart/form-data" action="/upload">
           <div class="row">
            File to upload:<input type="file" name="file" />
           </div>
        <div class="row">
                <select name="formType">
                <c:forEach begin="0" items="${formTypes}" var="item">
                    <option onchange="selectForm" value="${item}">${item}</option>
                </c:forEach>

            </select>
        </div>
        <div class="row">
           <input type="submit" value="Upload" />
        </div>
    </form>
</div>
</body>
</html>

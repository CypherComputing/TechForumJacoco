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
</head>
<body>
<div class="container">

    <div class="row">
        <div class="form-group">
            <label for="error">Error Detail:</label>
            <textarea class="form-control" readonly="true" rows="5" id="error">
             <c:if test="${FileData.valid}">
                 Everyting is a ok!
             </c:if>
                ${FileData.message}
            </textarea>
        </div>
    </div>

    <!-- Header Table-->
    <div class="row">
        <div class="table-bordered table-responsive text-center">
            <table class="table table-bordered" style="border: 1px solid #ddd !important;">
                <thead>
                <tr>
                    <th>
                        <b>${ColumnNames[1]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[2]}</b>
                    </th>
                    <th>
                        <b>FILE VALUE</b>
                    </th>
                    <th>
                        <b>SPEC ${ColumnNames[9]}</b>
                    </th>
                    <th>
                        <b>FILE LENGTH</b>
                    </th>
                    <th>
                        <b>${ColumnNames[10]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[11]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[12]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[7]}</b>
                    </th>
                </tr>

                </thead>
                <tbody>
                <c:forEach begin="0" items="${DBBatchFileSpec['Header']}" var="item" varStatus="loop">
                    <tr>
                        <td>
                            <c:if test="${item.fileSpecSectionCode ==1}">
                                Header
                            </c:if>
                            <c:if test="${item.fileSpecSectionCode ==2}">
                                Record
                            </c:if>
                            <c:if test="${item.fileSpecSectionCode ==3}">
                                Trailer
                            </c:if>
                        </td>
                        <td>
                                ${item.fieldName}
                        </td>
                        <td>
                            <c:if test="${item.id!=FileData.headerDataList[loop.index].getValue()}">
                                <span style="background-color: #ff4f4f">${FileData.headerDataList[loop.index].getValue()}</span>
                            </c:if>
                            <c:if test="${item.id==FileData.headerDataList[loop.index].getValue()}">
                                ${FileData.headerDataList[loop.index].getValue()}
                            </c:if>
                        </td>
                        <td>
                                ${item.length}
                        </td>
                        <td>
                            <c:if test="${item.length!=FileData.headerDataList[loop.index].getValue().length()}">
                                <span style="background-color: #ff4f4f">${FileData.headerDataList[loop.index].getValue().length()}</span>
                            </c:if>
                            <c:if test="${item.length==FileData.headerDataList[loop.index].getValue().length()}">
                                ${FileData.headerDataList[loop.index].getValue().length()}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.leftJustified ==1}">
                                Left
                            </c:if>
                            <c:if test="${item.leftJustified ==0}">
                                Right
                            </c:if>

                        </td>
                        <td>
                                ${item.paddingChar}
                        </td>
                        <td>
                            <c:if test="${item.aggregate ==1}">
                                Yes
                            </c:if>
                            <c:if test="${item.aggregate ==0}">
                                No
                            </c:if>
                        </td>
                        <td>
                                ${item.aggregateFieldName}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Record Table-->
    <div class="row">
        <div class="table-bordered table-responsive text-center">
            <c:forEach begin="1" end="${FileData.recordCount}" var="count">
                <table class="table table-bordered" style="border: 1px solid #ddd !important;">
                    <thead>
                    <tr>
                        <th>
                            <b>${ColumnNames[1]}</b>
                        </th>
                        <th>
                            <b>${ColumnNames[2]}</b>
                        </th>
                        <th>
                            <b>FILE VALUE</b>
                        </th>
                        <th>
                            <b>SPEC ${ColumnNames[9]}</b>
                        </th>
                        <th>
                            <b>FILE LENGTH</b>
                        </th>
                        <th>
                            <b>${ColumnNames[10]}</b>
                        </th>
                        <th>
                            <b>${ColumnNames[11]}</b>
                        </th>
                        <th>
                            <b>${ColumnNames[12]}</b>
                        </th>
                        <th>
                            <b>${ColumnNames[7]}</b>
                        </th>
                    </tr>

                    </thead>
                    <tbody>
                    <c:forEach begin="0" items="${DBBatchFileSpec['Record']}" var="item" varStatus="loop">
                        <tr>
                            <td>
                                <c:if test="${item.fileSpecSectionCode ==1}">
                                    Header
                                </c:if>
                                <c:if test="${item.fileSpecSectionCode ==2}">
                                    Record
                                </c:if>
                                <c:if test="${item.fileSpecSectionCode ==3}">
                                    Trailer
                                </c:if>
                            </td>
                            <td>
                                    ${item.fieldName}
                            </td>
                            <td>
                                <c:if test="${item.id!=FileData.bodyDataList[loop.index].getValue()}">
                                    <span style="background-color: #ff4f4f">${FileData.bodyDataList[loop.index].getValue()}</span>
                                </c:if>
                                <c:if test="${item.id==FileData.bodyDataList[loop.index].getValue()}">
                                    ${FileData.bodyDataList[loop.index].getValue()}
                                </c:if>
                            </td>
                            <td>
                                    ${item.length}
                            </td>
                            <td>
                                <c:if test="${item.length!=FileData.bodyDataList[loop.index].getValue().length()}">
                                    <span style="background-color: #ff4f4f">${FileData.bodyDataList[loop.index].getValue().length()}</span>
                                </c:if>
                                <c:if test="${item.length==FileData.bodyDataList[loop.index].getValue().length()}">
                                    ${FileData.bodyDataList[loop.index].getValue().length()}
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${item.leftJustified ==1}">
                                    Left
                                </c:if>
                                <c:if test="${item.leftJustified ==0}">
                                    Right
                                </c:if>

                            </td>
                            <td>
                                    ${item.paddingChar}
                            </td>
                            <td>
                                <c:if test="${item.aggregate ==1}">
                                    Yes
                                </c:if>
                                <c:if test="${item.aggregate ==0}">
                                    No
                                </c:if>
                            </td>
                            <td>
                                    ${item.aggregateFieldName}
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:forEach>
        </div>
    </div>

    <!-- Trailer Table-->
    <div class="row">
        <div class="table-bordered table-responsive text-center">
            <table class="table table-bordered" style="border: 1px solid #ddd !important;">
                <thead>
                <tr>
                    <th>
                        <b>${ColumnNames[1]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[2]}</b>
                    </th>
                    <th>
                        <b>FILE VALUE</b>
                    </th>
                    <th>
                        <b>SPEC ${ColumnNames[9]}</b>
                    </th>
                    <th>
                        <b>FILE LENGTH</b>
                    </th>
                    <th>
                        <b>${ColumnNames[10]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[11]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[12]}</b>
                    </th>
                    <th>
                        <b>${ColumnNames[7]}</b>
                    </th>
                </tr>

                </thead>
                <tbody>
                <c:forEach begin="0" items="${DBBatchFileSpec['TRAILER']}" var="item" varStatus="loop">
                    <tr>
                        <td>
                            <c:if test="${item.fileSpecSectionCode ==1}">
                                Header
                            </c:if>
                            <c:if test="${item.fileSpecSectionCode ==2}">
                                Record
                            </c:if>
                            <c:if test="${item.fileSpecSectionCode ==3}">
                                Trailer
                            </c:if>
                        </td>
                        <td>
                                ${item.fieldName}
                        </td>
                        <td>
                            <c:if test="${item.id!=FileData.trailerDataList[loop.index].getValue()}">
                                <span style="background-color: #ff4f4f">${FileData.trailerDataList[loop.index].getValue().toString()}</span>
                            </c:if>
                            <c:if test="${item.id==FileData.trailerDataList[loop.index].getValue()}">
                                ${FileData.trailerDataList[loop.index].getValue().toString()}
                            </c:if>
                        </td>
                        <td>
                                ${item.length}
                        </td>
                        <td>
                            <c:if test="${item.length!=FileData.trailerDataList[loop.index].getValue().length()}">
                                <span style="background-color: #ff4f4f">${FileData.trailerDataList[loop.index].getValue().length()}</span>
                            </c:if>
                            <c:if test="${item.length==FileData.trailerDataList[loop.index].getValue().length()}">
                                ${FileData.trailerDataList[loop.index].getValue().length()}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.leftJustified ==1}">
                                Left
                            </c:if>
                            <c:if test="${item.leftJustified ==0}">
                                Right
                            </c:if>

                        </td>
                        <td>
                                ${item.paddingChar}
                        </td>
                        <td>
                            <c:if test="${item.aggregate ==1}">
                                Yes
                            </c:if>
                            <c:if test="${item.aggregate ==0}">
                                No
                            </c:if>
                        </td>
                        <td>
                                ${item.aggregateFieldName}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
</body>
</html>

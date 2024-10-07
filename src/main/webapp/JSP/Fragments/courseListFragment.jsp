<table id="data table">
    <c:forEach items="${courseData}" var="dataPunkt">
        <tr>
            <c:forEach items="${courseData}" var="dataPunktKolumn">
                <td>${dataPunktKolumn}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
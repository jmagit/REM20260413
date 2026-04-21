<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../parts/header.jsp" %>
<table class="table table-hover table-striped table-bordered">
	<tr>
		<th>Ciudades</th>
		<th>Paises</th>
		<td class="text-end"><a class="btn btn-primary" href="/ciudades/add"><i class="fas fa-plus"></i></a></td>
	</tr>
	<c:forEach var="elemento" items="${listado.getContent()}">
		<tr>
			<td><a href="/ciudades/${elemento.cityId}/${elemento.city}">${elemento.city}</a></td>
			<td>${elemento.country.country}</td>
			<td class="text-end">
				<a href="/ciudades/${elemento.cityId}/edit" class="btn btn-success"><i class="fas fa-pen"></i></a>
				<a href="/ciudades/${elemento.cityId}/delete"class="btn btn-danger"><i class="fas fa-trash"></i></a>
			</td>
		</tr>
	</c:forEach>
</table>
<ul class="pagination">
  	<c:if test = "${listado.hasPrevious()}">
	    <li class="page-item">
	    	<a class="page-link" href="${pageContext.request.contextPath}/ciudades?page=${listado.getNumber()-1}">
	    		<span aria-hidden="true">&laquo;</span>
	    	</a>
	    </li>
	</c:if>
	<c:forEach var = "i" begin = "0" end = "${listado.getTotalPages() -1}">
    	<li class="page-item<c:if test = "${i==listado.getNumber()}"> active</c:if>">
    		<a class="page-link" href="${pageContext.request.contextPath}/ciudades?page=${i}">${i + 1}</a>
    	</li>
	</c:forEach>
  	<c:if test = "${listado.hasNext()}">
	    <li class="page-item">
	    	<a class="page-link" href="${pageContext.request.contextPath}/ciudades?page=${listado.getNumber()+1}">
	    		<span aria-hidden="true">&raquo;</span>
	    	</a>
	    </li>
	</c:if>
</ul>

<%@ include file="../parts/footer.jsp" %>

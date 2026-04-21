<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../parts/header.jsp" %>


<dl>
	<dt><s:message code="entidad.form.id" /></dt>
	<dd>${elemento.actorId}</dd>
	<dt><s:message code="entidad.form.nombre" /></dt>
	<dd>${elemento.firstName}</dd>
	<dt><s:message code="entidad.form.apellidos" /></dt>
	<dd>${elemento.lastName}</dd>
	<dt><s:message code="entidad.form.fecha" /></dt>
	<dd>
		<fmt:parseDate  value="${elemento.lastUpdate}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" />
		<fmt:formatDate pattern = "dd/MMMM/yyyy" value = "${parsedDate}" />
	</dd>
</dl>
<p>
	<a href="/actores" class="btn btn-primary">Volver</a>
</p>
<%@ include file="../parts/footer.jsp" %>
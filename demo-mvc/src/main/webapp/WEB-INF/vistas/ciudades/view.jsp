<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../parts/header.jsp" %>
<dl>
	<dt><s:message code="entidad.form.id" /></dt>
	<dd>${elemento.cityId}</dd>
	<dt>Nombre</dt>
	<dd>${elemento.city}</dd>
	<dt>País</dt>
	<dd>${elemento.country.country}</dd>
</dl>
<p>
	<a href="/ciudades" class="btn btn-primary">Volver</a>
</p>
<%@ include file="../parts/footer.jsp" %>

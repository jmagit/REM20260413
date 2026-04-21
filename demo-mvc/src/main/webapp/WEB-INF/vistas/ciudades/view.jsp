<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="../parts/header.jsp" %>
<dl>
	<dt><s:message code="entidad.form.id" /></dt>
	<dd>${elemento.cityId}</dd>
	<dt>Nombre</dt>
	<dd>${elemento.city}</dd>
	<dt>Paí­s</dt>
	<dd>${elemento.country.country}</dd>
</dl>
<p>
	<a href="/ciudades" class="btn btn-primary">Volver</a>
</p>
<%@ include file="../parts/footer.jsp" %>

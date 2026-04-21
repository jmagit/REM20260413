<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<c:set var="now" value="<%=new java.util.Date()%>" />

	</div>
	<footer class="footer mt-auto py-3">
	  <hr>
	  <div class="container">
	    <span class="text-muted">&copy; <fmt:formatDate value="${now}" pattern="yyyy" /> <s:message code="footer.copyright" /></span>
	  </div>
	</footer>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="/webjars/bootstrap/5.3.7/js/bootstrap.min.js"></script>
</body>
</html>
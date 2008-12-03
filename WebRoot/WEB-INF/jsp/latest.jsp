<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>Latest</h1>
<c:forEach items="${bookList}" var="book">
	<table cellpadding="5">
		<tr>
			<td><img height="64" width="64" src="images/${book.image}" /></td>
			<td>
				<span class="booktitle"> ${book.title}</span>&nbsp;<span class="isbn">ISBN: ${book.isbn}</span><br/>
				<span class="author">${book.author.firstName} ${book.author.latName}</span><br/>
				<span class="copyright">&copy;${book.copyrightDate}</span>
			</td>
		</tr>
	</table>
</c:forEach>
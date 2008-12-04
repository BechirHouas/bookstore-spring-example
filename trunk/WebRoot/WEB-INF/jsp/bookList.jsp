<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>${title}</h1>
<table cellpadding="5">
	<c:forEach items="${bookList}" var="book">
		<tr>
			<td><img height="64" width="64" src="images/${book.image}" /></td>
			<td>
				<span class="booktitle"> ${book.title}</span>&nbsp;<span class="isbn">ISBN: ${book.isbn}</span><br/>
				<span class="author">${book.author.firstName} ${book.author.lastName}</span><br/>
				<span class="copyright">&copy;${book.copyrightDate}</span>
			</td>
			<td>
				<button>Add to Cart</button>
			</td>
		</tr>
	</c:forEach>
</table>
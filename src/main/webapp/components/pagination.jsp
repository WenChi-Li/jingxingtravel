<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div class="container">

			<ul class="pagination justify-content-center">
				<c:if test="${allforums.totalPages>1 }">

					<c:url value="/${systemName }?PageNo=1" var="Firstpage"></c:url>
					<c:url value="/${systemName }?PageNo=${ allforums.totalPages }"
						var="endpage"></c:url>

					<c:url value="/${systemName }?PageNo=${currentPageNo-1 }" var="previouspage"></c:url>
					<c:url value="/${systemName }?PageNo=${currentPageNo+1 }" var="nextpage"></c:url>

					<c:url value="/${systemName }?PageNo=${currentPageNo-2 }" var="previoustwo"></c:url>
					<c:url value="/${systemName }?PageNo=${currentPageNo+2 }" var="nexttwo"></c:url>

					<c:choose>
						<c:when test="${currentPageNo==1}">
							<li class="page-item disabled"><span class="page-link">Previous</span></li>
							<li class="page-item active"><span class="page-link">1</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${previouspage }">Previous</a></li>
							<li class="page-item"><a class="page-link"
								href="${Firstpage }">1</a></li>
						</c:otherwise>
					</c:choose>

					<c:if test="${allforums.totalPages>2 }">
						<c:if test="${currentPageNo>=4 }">
							<li class="page-item"><a class="page-link"
								href="${previoustwo }">...</a></li>
						</c:if>

						<c:choose>
							<c:when test="${currentPageNo==1 && allforums.totalPages>3}">
								<li class="page-item"><a class="page-link"
									href="${nextpage }">2</a></li>
							</c:when>
							<c:when test="${currentPageNo==2 && allforums.totalPages>3}">
								<li class="page-item active"><span class="page-link">2</span></li>
								<li class="page-item"><a class="page-link"
									href="${nextpage }">3</a></li>
							</c:when>
							<c:when
								test="${currentPageNo>=3 && currentPageNo<=allforums.totalPages-2 }">

								<li class="page-item"><a class="page-link"
									href="${previouspage }">${currentPageNo-1 }</a></li>
								<li class="page-item active"><span class="page-link">${currentPageNo }</span></li>
								<li class="page-item"><a class="page-link"
									href="${nextpage }">${currentPageNo+1 }</a></li>
							</c:when>
							<c:when
								test="${currentPageNo==allforums.totalPages-1 && allforums.totalPages>3}">
								<li class="page-item"><a class="page-link"
									href="${previouspage }">${currentPageNo-1}</a></li>
								<li class="page-item active"><span class="page-link">${currentPageNo}</span></li>
							</c:when>
							<c:when
								test="${currentPageNo==allforums.totalPages && allforums.totalPages>3}">
								<li class="page-item"><a class="page-link"
									href="${previouspage }">${currentPageNo-1}</a></li>
							</c:when>

							<c:otherwise>
								<c:choose>
									<c:when test="${currentPageNo==2 }">
										<li class="page-item active"><span class="page-link">${allforums.totalPages-1 }</span></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a class="page-link"
											href="/${systemName }?PageNo=2">${allforums.totalPages-1 }</a></li>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>

						<c:if test="${currentPageNo<=allforums.totalPages-3}">
							<li class="page-item"><a class="page-link"
								href="${nexttwo }">...</a></li>
						</c:if>
					</c:if>


					<c:choose>
						<c:when test="${currentPageNo==allforums.totalPages}">
							<li class="page-item active"><span class="page-link">${allforums.totalPages }</span></li>
							<li class="page-item disabled"><span class="page-link">Next</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${endpage }">${allforums.totalPages }</a></li>
							<li class="page-item"><a class="page-link"
								href="${nextpage }">Next</a></li>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:if test="${allforums.totalPages<=1 }">
					<li class="page-item disabled"><span class="page-link">Previous</span></li>
					<li class="page-item active"><span class="page-link">1</span></li>
					<li class="page-item disabled"><span class="page-link">Next</span></li>
				</c:if>
			</ul>
		</div>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="css" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet">
        <link href="<css:url value="/css/displaypizza.css"></css:url>" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
    <jsp:include page="header.jsp"></jsp:include>
        <sql:setDataSource driver="org.apache.derby.jdbc.ClientDriver" 
                           url="jdbc:derby://localhost:1527/pizza404"
                           user="boot"
                           password="boot"
                           var="mydatasource"/>
        <div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
                                            <input type="hidden" value="${pi.pizza_id}"/>
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="${pi.imglink}" /></div>
						  
						</div>
						
						
					</div>
					<div class="details col-md-6">
						<h3 class="product-title">${pi.pizza_name}</h3>
						
						<p class="product-description">${pi.description}</p>
						<h4 class="price">current price: <span>${pi.price}</span></h4>
						
						<div class="action">
							<a class="btn btn-warning" href="${pi.link}">+ Order</a>
                                                        <a href="EditController?pizza_id=${pi.pizza_id}" class="btn btn-info">Edit</a>
                                                        <a class="btn btn-danger" href="DeleteController?pizza_id=${pi.pizza_id}">Delete</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <jsp:include page="footer.jsp"></jsp:include>                                            
    </body>
</html>

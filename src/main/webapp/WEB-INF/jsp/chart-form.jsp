<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head>
		<title>Inputs for a Chart</title>
		<style>
			form {
				display: inline
			}
			
			* {
				font-size: 16pt;
				line-height: 16pt;
			}
			
			h1 {
				font-size: 20pt
			}
			
			td {
				padding: 8pt;
			}
		</style>
	</head>
	<body>
		<h1>Inputs for a Graph</h1>
		
		<table>
			<tbody>
				<c:forEach items="${dateValues}" var="dateval">
					<tr>
						<td>${dateval.date}</td>
						
						<td>
							${dateval.value}
							<form action="/chart/${dateval.date}/up" method="POST">
								<input type="submit" value="UP" />
							</form>
							
							<form action="/chart/${dateval.date}/down" method="POST">
								<input type="submit" value="DOWN" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</body>
</html>
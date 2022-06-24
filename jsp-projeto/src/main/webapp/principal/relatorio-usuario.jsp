<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html lang="pt-br">

<!-- HEAD -->
<jsp:include page="head.jsp"></jsp:include>

<body>

	<!--PRE-LOADER START -->
	<jsp:include page="theme-loader.jsp"></jsp:include>


	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- BARRA DE NAVEGACAO -->
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- MENU LATERAL -->
					<jsp:include page="menulateral.jsp"></jsp:include>



					<div class="pcoded-content">


						<!-- PAGINA BOAS VINDAS -->
						<jsp:include page="welcomepage.jsp"></jsp:include>


						<div class="pcoded-inner-content">
							<div class="main-body">
								<div class="page-wrapper">
									<div class="page-body">
										<!-- BODY -->

										<div class="card">
											<div class="card-header">
												<h5>Relatorio de Usuarios</h5>
												<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
											</div>
											<div class="card-block">

												<form action="<%= request.getContextPath()%>/principal/ServletUsuario" method="GET" class="form-material" id="formulario">
													<input type="text" name="acao" value="imprimirRelatorio" hidden="">
													<div class="form-row align-items-center">
														<div class="col-auto">
															<label>Data Inicial:</label>
															<input name="dataInicial" value="${dataInicial}" type="date" name="" class="form-control mb-2" >
														</div>
														
														<div class="col-auto">
															 <label>Data Final:</label>
															<input name="dataFinal" value="${dataFinal}" type="date" class="form-control mb-2" >
														</div>
														 
														<div class="col-auto">
															<button type="submit" class="btn btn-primary mb-2">imprimir relatorio</button>
														</div>
													</div>


													<!-- TABELA -->
	                                            	<div style="height: 500px; overflow: scroll;">
													<table class="table" id="tabelausuarios">
													  <thead>
													    <tr>
													      <th scope="col">ID</th>
													      <th scope="col">Nome</th>
													       
													      
													    </tr>
													  </thead>
													  
													  <tbody>
													     <c:forEach items="${listaUsuario}" var="u">
													     	<tr>
													     		<td><c:out value="${u.id}"></c:out></td>
													     		<td><c:out value="${u.nome}"></c:out></td>
													     	</tr>
													     	
													     </c:forEach>
													     
													  </tbody>
													</table>
		
													</div>

												</form>


											</div>
										</div>
									</div>
									<!-- FIM BODY -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- JavaScript page -->
	<jsp:include page="jspage.jsp"></jsp:include>

</body>

</html>

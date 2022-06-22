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
                                    <div class="page-body"><!-- BODY -->
                                            
                                            <div class="card">
                                                    <div class="card-header">
                                                        <h5>Telefone</h5>
                                                    </div>
                                                    <div class="card-block">
                                                    
                                                    	<form action="<%= request.getContextPath()%>/principal/ServletTelefone" method="POST" class="form-material" id="formulario">
                                                    		
                                                    		<div class="form-group form-default form-static-label">
                                                                <input value="${usuario.id}" type="text" name="idUsuario" id="idUsuario" class="form-control" placeholder="Enter User Name" required="" readonly="readonly">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID Usuario</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default form-static-label">
                                                                <input value="${usuario.nome}" type="text" name="nome" id="nome" class="form-control" placeholder="Enter User Name" required="" readonly="readonly">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">nome</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input onkeypress="return somenteNumeros(event)" type="text" name="numero" id="numero" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">numero</label>
                                                            </div>
                                                    		
                                                    		<button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
                                                    		
                                                    	</form>
                                                    	
                                                    </div>
                                            </div>
                                            
                                            <span id="msg">${msg}</span>
                                            
                                            
                                            
                                            <!-- TABELA -->
	                                            	<div style="height: 300px; overflow: scroll;">
													<table class="table" id="tabelausuarios">
													  <thead>
													    <tr>
													      <th scope="col">ID</th>
													      <th scope="col">Numero</th>
													      <th scope="col">Excluir</th>
													      
													    </tr>
													  </thead>
													  <tbody>
													     <c:forEach items="${listaTelefone}" var="t">
													     	<tr>
													     		<td><c:out value="${t.id}"></c:out></td>
													     		<td><c:out value="${t.numero}"></c:out></td>
													     		<td><a href="<%= request.getContextPath() %>/principal/ServletTelefone?acao=deletarTelefone&idTelefone=${t.id}&idUsuario=${usuario.id}" class="btn btn-danger" >Excluir</a></td>
													     	</tr>
													     	
													     </c:forEach>
													     
													  </tbody>
													</table>
													
													</div>
                                            
                                            
                                            
                                            
                                            
                                    </div><!-- FIM BODY -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	
 	// ACEITA SOMENTE NUMEROS
	// input >> onkeypress="return somenteNumeros(event)"
	function somenteNumeros(e) {
    var charCode = e.charCode ? e.charCode : e.keyCode;
     
        if (charCode != 8 && charCode != 9) {
            
            if (charCode < 48 || charCode > 57) {
                return false;
            }
        }
	}
 	 
    </script>
    
    <!-- JavaScript page -->
    <jsp:include page="jspage.jsp"></jsp:include>
    
</body>

</html>

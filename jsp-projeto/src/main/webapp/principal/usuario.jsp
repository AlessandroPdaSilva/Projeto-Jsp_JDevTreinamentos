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
                                                        <h5>Cadastro de Usuarios</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                    	  
                                                    	 <!-- FORMULARIO -->
                                                        <form action="<%= request.getContextPath()%>/ServletUsuario" method="POST" enctype="multipart/form-data" class="form-material" id="formulario">
                                                        	<div class="form-group form-default form-static-label">
                                                                <input value="${usuario.id}" type="text" name="id" id="id" class="form-control" placeholder="Enter User Name" required="" readonly="readonly">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                        
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.nome}" type="text" name="nome" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.email}" type="email" name="email" id="email" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email</label>
                                                            </div>
                                                            
                                                            
                                                            
                                                             <c:if test="${login.perfil == 'ADMINISTRADOR'}">
	                                                            <div class="form-group form-default">
		                                                            <select name="perfil" class="form-control" aria-label="Default select example" required="required">
																	  <option <c:if test="${usuario.perfil == null}"> selected="selected" </c:if> disabled="disabled">Selecione um perfil</option>
																	  <option value="ADMINISTRADOR" <c:if test="${usuario.perfil == 'ADMINISTRADOR'}"> selected="selected" </c:if>>Administrador</option>
																	  <option value="SECRETARIO" <c:if test="${usuario.perfil == 'SECRETARIO'}"> selected="selected" </c:if>>Secretario</option>
																	  <option value="USUARIO" <c:if test="${usuario.perfil == 'USUARIO'}"> selected="selected" </c:if> >Usuario</option>
																	</select>
	                                                            </div>
                                                             </c:if>
                                                             
                                                             
                                                             
                                                              
                                                             <div class="form-group form-default">
                                                                <input onblur="localizarCep()" value="${usuario.cep}" type="text" name="cep" id="cep" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Cep</label>
                                                            </div> 
                                                            
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.logradouro}" type="text" name="logradouro" id="logradouro" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Logradouro</label>
                                                            </div>
                                                              
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.bairro}" type="text" name="bairro" id="bairro" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Bairro</label>
                                                            </div>
															             
															<div class="form-group form-default">
                                                                <input value="${usuario.localidade}" type="text" name="localidade" id="localidade" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Localidade</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.uf}" type="text" name="uf" id="uf" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">UF</label>
                                                            </div>
                                                                      
                                                                      
                                                                                                           	
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.login}" type="text" name="login" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input value="${usuario.senha}" type="password" name="senha" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                              <div class="form-group">
															    <label for="exampleFormControlFile1">Imagem de perfil</label><br>
															    
															    <img src="${usuario.fotoBase64}" id="imgfoto" width="70px">
															    <input type="file" id="filefoto" name="filefoto" accept="image/*" onchange="visualizarImg('imgfoto','filefoto')" class="form-control-file" id="exampleFormControlFile1" style="margin-top: 15px;">
															  </div>
                                                            
                                                             
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm()">Novo</button>
                                                            <button type="submit" class="btn btn-success waves-effect waves-light">Salvar</button>
                                                            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="excluirUsuario()">Excluir</button>
                                                            <!-- Button trigger modal -->
															<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#pesquisarUsuario">
															  Pesquisar Usuario
															</button>
                                                        </form>
                                                    </div>
                                                </div>
                                                
                                                
                                                <!-- MENSAGEM -->
                                                <span id="msg">${msg}</span>
                                            
                                            		<!-- TABELA -->
	                                            	<div style="height: 300px; overflow: scroll;">
													<table class="table" id="tabelausuarios">
													  <thead>
													    <tr>
													      <th scope="col">ID</th>
													      <th scope="col">Nome</th>
													      <th scope="col">Ver</th>
													      
													    </tr>
													  </thead>
													  <tbody>
													     <c:forEach items="${listaUsuario}" var="u">
													     	<tr>
													     		<td><c:out value="${u.id}"></c:out></td>
													     		<td><c:out value="${u.nome}"></c:out></td>
													     		<td><a href="<%= request.getContextPath() %>/ServletUsuario?acao=verEditar&id=${u.id}" class="btn btn-success" >Ver</a></td>
													     	</tr>
													     	
													     </c:forEach>
													     
													  </tbody>
													</table>
													
													</div>
															
		                                            <nav aria-label="Page navigation example">
													  <ul class="pagination">
													    
													    <%
													    	int totalPagina = (int) request.getAttribute("totalPagina");	
													    
													    	for(int i = 0; i < totalPagina; i++){
													    		String url = request.getContextPath()+ "/ServletUsuario?acao=paginacao&offset="+(i*5);
													    		out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+ url+"\"> "+ (i+1) +"</a></li>");
													    	}
													    %>
													    
													    
													  </ul>
													</nav>	
		                                            
		                                            
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
    
    
	    	<!-- MODAL PEQUISA -->
			<div class="modal fade" id="pesquisarUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Pesquisar Usuario</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body"><!-- Corpo -->
			        
						<div class="input-group mb-3">
						  <input id="buscaNome" type="text" class="form-control" placeholder="Pesquisar nome" aria-label="Recipient's username" aria-describedby="basic-addon2">
						  <div class="input-group-append">
						    <button onclick="bucarUsuario()" class="btn btn-success" type="button" >Enviar</button>
						  </div>
						</div>
						
							<div style="height: 300px; overflow: scroll;">
							<table class="table" id="tabelapesquisa">
							  <thead>
							    <tr>
							      <th scope="col">ID</th>
							      <th scope="col">Nome</th>
							      <th scope="col">Ver</th>
							      
							    </tr>
							  </thead>
							  <tbody>
							     
							  </tbody>
							</table>
							</div>
						

			      </div>
			      
			      <span id="totalresultados"> </span>
			      
			      
			      <div class="modal-footer"><!-- fim corpo -->
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">fechar</button>
			        
			      </div>
			    </div>
			  </div>
			</div>


    
    
    <!-- JAVASCRIPT -->
    <script type="text/javascript">
    
    	// LOCALIZAR CEP
    	function localizarCep(){
    		var cep = $("#cep").val();
    		
    		$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#logradouro").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#localidade").val(dados.localidade);
                    $("#uf").val(dados.uf);
                     
                    
                }else {
                    //CEP pesquisado não foi encontrado.
                    limpa_formulário_cep();
                    alert("CEP não encontrado.");
                }
            });
    		
    	}
    	
    
    	// VISUALIZAR FOTO
    	function visualizarImg(imgfoto,filefoto){
    		
    		var imgFoto = document.getElementById(imgfoto);
    		var fileFoto = document.getElementById(filefoto).files[0];
    		var reader = new FileReader();
    		
    		reader.onload = function(){
    			imgFoto.src = reader.result;
    		};
    		
    		if(fileFoto){
    			reader.readAsDataURL(fileFoto);
    		}else{
    			imgFoto.src = '';
    		}
    		
    	}
    		
	 	// VER EDITAR
		function verEditar(id){
	 		
			var urlAction = document.getElementById('formulario').action;
			
			window.location.href = urlAction + "?acao=verEditar&id=" + id;
			
		}
    
    	// BUSCAR USUARIO
    	function bucarUsuario(){
    		
    		var buscaNome = document.getElementById("buscaNome").value;
    		
    		if(buscaNome != null && buscaNome != "" && buscaNome.trim() != ""){
    			
    			var urlAction = document.getElementById('formulario').action;
    			
    			$.ajax({
    				
   				 method: "get",
   			     url : urlAction,
   			     data : "buscarNome=" + buscaNome + '&acao=buscarUsuario',
   			     success: function (response) {
   				 
   			    	  var json = JSON.parse(response); 
   			     
   			    	
   			    	  $('#tabelapesquisa > tbody > tr').remove();
   				 
		   			  for(var p = 0; p < json.length; p++){
		   			      $('#tabelapesquisa > tbody').append('<tr> <td>'+json[p].id+'</td> <td> '+json[p].nome+'</td> <td><button onclick="verEditar('+ json[p].id +')" type="button" class="btn btn-info">Ver</button></td></tr>');
		   			  		
		   			  }
   			  
		   			document.getElementById("totalresultados").textContent = "Resultados: " + json.length;
   				  	
		   			
   			     }
   			     
   				
    			
   			}).fail(function(xhr, status, errorThrown){
   				
   				alert('Erro ao Buscar usuário ' + xhr.responseText);
   				
   			})
    		}
    	}
    	
    
    	// LIMPAR FORM
    	function limparForm() {
    		var elementos = document.getElementById("formulario").elements; /*Retorna os elementos html dentro do form*/
    	    
    	    for (p = 0; p < elementos.length; p ++){
    		    elementos[p].value = '';
    	    }
		}
    	
    	// EXCLUIR USUARIO
    	function excluirUsuario(){
    		if(confirm("Deseja realmente deletar os dados ??")){
    			
    			var urlAction = document.getElementById('formulario').action;
    			var idUser = document.getElementById('id').value;
    			
    			$.ajax({
    				
    				 method: "get",
    			     url : urlAction,
    			     data : "id=" + idUser + '&acao=deletar',
    			     success: function (response) {
    				 
    				  limparForm();
    				  document.getElementById('msg').textContent = response;
    			     }
    			     
    				
    			}).fail(function(xhr, status, errorThrown){
    				
    				alert('Erro ao deletar usuário por id: ' + xhr.responseText);
    				
    			})
    			
    		}
    	}
    	
    </script>
    
    <!-- JavaScript page -->
    <jsp:include page="jspage.jsp"></jsp:include>
    
</body>

</html>

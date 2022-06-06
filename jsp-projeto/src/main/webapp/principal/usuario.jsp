<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
                                                    	  
                                                        <form action="<%= request.getContextPath()%>/ServletUsuario" method="POST" class="form-material" id="formulario">
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
                                                
                                                <span id="msg">${msg}</span>
                                            
                                            
                                            
                                            
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
    
    
	    	<!-- Modal -->
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
						
						
							<table class="table">
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
			      <div class="modal-footer"><!-- fim corpo -->
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">fechar</button>
			        
			      </div>
			    </div>
			  </div>
			</div>


    
    
    <script type="text/javascript">
    
    	function bucarUsuario(){
    		
    		var buscaNome = document.getElementById("buscaNome").value;
    		
    		if(buscaNome != null && buscaNome != "" && buscaNome.trim() != ""){
    			
    			var urlAction = document.getElementById('formulario').action;
    			
    			$.ajax({
    				
   				 method: "get",
   			     url : urlAction,
   			     data : "buscarNome=" + buscaNome + '&acao=buscarUsuario',
   			     success: function (response) {
   				 
   				  
   				  document.getElementById('msg').textContent = response;
   				  
   			     }
   			     
   				
    			
   			}).fail(function(xhr, status, errorThrown){
   				
   				alert('Erro ao Buscar usuário ' + xhr.responseText);
   				
   			})
    		}
    	}
    
    	function limparForm() {
    		var elementos = document.getElementById("formulario").elements; /*Retorna os elementos html dentro do form*/
    	    
    	    for (p = 0; p < elementos.length; p ++){
    		    elementos[p].value = '';
    	    }
		}
    	
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

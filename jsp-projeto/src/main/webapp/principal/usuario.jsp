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
                                                    	  
                                                        <form action="<%= request.getContextPath()%>/ServletUsuario" method="POST" class="form-material" >
                                                        	<div class="form-group form-default form-static-label">
                                                                <input value="${usuario.id}" type="text" name="id" class="form-control" placeholder="Enter User Name" required="" readonly="readonly">
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
                                                            
                                                             
                                                            <button class="btn btn-primary waves-effect waves-light">Novo</button>
                                                            <button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                            <button class="btn btn-danger waves-effect waves-light">Excluir</button>
                                                            
                                                        </form>
                                                    </div>
                                                </div>
                                                
                                                <span>${msgErro}</span>
                                            
                                            
                                            
                                            
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
    
    
    <!-- JavaScript page -->
    <jsp:include page="jspage.jsp"></jsp:include>
    
</body>

</html>

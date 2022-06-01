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
                                                        <form class="form-material">
                                                            <div class="form-group form-default">
                                                                <input type="text" name="footer-email" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Nome</label>
                                                            </div>
                                                            
                                                            <div class="form-group form-default">
                                                                <input type="password" name="footer-email" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                             
                                                            <button class="btn btn-primary waves-effect waves-light">Enviar</button>
                                                        </form>
                                                    </div>
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
    
    
    <!-- JavaScript page -->
    <jsp:include page="jspage.jsp"></jsp:include>
    
</body>

</html>

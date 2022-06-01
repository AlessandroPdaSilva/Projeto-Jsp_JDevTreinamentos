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
                                            
                                            <h1> Ola mundo </h1>
                                            
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

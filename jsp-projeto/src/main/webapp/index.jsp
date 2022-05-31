<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<!-- Meta tags Obrigatórias -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	
	<title>Projeto JSP</title>
	
	<style type="text/css">
		.caixa{
			display: flex;
			flex-direction:column;
			align-content: center;
			justify-content: center;
			padding:1% 30%;
			
			height: 100vh;
    		width: 100vw;
		}
		
		.titulo{	
			text-align: center;
		}
		
		.msg{
			color: red;
		}
		
		#botao{
			margin-top: 2%;
		}
			
	</style>
	
</head>

<body>
	
	<div class="caixa">
		<h1 class="titulo"> Formulario </h1>
		
		<form action="<%= request.getContextPath() %>/ServletLogin" method="POST" class="row g-3 row g-3 needs-validation" novalidate>
			 
			<div class="col-md-6">
				<label class="form-label"> Login:</label>
				<input name="login" type="text" class="form-control" required>
					 
				    <div class="invalid-feedback">
				      preencha o campo
				    </div>
			</div>
			
			<div class="col-md-6">
				<label class="form-label">Senha:</label>
				<input name="senha" type="password" class="form-control" required>
					 
				    <div class="invalid-feedback">
				      preencha o campo
				    </div>
			</div>
		
			
			<div class="col-12" id="botao">
				<input type="submit" value="enviar" class="btn btn-primary"> 
			</div>
			
		</form>
		<br>
		
		<div class="msg">${msgErro}</div>
		
		
	</div>




	<!--Bootstrap Validacao -->
	<script type="text/javascript">
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
	</script>
	
    <!--Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
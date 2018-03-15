<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transcal</title>
<link type="text/css" rel="stylesheet" href="../css/estilo.css" />
<link type="text/css" rel="stylesheet" href="../css/estiloForm.css" />
<link type="text/css" rel="stylesheet" href="../css/estiloRelatorio.css" />
<link type="text/css" rel="stylesheet" href="../css/estiloGrid.css" />
<link type="text/css" rel="stylesheet" media="print"	href="../css/printRelatorio.css">

<script type="text/javascript" src="../js/jsGlobal.js"></script>
<script type="text/javascript" src="../js/jsRelatorios.js"></script>
</head>

<%
	// b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar, b7 = imprimir
	String b3visivel = "type = " + "image";
	String b5visivel = "type = " + "hidden";
	String b6visivel = "type = " + "image";
	String b7visivel = "class=\"oculto\"";
	String exibirFiltros = "";
%>

<body>
	<br>
	<div id="conteudo">
		<br>
		<h1>
			<a>Cadastro de Clientes</a>
		</h1>
		<div>
			<form name="filtroRelCadClientes" id="filtroRelCadClientes"
				method="post" action="relatCadClientes.jsp"
				onsubmit="return validarFiltrosCadClientes()">
				<div class="alerta" id="alerta1"></div>
				<br> <label class="tamData"><a id="alabelperiodo">Data
						de Cadastro</a> <input type="date" class="data" name="dataInicio"
					id="idDataInicio" maxlength="10" /> </label> <label class="tamB">&nbsp;
					<input type="text" class="vazio" name="espacovazio"
					disabled="disabled" value="até" />
				</label> <label class="tamData">&nbsp; <input type="date"
					class="data" name="dataFim" id="idDataFim" maxlength="10" />
				</label> <label class="tam2">Cidade <input class="textfield"
					name="cidade" id="idcidade" type="text" class="textfield"
					tabindex="7" />
				</label> <br> <input type="hidden" id="idclicado" name="clicado"
					value="0"> <br>
				<hr>
				<table id="menu" align="center">
					<tr>
						<td><input class="button1" type="image"
							src="../images/botao_cancelar.png" alt="cancelar"
							value="cancelar" onClick="botaoClicado(3)" tabindex="17" /></td>
						<td><input class="button1" type="image"
							src="../images/botao_executar.png" alt="executar"
							value="executar" onClick="botaoClicado(6)" tabindex="18" /></td>
					</tr>
				</table>
			</form>
		</div>
		<hr>
	</div>
</body>
</html>
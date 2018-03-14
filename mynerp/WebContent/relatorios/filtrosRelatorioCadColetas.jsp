<%@page import="br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<%@page import="br.com.mynerp.persistencia.enumerate.PessoaEnum"%>
<%@page import="br.com.mynerp.persistencia.*"%>
<%@page import="java.util.*;"%>
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
	PessoaFacade pessoaFacade = new PessoaFacade();
	Iterator<Cliente> itListRelCliente = null;	
	Cliente cli = null;		
	
	// b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar, b7 = imprimir
				
	String b3visivel = "type = "+"image";			
	String b5visivel = "type = "+"hidden";
	String b6visivel = "type = "+"image";
	String b7visivel = "class=\"oculto\"";
	String exibirFiltros = "";
				
	List<Cliente> listCliente = pessoaFacade.pesquisar(PessoaEnum.CLIENTE);
	Iterator<Cliente> itListCliente = listCliente.iterator();
	%>
<body>
	<br>
	<div id="conteudo">
		<br>
		<h1>
			<a>Coletas Cadastradas</a>
		</h1>
		<div>
			<form name="filtroRelCadColetas" id="filtroRelCadColetas"
				method="post" action="relatCadColetas.jsp"
				onsubmit="return validarFiltrosCadColetas()">
				<div class="alerta" id="alerta1"></div>

				<br> <label class="tam3" id="idcliente"> Cliente <select
					name="cliente" id="idcliente" class="select" tabindex="2">
						<option value="" class="selected" selected="selected"></option>
						<%	
	    					while (itListCliente.hasNext()) {
	    							Cliente cliente = (Cliente)itListCliente.next();
	    	   			%>
						<option value="<%=cliente.getId()%>"><%=""+cliente.getId() + " - " +cliente.getNome()%></option>
						<% 
	           				}
	    	    		%>
				</select>
				</label> <label class="tamData"> Data Inicial <input type="date"
					class="data" name="dataInicio" id="idDataInicio" maxlength="10"
					onkeypress="return dateMask(this, event)"
					onchange="validaData(this)" />
				</label> <label class="tamB">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" value="até" />
				</label> <label class="tamData"> Data Final <input type="date"
					class="data" name="dataFim" id="idDataFim" maxlength="10"
					onkeypress="return dateMask(this, event)" />
				</label> <label class="tam2">Local de Coleta <input class="textfield"
					name="cidade" id="idcidade" type="text" class="textfield"
					tabindex="7" />
				</label> <br> <label id="idPagos" class="tam1"> <input
					type="checkbox" name="pagos" id="idPagos" value="1"
					checked="checked" tabindex="20"> Pagos
				</label> <label id="idNaoPagos" class="tam2"> <input type="checkbox"
					name="naoPagos" id="idNaoPagos" value="1" checked="checked"
					tabindex="21"> Não Pagos
				</label> <input type="hidden" id="idclicado" name="clicado" value="0">
				<br>
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
				<hr>
			</form>
		</div>
	</div>
</body>
</html>
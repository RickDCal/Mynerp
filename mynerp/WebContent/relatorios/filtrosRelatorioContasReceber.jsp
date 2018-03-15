<%@page	import="br.com.mynerp.apresentacao.facade.estaticos.ListaTabelasFacade"%>
<%@page import="br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" 	pageEncoding="ISO-8859-1"%>
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
	ListaTabelasFacade listasFacade = new ListaTabelasFacade();

	Iterator<Cliente> itListRelCliente = null;
	Iterator<Cobranca> itListCobrancaReceber = null;

	Cliente cli = null;

	// b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar, b7 = imprimir

	String b3visivel = "type = " + "image";
	String b5visivel = "type = " + "hidden";
	String b6visivel = "type = " + "image";
	String b7visivel = "class=\"oculto\"";
	String exibirFiltros = "";

	List<Cliente> listCliente = pessoaFacade.pesquisar(PessoaEnum.CLIENTE);
	Iterator<Cliente> itListCliente = listCliente.iterator();

	List<Cobranca> listCobrancaReceber = listasFacade.pesquisarCobrancas(2);
	itListCobrancaReceber = listCobrancaReceber.iterator();
%>

<body>
	<br>
	<div id="conteudo">
		<br>
		<h1>
			<a>Financeiro - Contas a Receber</a>
		</h1>
		<div>
			<form name="filtroRelatorioContasReceber"
				id="formFiltroRelatorioContasReceber" method="post"
				action="relatContasReceber.jsp"
				onsubmit="return validarFiltrosContasReceber(this.id)">
				<div class="alerta" id="alerta1"></div>
				<br> 
				<label class="tam2"> Ordenação <select
					name="ordenacao" id="ordenacao" class="select" tabindex="1">
						<option value="p.dataVencimento">Data de Vencimento</option>
						<option value="p.dataEmissao">Data de Emissão</option>
						<option value="c.pessoa.nome">Nome do Fornecedor</option>
				</select>
				</label> <label class="tam2"> Recebimento <select name="pagamento"
					id="pagamento" class="select" tabindex="2">
						<option value="0">Todos</option>
						<option value="1">Somente Recebidos</option>
						<option value="2">Somente Não Recebidos</option>
				</select>
				</label> <br>
				<hr>
				<br> <label class="tam3" id="labelcliente"> Cliente <select
					name="cliente" id="cliente" class="select" tabindex="3">
						<option value=""></option>
						<%
							Iterator<Cliente> itListCliente2 = listCliente.iterator();
							while (itListCliente2.hasNext()) {
								Cliente cliente = (Cliente) itListCliente2.next();
						%>
						<option value="<%=cliente.getId()%>"><%="" + cliente.getId() + " - " + cliente.getNome()%></option>
						<%
							}
						%>
				</select>
				</label> <label class="tam3" id="labelcob"> Forma de Cobranca <select
					name="cobranca" id="cobranca" class="select" tabindex="3">
						<option value="0"></option>
						<%
							while (itListCobrancaReceber.hasNext()) {
								Cobranca cobranca = (Cobranca) itListCobrancaReceber.next();
						%>
						<option value="<%=cobranca.getId()%>"><%="" + cobranca.getId() + " - " + cobranca.getNome()%></option>
						<%
							}
						%>
				</select>
				</label> <br>
				<hr>
				<br> <label class="tamData">Emissão inicial <input
					type="date" class="data" name="emissaoInicio" id="emissaoInicio"
					maxlength="10" />
				</label> <label class="tamB">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" value="até" />
				</label> <label class="tamData">Emissao Final <input type="date"
					class="data" name="emissaoFim" id="emissaoFim" maxlength="10" />
				</label> <label class="tam0">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" />
				</label> <label class="tamData">Vencimento inicial <input
					type="date" class="data" name="vencimentoInicio"
					id="vencimentoInicio" maxlength="10" />
				</label> <label class="tamB">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" value="até" />
				</label> <label class="tamData">Vencimento Final <input type="date"
					class="data" name="vencimentoFim" id="vencimentoFim" maxlength="10" />
				</label> <label class="tam0">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" />
				</label> <label class="tamData">Pagamento inicial <input type="date"
					class="data" name="pagamentoInicio" id="pagamentoInicio"
					maxlength="10" />
				</label> <label class="tamB">&nbsp; <input type="text" class="vazio"
					name="espacovazio" disabled="disabled" value="até" />
				</label> <label class="tamData">Pagamento Final <input type="date"
					class="data" name="pagamentoFim" id="pagamentoFim" maxlength="10" />
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
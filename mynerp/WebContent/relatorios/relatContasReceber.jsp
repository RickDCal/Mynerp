<%@page import="br.com.mynerp.apresentacao.facade.financeiro.ContaFacade"%>
<%@page import="br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade"%>
<%@page import="br.com.mynerp.persistencia.enumerate.ContaEnum"%>
<%@page import="org.omg.CORBA.DataInputStream"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.mynerp.persistencia.enumerate.PessoaEnum"%>
<%@page import="br.com.mynerp.negocio.*"%>
<%@page import="br.com.mynerp.persistencia.*"%>
<%@page import="br.com.mynerp.apresentacao.*"%>
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
<link type="text/css" rel="stylesheet" media="print" href="../css/printRelatorio.css">

<script type="text/javascript" src="../js/jsGlobal.js"></script>
<script type="text/javascript" src="../js/jsRelatorios.js"></script>
</head>

<%
	

	SimpleDateFormat dthbr = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	SimpleDateFormat dthen = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	SimpleDateFormat dtbr = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dten = new SimpleDateFormat("yyyy-MM-dd");
	//DecimalFormat df = new DecimalFormat("#.###,00");

		
	String relatorio = "Relatório: Cadastro de Contas a Receber";
	String emissaoRelatorio = "";
	String periodoEmissao = "";
	Date emissaoInicio = null;
	Date emissaoFim = null;
	String periodoVencimento = "";
	Date vencimentoInicio = null;
	Date vencimentoFim = null;
	String periodoQuitacao = "";
	Date quitacaoInicio = null;
	Date quitacaoFim = null;
	
	Integer idPagamento = Integer.parseInt(request.getParameter("pagamento"));
	Integer idCobranca = Integer.parseInt(request.getParameter("cobranca"));
	
	String pagamento = "";
	
	switch (idPagamento) {
	case 1: pagamento = " |	Somente Pagos"; break;	
	case 2: pagamento = " |	Somente Não Pagos"; break;
	}
	
	
	String codCliente = request.getParameter("cliente");
	String nomeCliente ="";
	String rotulos ="";
	String dados = "";
	String rodape = "";
	String order = request.getParameter("ordenacao");
	
	PessoaFacade pessoaFacade = new PessoaFacade();
	ContaFacade contaFacade = new ContaFacade();
		
	Cliente cli = null;
	
	if (codCliente!= null && !codCliente.isEmpty()) {			
			Integer idCliente = Integer.parseInt(codCliente);	
			cli = (Cliente)pessoaFacade.pesquisar(PessoaEnum.CLIENTE, idCliente);
			nomeCliente = "|	Fornecedor: " + cli.getNome();
			
		}
			
	Date date = new Date();
	emissaoRelatorio = dtbr.format(date);
	
	String parametro = request.getParameter("emissaoInicio");		 
		 if (parametro!= null && !parametro.isEmpty()) {
				emissaoInicio = dten.parse(parametro);
				periodoEmissao = "|	Dt. Emissão: A partir de " + dtbr.format(emissaoInicio);								
			}
			
		 parametro = request.getParameter("emissaoFim");		 
			if (parametro != null && !parametro.isEmpty()) {
				emissaoFim = dten.parse(parametro);
				emissaoFim.setHours(23);
				emissaoFim.setMinutes(59);
				periodoEmissao = periodoEmissao + " até " + dtbr.format(emissaoFim);							
			}	
			
		parametro = request.getParameter("vencimentoInicio");		 
			if (parametro != null && !parametro.isEmpty()) {
				vencimentoInicio = dten.parse(parametro);
				periodoVencimento = "|	Dt. Vencimento: A partir de " + dtbr.format(vencimentoInicio);								
			}
			
		parametro = request.getParameter("vencimentoFim");		 
		if (parametro != null && !parametro.isEmpty()) {
			vencimentoFim = dten.parse(parametro);
			vencimentoFim.setHours(23);
			vencimentoFim.setMinutes(59);
			periodoVencimento = periodoVencimento + " até " + dtbr.format(vencimentoFim);							
		}
		
		parametro = request.getParameter("pagamentoInicio");		 
		if (parametro != null && !parametro.isEmpty()) {
			quitacaoInicio = dten.parse(parametro);
			periodoQuitacao = "|	Dt. Quitação: A partir de " + dtbr.format(quitacaoInicio);								
		}
		
		parametro = request.getParameter("pagamentoFim");		 
		if (parametro != null && !parametro.isEmpty()) {
			quitacaoFim = dten.parse(parametro);
			quitacaoFim.setHours(23);
			quitacaoFim.setMinutes(59);
			periodoQuitacao = periodoQuitacao + " até " + dtbr.format(quitacaoFim);							
		}
			
	List<Parcela> listRelCtaReceber = contaFacade.pesquisarParcelas(ContaEnum.RECEBER, emissaoInicio, emissaoFim, vencimentoInicio, vencimentoFim, quitacaoInicio, quitacaoFim, cli, idPagamento, null, null, order);//pesquisarCP();
	Iterator<Parcela> itListRelCtaReceber = null;
			
			itListRelCtaReceber = listRelCtaReceber.iterator();
			
	
	
	// b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar, b7 = imprimir
				
	String b3visivel = "type = "+"image";			
	String b5visivel = "type = "+"hidden";
	String b6visivel = "type = "+"image";
	String b7visivel = "class=\"oculto\"";
	String exibirFiltros = "";
			

		b3visivel = "type = "+"hidden";			
		b5visivel = "type = "+"image";
		b6visivel = "type = "+"hidden";	
		b7visivel = "";
		exibirFiltros = "class=\"oculto\"";
		

	%>

<body>

	<br>
	<div id = "conteudo">
		<div class="noprint">
		<hr>	 	
	 	
		 	<div class="subtitulo">Opções de Relatório </div>
		 	<br>
		 	<div>
				
				<div <%=b7visivel%> >
				<input class="button1"  <%=b5visivel%> src="../images/botao_voltar.png" alt="voltar" value="voltar" onClick="botaoClicado(5)" tabindex="19" />	   			
	   			<a href="javascript:window.print();" display = "none"<%=b5visivel%> ><img src="../images/botao_imprimir.png" alt="imprimir"></a>	   			
	   			</div>
	   		<hr>		
		 </div>
		 </div>	
		 <div id="dadosRelatorio">
		 
		 	<img class="oculto imprimir" src="<%= "../images/" + session.getAttribute("logoRelatorio")%>" alt="Logomarca"/>
		 	<br>
		 	<b><%=relatorio + " " + nomeCliente + " " + periodoEmissao + " " + periodoVencimento + " " + periodoQuitacao + " " + pagamento %></b>
		 	<br><br>
		 	<table  class="relat" align="center" width = "1180px">
		 			 		
		 		<tr class="relat">
				<th class="grid tam4">Código</th>
				<th class="grid tam4">Nº Nota</th>
				<th class="grid tam12">Cliente</th>
				<th class="grid tam4">Emissão</th>
				<th class="grid tam4">Vencto.</th>
				<th class="grid tam8">F. Cobrança</th>
				<th class="grid tam4">Valor</th>				
				<th class="grid tam4">Dt. Quitação</th>
				</tr>	 		
		 		 		
		 		
		 		<%	 	 			
		 		int qtdParcelas = 0;
				double valorTotal = 0;
				int vencidos = 0;
				int vencer = 0;
				double totalVencido = 0;
				double totalVencer = 0;	
				double totalRecebido = 0;
				double totalReceber = 0;
		 		
		 		
		 			rodape = "<td colspan = \"10\">" + session.getAttribute("dadosRodape") + "</td>";
		 			
		 			
						while (itListRelCtaReceber.hasNext()) {							
							
						
							
							Parcela parcela = (Parcela)itListRelCtaReceber.next();
							
							String codigo = "000000";
							String idconta = "" + parcela.getConta().getId();
							codigo = codigo.substring(0,6-idconta.length()) + idconta;
							
							String numNota = "";
							if (parcela.getNumeroDocto()!= null) {
								numNota = parcela.getConta().getNumeroDocumento(); 
							}
							
							String nome = parcela.getConta().getPessoa().getNome();
							
							String dtEmissao = "" + dtbr.format(parcela.getDataEmissao());
							
							String dtVencimento = "" + dtbr.format(parcela.getDataVencimento());
							
							String cob = parcela.getCobranca().getNome();
							
							String valor = String.format("%.2f",parcela.getValor());
							
							String dtQuitacao = "";
							if (parcela.getDataQuitacao() != null) {
								dtQuitacao = dtbr.format(parcela.getDataQuitacao());
								totalRecebido = totalRecebido + parcela.getValor();
							} else {
								totalReceber = totalReceber + parcela.getValor();
							}
							
												
							if (dtbr.parse(dtVencimento).before(dtbr.parse(emissaoRelatorio))&& parcela.getDataQuitacao() == null) {
								vencidos = vencidos + 1;
								totalVencido = totalVencido + parcela.getValor();
							} else if (dtbr.parse(dtVencimento).after(dtbr.parse(emissaoRelatorio))&& parcela.getDataQuitacao() == null) {
								vencer = vencer + 1;
								totalVencer = totalVencer + parcela.getValor();
							}
							
							valorTotal = valorTotal + parcela.getValor();
							qtdParcelas = qtdParcelas + 1;
				if (idCobranca == 0 || parcela.getCobranca().getId() == idCobranca)	{
					
				
							
		 	   	%>	 		
				<tr class="relat">
					<td class="relat"><%=codigo%></td>
					<td class="relat"><%=numNota%></td>
					<td class="relat"><%=nome%></td>
					<td class="relat"><%=dtEmissao%></td>
					<td class="relat"><%=dtVencimento%></td>
					<td class="relat"><%=cob%></td>
		        	<td class="relat valor"><%=valor%></td>					
					<td class="relat data"><%=dtQuitacao%></td>
				</tr> 
				<% 
				}
						
				}
				
	           		
		 			emissaoRelatorio = " | Emissão: " + emissaoRelatorio;   
		 		%>	
		 				 		
		 		<tr class="relat">
					<b><%="Quant. Documentos: " + qtdParcelas + "&nbsp;&nbsp;&nbsp;&nbsp; Docs. Vencidos: " + vencidos + "&nbsp;&nbsp;&nbsp;&nbsp; Docs. a Vencer: " + vencer + "<br>"
					+ "Vlr. Vencido: R$ " + String.format("%.2f",totalVencido) +  "&nbsp;&nbsp;&nbsp;&nbsp; Vlr. a Vencer: R$ " + String.format("%.2f",totalVencer) 
					+ "&nbsp;&nbsp;&nbsp;&nbsp; Vlr. Recebido: R$ " + String.format("%.2f",totalRecebido) + "&nbsp;&nbsp;&nbsp;&nbsp;Vlr. Receber: R$ " + String.format("%.2f",totalReceber) 
					+ "&nbsp;&nbsp;&nbsp;&nbsp; Valor Total: R$ " + String.format("%.2f",valorTotal)  %></b> 
		 		</tr>
		 		<br><br>		
		 		<tr class="relat">
					<%=rodape%>
		 		</tr>
		 	</table>	 
		 </div>	
	 </div>	
	<div id="rodape">
	<img src="<%="../images/rodape.png" %>">
	</div>
</body>
</html>
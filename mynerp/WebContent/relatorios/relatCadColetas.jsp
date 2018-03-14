<%@page import="br.com.mynerp.apresentacao.facade.comercial.ColetaFacade"%>
<%@page import="br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade"%>
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

	String act = request.getParameter("act"); // 1 - filtrar, 2 - exibir relatório, 
	
	String relatorio = "Relatório: Coletas Cadastradas";
	String periodo = "";
	String cidade = "";
	String emissao = "";
	String cid = null;
	String codcliente = request.getParameter("cliente");
	String pagos = "0";
	if (request.getParameter("pagos") != null) {
		pagos = request.getParameter("pagos");
	}
	
	String naoPagos = "0";
	if (request.getParameter("naoPagos") != null) {
		naoPagos = request.getParameter("naoPagos");
	}

	String idpagamento ="3";
	String pagamento = "";
	
	String nomeCliente ="";

	Date dataInicio = null;
	Date dataFim = null;
	String rotulos ="";
	String dados = "";
	String rodape = "";
	
	PessoaFacade pessoaFacade = new PessoaFacade();
	ColetaFacade coletaFacade = new ColetaFacade();
	
	Iterator<Coleta> itListColeta = null;
	Iterator<Cliente> itListRelCliente = null;
		
	Cliente cli = null;
	
	if (codcliente!= null && !codcliente.isEmpty()) {			
			Integer idcliente = Integer.parseInt(codcliente);	
			cli = (Cliente)pessoaFacade.pesquisar(PessoaEnum.CLIENTE, idcliente);
			nomeCliente = "|	Cliente: " + cli.getNome();
			
		}

	Date x = null;
	
		 Date date = new Date();
		 emissao = " | Emissão: " +  dtbr.format(date);
		 
		 String parametro = request.getParameter("dataInicio");		 
		 if (parametro!= null && !parametro.isEmpty()) {
				dataInicio = dten.parse(parametro);
				periodo = "|	Periodo: A partir de " + dtbr.format(dataInicio);								
			}
			
		 parametro = request.getParameter("dataFim");		 
			if (parametro != null && !parametro.isEmpty()) {
				dataFim = dten.parse(parametro);
				dataFim.setHours(23);
				dataFim.setMinutes(59);
				periodo = periodo + " até " + dtbr.format(dataFim);							
			}		

			if (request.getParameter("cidade")!= null && !request.getParameter("cidade").isEmpty()) {
				cid = request.getParameter("cidade");
				cidade = "|		Local de Coleta: " + cid;	
				
			}
			
			if (pagos.equalsIgnoreCase("0") && naoPagos.equalsIgnoreCase("0")) {
				idpagamento = "4"; // nenhum
			} else if (pagos.equalsIgnoreCase("1") && naoPagos.equalsIgnoreCase("1")) {
				idpagamento = "3";
			} else if (pagos.equalsIgnoreCase("1")) {
				idpagamento = "2";
			} else if (naoPagos.equalsIgnoreCase("1")) {
				idpagamento = "1";
			}
		
		
			List<Coleta> listColeta = coletaFacade.pesquisar(dataInicio, dataFim, cli, null, cid, idpagamento, null,null); 
			rotulos = 
			" <th class=\"relat\">Código</th> <th class=\"relat\">Data</th> <th class=\"relat\">Cliente</th> <th class=\"relat\">Nome Local</th> <th class=\"relat\"> Cidade</th>"
			+"<th class=\"relat\" width=65>Nº Pedido</th> <th class=\"relat\" width=40>Nº NF</th><th class=\"relat\" width=50>Nº CT-e</th><th class=\"relat\">Volumes</th> <th class=\"relat\" width=50>Peso kg</th> <th class=\"relat\" width=55>Vr Frete</th>";
			itListColeta = listColeta.iterator();			
			
		Double valor = 0.00;
		int coletas = 0;
		
		
		switch (Integer.parseInt(idpagamento)) {
		case 1: pagamento = "| Não Pagos"; break;
		case 2: pagamento = "|Pagos"; break;
		default: pagamento = ""; break;
		}		
	
	// b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar, b7 = imprimir
				

	String b3visivel = "type = "+"hidden";			
	String b5visivel = "type = "+"image";
	String b6visivel = "type = "+"hidden";	
	String b7visivel = "";
	String exibirFiltros = "class=\"oculto\"";
		
	
	List<Cliente> listCliente = pessoaFacade.pesquisar(PessoaEnum.CLIENTE);
	Iterator<Cliente> itListCliente = listCliente.iterator();	
	%>

<body>



	<br>
	<div id = "conteudo">
		<div class="noprint">	 	
	 	<br>
	 	<hr>
		 	<div class="subtitulo">Opções de Relatório </div>
		 	<div>
		 	<br>			
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
		 	<b><%=relatorio + " " + nomeCliente + " " + periodo + " " + cidade + " " + emissao + " " + pagamento %></b>
		 	
		 	<br><br>
		 	<table  class="relat" align="center" width = "1180px">
		 			 		
		 		<tr class="relat">
		 			<%=rotulos %>	 		
		 		</tr>	 		
		 		<%	 		
		 		rodape = "<td colspan = \"11\">" +  session.getAttribute("dadosRodape") + "</td>";		 		
		 		
		 			while (itListColeta.hasNext()) {
						Coleta coleta = (Coleta)itListColeta.next();
						String codigo = "000000";
						String idcoleta = "" + coleta.getId();
						codigo = codigo.substring(0,6-idcoleta.length()) + idcoleta;
						
						String idcte = "";
						if (coleta.getIdCTE() != null) {
							idcte = "" + coleta.getIdCTE();
						}
						if (coleta.getValorFrete()!= null) {
							valor = valor + coleta.getValorFrete();
						}
						String nomeLocal = "";
						if (coleta.getNomeLocal() != null) {nomeLocal = coleta.getNomeLocal();}
						String cidadeColeta = "";
						if (coleta.getCidade() != null) {cidadeColeta = coleta.getCidade();}
						String pedido = "";
						if (coleta.getNumeroPedido() != null) {pedido = coleta.getNumeroPedido();}
						String nota = "";
						if (coleta.getNumeroNota() != null) {nota = coleta.getNumeroNota();}
						String volume = "";
						if (coleta.getQuantidadeVolume() != null) {volume = ""+coleta.getQuantidadeVolume();}
						String peso = "";
						if (coleta.getPeso() != null) {volume = ""+coleta.getPeso();}
						
						coletas = coletas + 1;
						
		 	   	%>	 		
				<tr class="relat">
					<td class="relat"><%=codigo%></td>
					<td class="relat"><%=dtbr.format(coleta.getData())%></td>
					<td class="relat"><%=coleta.getCliente().getNome()%></td>
		        	<td class="relat"><%=nomeLocal%></td>
					<td class="relat"><%=cidadeColeta%></td>
					<td class="relat"><%=pedido%></td>
					<td class="relat"><%=nota%></td>
					<td class="relat"><%=idcte%></td>
					<td class="relat"><%=volume%></td>
					<td class="relat"><%=peso%></td>
					<td class="relat"><%=String.format("%.2f",coleta.getValorFrete())%></td>
				</tr> 
				<% 
	           		}
		 			
		 			String valorTotal = " <tr class=\"relat\"> <td colspan=2><b>Coletas: " + coletas + "</b></td> <td colspan=2><b> Valor total: R$ " + String.format("%.2f",valor) + "</b></td></tr>";
		 		
		 		%>	 
		 		
		 		<%=valorTotal%>	 				
		 		<tr class="relat">
					<%=rodape%>
		 		
		 	</table>	 
		 </div>	
	 </div>	
	<div id="rodape">
	<img src="<%="../images/rodape.png" %>">
	</div>
</body>
</html>
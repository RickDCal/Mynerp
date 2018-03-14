<%@page import="br.com.mynerp.apresentacao.facade.cadastro.ContatoFacade"%>
<%@page import="br.com.mynerp.apresentacao.facade.cadastro.EnderecoFacade"%>
<%@page import="br.com.mynerp.apresentacao.facade.cadastro.PessoaFacade"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<link type="text/css" rel="stylesheet" media="print" href="../css/printRelatorio.css">

<script type="text/javascript" src="../js/jsGlobal.js"></script>
<script type="text/javascript" src="../js/jsRelatorios.js"></script>
</head>

<%	

	SimpleDateFormat dtbr = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dten = new SimpleDateFormat("yyyy-MM-dd");
	
	String  relatorio = "Relatório: Cadastro de Clientes";
	String periodo = "";
	String cidade = "";
	String emissao = "";
	String cid = null;
	Date dataInicio = null;
	Date dataFim = null;
	String rotulos ="";
	String dados = "";
	String rodape = "";
	
	PessoaFacade pessoaFacade = new PessoaFacade();
	
	Iterator<Cliente> itListRelCliente = null;
		
	Cliente cli = null;
		
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
				cidade = "|		Cidade: " + cid;				
			}		
			
			List<Cliente> listRelCliente = pessoaFacade.pesquisar(PessoaEnum.CLIENTE, dataInicio, dataFim, cid); 
 			itListRelCliente = listRelCliente.iterator();
			rotulos = 
			" <th class=\"relat\">Código</th> <th class=\"relat\">F/J</th> <th class=\"relat\">Nome - Razão Social</th> <th class=\"relat\">E-mail</th> <th class=\"relat\">Telefone</th>"
			+"<th class=\"relat\">Celular</th> <th class=\"relat\">Endereço</th><th class=\"relat\">Vencto.</th>";
			
	
	
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
	 	<br>
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
		 	<b><%=relatorio + " "  + periodo + " " + cidade + " " + emissao %></b>
		 	<br><br>
		 	<table  class="relat" align="center" width = "1180px">
		 			 		
		 		<tr class="relat">
		 			<%=rotulos %>	 		
		 		</tr>	 		
		 		<%	 	 			
		 			
		 			rodape = "<td colspan = \"10\">" + session.getAttribute("dadosRodape") + "</td>";
		 			while (itListRelCliente.hasNext()) {
						Cliente cadCliente = (Cliente)itListRelCliente.next();
						
						String endCliente = "";
						
						EnderecoFacade endFacade = new EnderecoFacade();						
						
						Endereco endPrincipal = endFacade.pesquisarPrincipal(cadCliente.getId());
						
						if (endPrincipal != null) {
							endCliente = endPrincipal.toString();
						}
												
						String pfpj = "";
						if (cadCliente.getPfPj()!= null && cadCliente.getPfPj() ==1) {
							pfpj = "F";
						} else pfpj = "J";
						String codigo = "000000";
						String idcliente = "" + cadCliente.getId();
						codigo = codigo.substring(0,6-idcliente.length()) + idcliente;
						
						String fantasia = "";
						String telefone ="";
						String vencto = "";
						String email = "";
						String celular = "";
												
						if (cadCliente.getNomeFantasia() != null){
							fantasia = cadCliente.getNomeFantasia();
						}
						
						ContatoFacade contFacade = new ContatoFacade();
						
						Contato contatoPrinc = contFacade.pesquisarPrincipal(cadCliente.getId());
						
						if (contatoPrinc != null && (contatoPrinc.getDdd() != null || contatoPrinc.getTelefone() != null)) {
							telefone = contatoPrinc.getDdd() + "-" + contatoPrinc.getTelefone();
						}
						
						if (contatoPrinc != null && (contatoPrinc.getDddCelular() != null || contatoPrinc.getCelular() != null)) {
							celular = contatoPrinc.getDddCelular() + "-" + contatoPrinc.getCelular();
						}
						
						if (contatoPrinc != null && contatoPrinc.getEmail() != null ) {
							email = contatoPrinc.getEmail();
						}
												 
						if (cadCliente.getVencimento() != null) {
							vencto = "dia " + cadCliente.getVencimento();
						}
						
		 	   	%>	 		
				<tr class="relat">
					<td class="relat"><%=codigo%></td>
					<td class="relat"><%=pfpj%></td>
					<td class="relat"><%=cadCliente.getNome()%></td>
					<td class="relat"><%=email%></td>
					<td class="relat"><%=telefone%></td>
					<td class="relat"><%=celular%></td>
		        	<td class="relat"><%=endCliente%></td>					
					<td class="relat"><%=vencto%></td>
				</tr> 
				<% 
	           		}
		 		
		 		%>	
		 				 		
		 		
		 				
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
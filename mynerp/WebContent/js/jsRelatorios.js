/*determina uma aï¿½ï¿½o para cada botï¿½o de menu inferior*/
function botaoClicado(botao) {
	//b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar, b6 = executar
	if (botao == 1) {
		document.getElementById('idclicado').value = '1';
	}
	if (botao == 2) {
		document.getElementById('idclicado').value = '2';
	}
	if (botao == 6) {
		document.getElementById('idclicado').value = '6';
	}
	if (botao == 4) {
		resposta = confirm ("Deseja realmente remover este registro?");
		if (resposta) {
			document.getElementById('idclicado').value = '4';		
		} else {
			return false;
		}
	}	
	if (botao == 5) {
		history.go(-1);
		document.getElementById('idclicado').value = '5';
		return false;
	} else if (botao == 3) {
		resposta = confirm ("Deseja abandonar as alterações?");
		document.getElementById('idclicado').value = '3';
		if (resposta) {
		history.go(0);		
		return false;
		}		
	} 	
}

function validarFiltrosCadClientes() {
		
var dataInicio = document.getElementById("idDataInicio");
var dataFim = document.getElementById("idDataFim");
var clicado = document.getElementById("idclicado").value;
	
	if (clicado != "5" && clicado != "6") {
	return false;	
	}

	if (validaData(dataInicio) == false | validaData(dataFim)==false) {
		return false;
	}	
}

function validarFiltrosCadColetas() {
	
var dataInicio = document.getElementById("idDataInicio");
var dataFim = document.getElementById("idDataFim");
var clicado = document.getElementById("idclicado").value;
	
	if (clicado != "5" && clicado != "6") {
	return false;	
	}
	
	if (validaData(dataInicio) == false | validaData(dataFim)==false) {
		return false;
	}	
}

function validarFiltros() {
	
var rel= document.getElementById("idRelatorio").value;	
var dataInicio = document.getElementById("idDataInicio");
var dataFim = document.getElementById("idDataFim");
var clicado = document.getElementById("idclicado").value;
	
	if (clicado != "5" && clicado != "6") {
	return false;	
	}
	if (rel== "0") {
		document.getElementById('alerta1').innerText = 'Informe o tipo de relatório para continuar';
		document.getElementById('idRelatorio').focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';	
	} 

	if (validaData(dataInicio) == false | validaData(dataFim)==false) {
		return false;
	}	
}


function tipoRelatorio() {
		/*1= cadastro clientes 2= coletas cadasradas  3= financeiro*/
	if (document.getElementById('idRelatorio').value=="1") {
		document.getElementById('idcliente').style.visibility='hidden'; 		
		document.getElementById('idPagos').style.visibility='hidden';
		document.getElementById('idNaoPagos').style.visibility='hidden';
		document.getElementById('alabelperiodo').innerText="Cadastro";
	} else if (document.getElementById('idRelatorio').value=="2") {
		//document.getElementById('idcpfcnpj').innerHTML = document.getElementById('idcpfcnpj').innerHTML + 'maxlength="18"';
		document.getElementById('idcliente').style.visibility='visible';
		document.getElementById('idPagos').style.visibility='visible';
		document.getElementById('idNaoPagos').style.visibility='visible';
		document.getElementById('alabelperiodo').innerText="Período";		
	}
	
}





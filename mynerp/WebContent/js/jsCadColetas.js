
/*forma uma data para o caso de data com campos select*/
function formaData() {
	if ((document.getElementById('iddiaColeta').value=="") 
	&& (document.getElementById('idmesColeta').value =="") 
	&& (document.getElementById('idanoColeta').value =="")) {
		document.getElementById('iddataColeta').value = "";
		document.getElementById('alerta1').innerText = '';
	} else if ((document.getElementById('iddiaColeta').value!="") 
			&& (document.getElementById('idmesColeta').value!="") 
			&& (document.getElementById('idanoColeta').value!="")){
			document.getElementById('iddataColeta').value = 
			document.getElementById('idanoColeta').value + '-' 
			+ document.getElementById('idmesColeta').value + '-'
			+ document.getElementById('iddiaColeta').value;
	}else {
		document.getElementById('alerta1').innerText="A data de coleta inserida est� incompleta";
		return false;
	}	
}

function formataValor(campo){
	var valorConta = campo.value;
	valorConta = valorConta.replace(',','.');
	if (valorConta.toString().indexOf(".") == -1) {
		valorConta = valorConta.toString() + ".00";
	}
	
	campo.value = valorConta;
	
	
}



function validaDataVencimento(data) {	
	var campo = data.id;
	var geraFatura = document.getElementById("idGeraFaturamento");
	if (data.value != "" && data.value.length <10) {
		document.getElementById('alerta1').innerText = 'A data de vencimento informada est� incorreta';
		document.getElementById(campo).focus();
		return false;
	} else if (data.value == "" && geraFatura.value == "1") {
		document.getElementById('alerta1').innerText = 'Para gerar faturamento � necessario informar a data de vencimento';
		document.getElementById(campo).focus();
		return false;
	} else {
	document.getElementById('alerta1').innerText = '';
	} 
}



/*verifica se os campos obrigat�rios foram preenchidos*/
function validarColeta(botao) {
	
var cliente = cadastroColeta.cliente.value;
var local = cadastroColeta.nomeLocal.value;
var cidade = cadastroColeta.cidade.value;
var frete = cadastroColeta.vlfrete.value;
var clicado = cadastroColeta.clicado.value;

	
	if (clicado != "1" && clicado != "2" && clicado != "4") {
	return false;	
	}
	if (cliente == "") { avisoCliente(); 
	cadastroColeta.cliente.focus();
	return false;
	}
	formaData();
	if(formaData()==false){
	return false;	
	}
	validaDataVencimento(cadastroColeta.dtvenc);
	if(validaDataVencimento(cadastroColeta.dtvenc)==false){
	return false;	
	}
	if (local == "") { avisoLocal();
	cadastroColeta.nomeLocal.focus();
	return false;
	}	
	if (cidade == "") { avisoCidade();
	cadastroColeta.cidade.focus();
	return false;
	}
	if (frete == "") { avisoFrete();
	cadastroColeta.vlfrete.focus();
	return false;
	}
}

/*verifica se o cliente foi preenchido*/
function avisoCliente() {
	var cliente = cadastroColeta.cliente.value;	
	if (cliente== "") {
		document.getElementById('alerta1').innerText = 'Informe o cliente para continuar';
		cadastroColeta.cliente.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function avisoLocal() {
	var local = cadastroColeta.nomeLocal.value;	
	if (local == "") {
		document.getElementById('alerta1').innerText = 'Informe o local de coleta para continuar';
		cadastroColeta.nomeLocal.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function avisoCidade() {
	var cidade = cadastroColeta.cidade.value;	
	if (cidade == "") {
		document.getElementById('alerta1').innerText = 'Informe a cidade de coleta para continuar';
		cadastroColeta.cidade.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function avisoFrete() {
	var frete = cadastroColeta.vlfrete.value;	
	if (frete == "") {
		document.getElementById('alerta1').innerText = 'Informe o valor do frete para continuar';
		cadastroColeta.vlfrete.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function limpaFiltros(){
	document.getElementById("idCliente").selectedIndex = "-1";
	document.getElementById("dataInicial").value = "";
	document.getElementById("dataFinal").value = "";
	document.getElementById("nomeLocal").value = "";
	//document.getElementById("filtroSuperior").reset();
	//document.getElementById("paginacao").reset();
	return false;
}

function igualaFiltros() {
	
	var dataI = filtroSuperior.dataInicial.value;
	var dataF = filtroSuperior.dataFinal.value;
	var idCliente = filtroSuperior.idCliente.value;
	var nomeLocal = filtroSuperior.nomeLocal.value;
	
	document.getElementById("dataInicial2").value = dataI;
	document.getElementById("dataFinal2").value = dataF;
	document.getElementById("idCliente2").value = idCliente;
	document.getElementById("nomeLocal2").value = nomeLocal;
	document.getElementById("idpos").value = 0;
	
}




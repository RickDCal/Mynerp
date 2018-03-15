/*determina uma aï¿½ï¿½o para cada botï¿½o de menu inferior*/
function botaoClicado(botao) {
	//b1 = cadastrar, b2 = salvar, b3 cancelar, b4 = excluir, b5 = voltar
	if (botao == 1) {
		document.getElementById('idclicado').value = '1';
	}
	if (botao == 2) {
		document.getElementById('idclicado').value = '2';
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
		resposta = confirm ("Deseja abandonar as alteraï¿½ï¿½es?");
		document.getElementById('idclicado').value = '3';
		if (resposta) {
		history.go(0);		
		return false;
		}		
	} 	
}


/*verifica o tipo de pessoa para determinar os campos visíveis*/
function tipoPessoa() {
		/*1= fisica 2= juridica*/
	if (document.getElementById('idtipo').value=="1") {
		//var tamanho = document.getElementById('idcpfcnpj').size;
		//tamanho.maxlength = '14'; tamanho.size = '14';
		document.getElementById('idcpfcnpj').maxlength = 14;
		document.getElementById('idfantasia').style.visibility='hidden'; 		
		document.getElementById('idlabelfantasia').style.visibility='hidden';		
		document.getElementById('alabelnome').innerText="Nome";
		document.getElementById('alabelcpfcnpj').innerText="CPF"; /*tbem existe o inner html*/		
		
	} else if (document.getElementById('idtipo').value=="2") {
		//document.getElementById('idcpfcnpj').innerHTML = document.getElementById('idcpfcnpj').innerHTML + 'maxlength="18"';
		document.getElementById('idfantasia').style.visibility='visible';
		document.getElementById('idlabelfantasia').style.visibility='visible';		
		document.getElementById('alabelnome').innerText="Razão Social";
		document.getElementById('alabelcpfcnpj').innerText="CNPJ";		
	}
	document.getElementById('alerta1').innerText="";
}
/*verifica se o tipo de pessoa foi preenchido*/
function verificaTipo() {
	if (document.getElementById('idtipo').value=="") {
		document.getElementById('alerta1').innerText="Selecione o tipo de pessoa para continuar";
		cadastroFornecedor.tipo.focus();
		//return false;
	}
}

function lengthTipo(inputData) {
	if (document.getElementById('idtipo').value=="1") {
		inputData.value = inputData.value.substring(0,14);
	}	
	if (document.getElementById('idtipo').value=="2") {
		inputData.value = inputData.value.substring(0,18);
	}
}

/*verifica se oos campos obrigatï¿½rios foram preenchidos*/
function validarCadastro(botao) {
	
var nome = cadastroFornecedor.nomeRazao.value;
var tipo = cadastroFornecedor.tipo.value;
var clicado = cadastroFornecedor.clicado.value;

	
	if (clicado != "1" && clicado != "2" && clicado != "4") {
	return false;	
	}	
	if (nome == "") { avisoNome(); 
	cadastroFornecedor.nomeRazao.focus();
	return false;
	}
	if (tipo == "") {
	verificaTipo();
	return false;
	}
	
}

/*verifica se o nome foi preenchido*/
function avisoNome() {
	var nome = cadastroFornecedor.nomeRazao.value;	
	if (nome== "") {
		document.getElementById('alerta1').innerText = 'Informe o nome do cliente para continuar';
		cadastroFornecedor.nomeRazao.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}



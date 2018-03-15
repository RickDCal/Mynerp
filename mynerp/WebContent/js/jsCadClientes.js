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

/*forma uma data para o caso de data com campos select*/
function formaNascimento() {
	if ((document.getElementById('dianasc').value=="") 
	&& (document.getElementById('mesnasc').value =="") 
	&& (document.getElementById('anonasc').value =="")) {
		document.getElementById('iddatanascimento').value = "";
		document.getElementById('alerta1').innerText = '';
	} else if ((document.getElementById('dianasc').value!="") 
			&& (document.getElementById('mesnasc').value!="") 
			&& (document.getElementById('anonasc').value!="")){
			document.getElementById('iddatanascimento').value = 
			document.getElementById('anonasc').value + '-' 
			+ document.getElementById('mesnasc').value + '-'
			+ document.getElementById('dianasc').value;
	}else {
		document.getElementById('alerta1').innerText="A data de nacimento inserida está incompleta";
		return false;
	}	
}

/*verifica o tipo de pessoa para determinar os campos visï¿½veis*/
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
		document.getElementById('idsexo').style.display='block';
		document.getElementById('idlabelsexo').style.display='block';		
		document.getElementById('dianasc').style.display='block'; 
		document.getElementById('labeldianasc').style.display='block';
		document.getElementById('mesnasc').style.display='block'; 
		document.getElementById('labelmesnasc').style.display='block';
		document.getElementById('anonasc').style.display='block'; 
		document.getElementById('labelanonasc').style.display='block';
	} else if (document.getElementById('idtipo').value=="2") {
		//document.getElementById('idcpfcnpj').innerHTML = document.getElementById('idcpfcnpj').innerHTML + 'maxlength="18"';
		document.getElementById('idfantasia').style.visibility='visible';
		document.getElementById('idlabelfantasia').style.visibility='visible';		
		document.getElementById('alabelnome').innerText="Razão Social";
		document.getElementById('alabelcpfcnpj').innerText="CNPJ";		
		document.getElementById('idsexo').style.display='none'; 
		document.getElementById('idlabelsexo').style.display='none';		
		document.getElementById('dianasc').style.display='none'; 
		document.getElementById('labeldianasc').style.display='none';
		document.getElementById('mesnasc').style.display='none'; 
		document.getElementById('labelmesnasc').style.display='none';		
		document.getElementById('anonasc').style.display='none'; 
		document.getElementById('labelanonasc').style.display='none';
	}
	document.getElementById('alerta1').innerText="";
}
/*verifica se o tipo de pessoa foi preenchido*/
function verificaTipo() {
	if (document.getElementById('idtipo').value=="") {
		document.getElementById('alerta1').innerText="Selecione o tipo de pessoa para continuar";
		cadastroCliente.tipo.focus();
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
	
var nome = cadastroCliente.nomeRazao.value;
var tipo = cadastroCliente.tipo.value;
var clicado = cadastroCliente.clicado.value;

	
	if (clicado != "1" && clicado != "2" && clicado != "4") {
	return false;	
	}	
	if (nome == "") { avisoNome(); 
	cadastroCliente.nomeRazao.focus();
	return false;
	}
	if (tipo == "") {
	verificaTipo();
	return false;
	}
	formaNascimento();
	if(formaNascimento()==false){
	return false;	
	}
	
}

/*verifica se o nome foi preenchido*/
function avisoNome() {
	var nome = cadastroCliente.nomeRazao.value;	
	if (nome== "") {
		document.getElementById('alerta1').innerText = 'Informe o nome do cliente para continuar';
		cadastroCliente.nomeRazao.focus();
	//return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function limpaFiltros(){
	document.getElementById("idCliente").selectedIndex = "-1";
	document.getElementById("nomeCliente").value = "";
	//document.getElementById("filtroSuperior").reset();
	//document.getElementById("paginacao").reset();
	return false;
}

function igualaFiltros() {
	
	var idCliente = filtroSuperior.idCliente.value;
	var nomeCliente = filtroSuperior.nomeCliente.value;
	
	document.getElementById("idCliente2").value = idCliente;
	document.getElementById("nomeCliente2").value = nomeCliente;
	document.getElementById("idpos").value = 0;
	
}




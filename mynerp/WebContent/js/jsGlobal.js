
/*fun��o de pagina��o do grid numero de p�gina avan�ar voltar*/
function paging(act) {
var atual = document.getElementById('idpos').value;
pag = parseInt(atual);

	if (act == 1) {
		document.getElementById('idpos').value = '0';
	} else if (act == 2) {
		if (pag > 0) {
			document.getElementById('idpos').value = pag-1;
		}
	} else if (act == 3) {
		document.getElementById('idpos').value = pag+1;
	} else if (act == 4) {
		document.getElementById('idult').value = 1;
	} 	
}

function dateMask(inputData, e){
	var tecla;
	if(document.all) // Internet Explorer
	tecla = event.keyCode;
	else //Outros Browsers
	tecla = e.which;
	if(tecla >= 47 && tecla < 58){ // numeros de 0 a 9 e '/'
	var data = inputData.value;
	//se for um numero coloca no input
	if(tecla > 47 && tecla < 58){
	if (data.length == 2 || data.length == 5){
	data += '/';
	}
	}else if(tecla == 47){ //se for a barra, so deixa colocar se estiver na posicao certa
	if (data.length != 2 && data.length != 5){
	return false;
	}
}


//atualiza o input da data
inputData.value = data;
return true;
}else if(tecla == 8 || tecla == 0) // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF
return true;
else
return false;
}

function cpfCnpjMask(inputData, e){
	var tipo = document.getElementById("idtipo").value;
	var tecla;
	if(document.all) // Internet Explorer
	tecla = event.keyCode;
	else //Outros Browsers
	tecla = e.which;
	if (tipo == 1 && tecla >= 45 && tecla < 58 && tecla != 47) {// 1 = cpf // numeros de 0 a 9, '.','-' 
		if(tecla >= 45 && tecla < 58){ 
			var data = inputData.value; //se for um numero coloca no input
			if(tecla > 47 && tecla < 58){
				if (data.length == 3 || data.length == 7){
					data += '.';
				} 
				else if(data.length == 11) {
						data += '-';	
				}
			} else if (tecla == 46){ //se for o ponto, so deixa colocar se estiver na posicao certa
				if (data.length != 3 && data.length != 7){
					return false;
				} 
			} else if (tecla == 45){
				if (data.length != 11){
					return false;
				} 	
			}			
			inputData.value = data; //atualiza o input da data
			
			return true;
		} else if (tecla == 8 || tecla == 0) { // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF
			return true;	
		} else {
			return false;	
		}
	} else if (tipo == 2 && tecla >= 45 && tecla < 58 && tecla) {// 2 = cnpj // numeros de 0 a 9, '.','-' e '/'
		if(tecla >= 45 && tecla < 58){ 
			var data = inputData.value; //se for um numero coloca no input
			if(tecla > 47 && tecla < 58){
				if (data.length == 2 || data.length == 6){
					data += '.';
				} 
				else if(data.length == 10) {
						data += '/';	
				}
				else if(data.length == 15) {
						data += '-';	
				}
			} else if (tecla == 46){ //se for o ponto, so deixa colocar se estiver na posicao certa
				if (data.length != 2 && data.length != 6){
					return false;
				} 
			} else if (tecla == 47){ //se for a barra, so deixa colocar se estiver na posicao certa
				if (data.length != 10){
					return false;
				} 	
			} else if (tecla == 45){ //se for o tra�o, so deixa colocar se estiver na posicao certa
				if (data.length != 15){
					return false;
				} 	
			}			
			inputData.value = data; //atualiza o input da data
			return true;
		} else if (tecla == 8 || tecla == 0) { // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF
			return true;	
		} else {
			return false;	
		}
	}	
}



/* função de paginação do grid filtro por letra*/
function filtroLetra(letra) {

	switch (letra){
	case "num": document.getElementById('idletra').value = "1";break;
	case "abc": document.getElementById('idletra').value = "";break;
	case "A": document.getElementById('idletra').value = "A";break;
	case "B": document.getElementById('idletra').value = "B";break;
	case "C": document.getElementById('idletra').value = "C";break;
	case "D": document.getElementById('idletra').value = "D";break;
	case "E": document.getElementById('idletra').value = "E";break;
	case "F": document.getElementById('idletra').value = "F";break;
	case "G": document.getElementById('idletra').value = "G";break;
	case "H": document.getElementById('idletra').value = "H";break;
	case "I": document.getElementById('idletra').value = "I";break;
	case "J": document.getElementById('idletra').value = "J";break;
	case "K": document.getElementById('idletra').value = "K";break;
	case "L": document.getElementById('idletra').value = "L";break;
	case "M": document.getElementById('idletra').value = "M";break;
	case "N": document.getElementById('idletra').value = "N";break;
	case "O": document.getElementById('idletra').value = "O";break;
	case "P": document.getElementById('idletra').value = "P";break;
	case "Q": document.getElementById('idletra').value = "Q";break;
	case "R": document.getElementById('idletra').value = "R";break;
	case "S": document.getElementById('idletra').value = "S";break;
	case "T": document.getElementById('idletra').value = "T";break;
	case "U": document.getElementById('idletra').value = "U";break;
	case "V": document.getElementById('idletra').value = "V";break;
	case "W": document.getElementById('idletra').value = "W";break;
	case "X": document.getElementById('idletra').value = "X";break;
	case "Y": document.getElementById('idletra').value = "Y";break;
	case "Z": document.getElementById('idletra').value = "Z";break;
	
	}
	document.getElementById('idpos').value = '0';
	 
}

/*determina uma a��o para cada bot�o de menu inferior*/
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
		resposta = confirm ("Deseja abandonar as altera��es?");
		document.getElementById('idclicado').value = '3';
		if (resposta) {
		history.go(0);		
		return false;
		}		
	} 	
}

function validaData(data) {	
	var campo = data.id;
	if (data.value != "" && data.value.length <10) {
		document.getElementById('alerta1').innerText = 'A data informada est� incorreta';
		document.getElementById(campo).focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';
	} 
}







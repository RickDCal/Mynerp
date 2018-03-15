function validarConta() {

var clicado = cadastroConta.clicado.value;
var pessoa = cadastroConta.pessoa.value;
var emissao = cadastroConta.dtEmissaoConta.value;
var valor = cadastroConta.ctaValor.value;

	
	if (clicado != "1" && clicado != "2" && clicado != "4") {
	return false;	
	}
	if (pessoa == "0" || pessoa == "") { avisoPessoa(); 
	cadastroConta.pessoa.focus();
	return false;
	}
	if (emissao == "") { avisoEmissao();
	cadastroConta.dtEmissaoConta.focus();
	return false;
	}	
	if (valor == "" || valor == 0) { avisoValor();
	cadastroConta.ctaValor.focus();
	return false;
	}
		
}

//Função responsável por inserir linhas na tabela
function inserirParcela(valorParcela, vencimento, cobranca) {
	
var table = document.getElementById("grid"); //Captura a referência da tabela com id “grid”
var numOfRows = table.rows.length; //Captura a quantidade de linhas já existentes na tabela
var numOfCols = table.rows[numOfRows-1].cells.length;//Captura a quantidade de colunas da última linha da tabela
var newRow = table.insertRow(numOfRows); //Insere uma linha no fim da tabela
var dataEmissao = document.getElementById("data").value;
var doc = document.getElementById("idnumnota").value;

	if (doc != null && doc != "") {
		doc = doc + '-' + numOfRows;
	}
    
	if (valorParcela == null) {
		valorParcela = "";
		cobranca = "";
	}else if (valorParcela.toString().indexOf(".") == -1) {
		valorParcela = valorParcela.toString() + ".00";
	}else {
		valorParcela = parseFloat(valorParcela.toFixed(2));
	}
	
	
	if (valorParcela != "" && numOfRows == 1) {
		numOfCols = numOfCols + 1;
	}		

	if (vencimento == null) {
		vencimento = "";
	}	

	for (var c = 0; c <numOfCols; c++) { //Faz um loop para criar as colunas
		newCell = newRow.insertCell(c); //Insere uma coluna na nova linha
		
		newCell.className = "grid";
		newRow.className = "grid";
		var r = numOfRows;
		var nome = 'L'+(r)+'C'+(c+1);
		var idInput = 'id' + nome;
		var options = document.getElementById('idOpcoesCobranca').value;
		
		if (c==0) {

			newCell.innerHTML ='<input type = \"text\"' + 'class=\"cell\"' + 'name=\"' + nome + '\" id=\"' + idInput + '\" value=\"'+ (r) + '\"/>'; 	

		} else if (c == (numOfCols-2)){
			newCell.innerHTML ='<input type = \"image\"' + 'src=\"images/addLinha.png\" onclick=\"inserirParcela()\"' +'id=\"' + idInput + '\"/>'; //icone insere linha
		} else if (c == (numOfCols-1)){
			newCell.innerHTML ='<input type = \"image\"' + 'src=\"images/removeLinha.png\" onclick=\"removerParcela(this)\"' +'id=\"' + idInput + '\"/>'; //icone remove linha
		}else if (c == 1){
			newCell.innerHTML ='<input type = \"date\"' + 'class=\"cell\"' + ' name=\"' + nome+ '\" id=\"' + idInput + '\" value=\"'+ dataEmissao +'\"/>'; //campos de data
		}else if (c == 2){
			newCell.innerHTML ='<input type = \"text\"' + 'class=\"cell\"' + ' name=\"' + nome + '\" id=\"' + idInput+ '\" value=\"'+ doc +'\"/>'; 
		}else if (c == 3){
			newCell.innerHTML ='<input type = \"date\"' + 'class=\"cell\"' + ' name=\"' + nome+ '\" id=\"' + idInput + '\" value=\"'+ vencimento +'\"/>'; //campos de data
		}else if (c == 4){
			newCell.innerHTML ='<input type = \"date\"' + 'class=\"cell\"' + ' name=\"' + nome+ '\" id=\"' + idInput + '\"/>'; //campos de data
		}else if (c == 5){
			newCell.innerHTML ='<input type = \"text\"' + 'class=\"cell\"' + ' name=\"' + nome + '\" id=\"' + idInput+ '\" value=\"'+ valorParcela +'\"/>'; 
		}else if (c == 6){
			newCell.innerHTML ='<select ' + 'class=\"cell\"' + ' name=\"' + nome+ '\" id=\"' + idInput + '\"/>' + 
			'\n <option value="" class=\"selected\"></option>' 
			+ options + '\\n </select>'; //campos de forma de cobrança		
		}else{
			newCell.innerHTML ='<input type = \"text\"' + 'class=\"cell\"' + ' name=\"' + nome + '\" id=\"' + idInput+ '\"/>'; 
		}
		document.getElementById('numParcelas').value = r;
		document.getElementById('idqtdParcelas').value = r;
	}

	
}

//Função responsável em receber um objeto e extrair as informações necessárias para a remoção da linha.
function removerParcela(obj) {
		var table = document.getElementById("grid");
	 	var numOfRows = table.rows.length;
		//var objInput = obj.parentNode.parentNode;
		// Capturamos a referência da TR (linha) pai do objeto        
		var objTR = obj.parentNode.parentNode;
        // Capturamos a referência da TABLE (tabela) pai da linha
        var objTable = objTR.parentNode;
        // Capturamos o índice da linha
        //var indexTR = objTR.rowIndex;
        // Chamamos o método de remoção de linha nativo do JavaScript, passando como parâmetro o índice da linha  
        var r = numOfRows;
        
        if (r > 2) {
        	//objTable.deleteRow(indexTR); assim remove a linha onde clicou
        	objTable.deleteRow(r-1);//assim remove sempre a ultima linha
        	
        	document.getElementById('numParcelas').value = r-2;
            document.getElementById('idqtdParcelas').value = r-2;
        }                
        
        // Exibe uma mensagem de confirmação da remoção
        //alert("Remoção Efetuada com Sucesso!!");
 } 

//Fonte: PORTAL EDUCAÇÃO - Cursos Online : Mais de 1000 cursos online com certificado 
//http://www.portaleducacao.com.br/informatica/artigos/6791/removendo-linhas-de-uma-tabela-com-javascript#ixzz3z7aPWljF

function gerarParcelas() {
	
var emissao = cadastroConta.dtEmissaoConta.value;	
var valorConta = document.getElementById("idctaVr").value;
var qtdParcelas = document.getElementById("idqtdParcelas").value;
var cob = document.getElementById("idcobranca");

if (emissao == "") { avisoEmissao();
cadastroConta.dtEmissaoConta.focus();
return false;
}

if (valorConta == "" || valorConta == 0) { avisoValor();
cadastroConta.ctaValor.focus();
return false;
}

if (qtdParcelas == "" || qtdParcelas == 0) { avisoNumParcelas();
cadastroConta.qtdParcelas.focus();
return false;
}
		
		var valorParcela = valorConta / qtdParcelas;
		var table = document.getElementById("grid"); //Captura a referência da tabela com id “grid”
		var numOfRows = table.rows.length;	
		
		while (numOfRows > 1) {
	    	table.deleteRow(numOfRows -1);//assim remove sempre a ultima linha até remover todas pra recriar depois
	    	numOfRows --;
	    }		
	
		for (var p = 0; p < qtdParcelas; p++) {
			
			var time = new Date(emissao);
			time.setHours(time.getHours() + 12); // evitar problemas com horario de verão
			time.setDate(time.getDate() + (30 * (p+1)));
			
			var vencimento = time.getFullYear() + "-" +("0"+(time.getMonth()+1)).slice(-2) + "-" + ("0" + time.getDate()).slice(-2);
					
			//var vencimento = dataVencimento.toLocaleDateString();//toLocaleDateString();
			inserirParcela(valorParcela, vencimento);
		}
	    for (var i = 1; i <= qtdParcelas; i++){
	    	var campo = document.getElementById('idL'+i+'C7'); 
	    	campo.selectedIndex = cob.options[cob.selectedIndex].index;
	    	
	    }	
}

function formataValor(){
	var valorConta = document.getElementById("idctaVr").value;
	valorConta = valorConta.replace(',','.');
	if (valorConta.toString().indexOf(".") == -1) {
		valorConta = valorConta.toString() + ".00";
	}
	
	document.getElementById("idctaVr").value = valorConta;
	
	
}

function validaData(data) {	
	var campo = data.id;
	if (data.value != "" && data.value.length <10) {
		document.getElementById('alerta1').innerText = 'A data informada está incorreta';
		document.getElementById(campo).focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';
	return true;
	} 
}


function avisoEmissao() {
	var emissao = cadastroConta.dtEmissaoConta.value;

	if (emissao == "") {
		document.getElementById('alerta1').innerText = 'Informe a data de emissão da conta para continuar.';
		cadastroConta.dtEmissaoConta.focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';
	return true;
	} 
}

function avisoValor() {
	var valor = cadastroConta.ctaValor.value;

	if (valor == "" || valor == 0) {
		document.getElementById('alerta1').innerText = 'Informe o valor da conta para continuar.';
		cadastroConta.ctaValor.focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';
	return true;
	} 
}

function avisoNumParcelas() {
	var qtdParcelas = document.getElementById("idqtdParcelas").value;

	if (qtdParcelas == "" || qtdParcelas == 0) {
		document.getElementById('alerta1').innerText = 'Informe a quantidade de parcelas para continuar.';
		cadastroConta.qtdParcelas.focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

/*verifica se o cliente foi preenchido*/
function avisoPessoa() {
	var pessoa = cadastroConta.pessoa.value;
	var individuo = cadastroConta.tipodePessoa.value;
	
	if (individuo =="1") {
		individuo = "fornecedor";		
	} else {
		individuo = "cliente";
	}
	
	if (pessoa== "" || pessoa== "0") {
		document.getElementById('alerta1').innerText = 'Informe o '+ individuo +  ' para continuar';
		cadastroConta.pessoa.focus();
		return false;
	}  else {
	document.getElementById('alerta1').innerText = '';

	} 
}

function limpaFiltros(){
	document.getElementById("codigo").value = "";
	document.getElementById("numeroDoc").value = "";
	document.getElementById("idPessoa").selectedIndex = "-1";
	document.getElementById("emissaoInic").value = "";
	document.getElementById("emissaoFinal").value = "";
	document.getElementById("vencInic").value = "";
	document.getElementById("vencFinal").value = "";
	//document.getElementById("filtroSuperior").reset();
	//document.getElementById("paginacao").reset();
	return false;
}

function igualaFiltros() {
	
	var codigo = filtroSuperior.codigo.value;
	var doc = filtroSuperior.numeroDoc.value;
	var idpessoa = filtroSuperior.idPessoa.value;
	var emissaoI = filtroSuperior.emissaoInic.value;
	var emissaoF = filtroSuperior.emissaoFinal.value;
	var vencI = filtroSuperior.vencInic.value;
	var vencF = filtroSuperior.vencFinal.value;
	
	document.getElementById("codigo2").value = codigo;
	document.getElementById("numeroDoc2").value = doc;
	document.getElementById("idPessoa2").value = idpessoa;
	document.getElementById("emissaoInic2").value = emissaoI;
	document.getElementById("emissaoFinal2").value = emissaoF;
	document.getElementById("vencInic2").value = vencI;
	document.getElementById("vencFinal2").value = vencF;
	document.getElementById("idpos").value = 0;
	
}
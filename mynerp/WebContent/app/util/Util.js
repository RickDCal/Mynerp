Ext.define('Mynerp.util.Util', {

	requires: [
        'Ext.window.Toast'
    ],
    
	statics: {
		decodeJSON : function (text) {
			
		var result = Ext.JSON.decode(text, true);
		
		if (!result) {//se o json for inválido, ou seja, objeto nulo
			result = {};//cria um result novo
			result.success = false;//adiciona propriedades nele
			result.msg = text;//adiciona o texto que recebeu o que recebeu qdo não for um json válido
		}
		return result; //é o result que veio json válido do servidor ou o que criamos na função acima
		},

		showErrorMsg: function(text) {
			Ext.Msg.show({
				title: 'Erro',
				msg: text,
				icon: Ext.Msg.ERROR,
				buttons: Ext.Msg.OK
			});
		},

		showAlertMsg: function(text) {
			Ext.Msg.show({
				title: 'Atenção',
				msg: text,
				icon: Ext.Msg.WARNING,
				buttons: Ext.Msg.OK
			});
		},

		showInfoMsg: function(text) {
			Ext.Msg.show({
				title: 'Informação',
				msg: text,
				icon: Ext.Msg.INFO,
				buttons: Ext.Msg.OK
			});
		},

		showToast: function(text) {

			Ext.toast({
				html: text,
				closable: false,
				align: 't',
				slideInDuration: 450, //tempo da descida do slide, não o tempo de exibição na tela.
				minWidth: 400
			});
		},

		handleFormFailure: function(action) {
			var me = this,
			result = Mynerp.util.Util.decodeJSON(action.response.responseText);

			switch (action.failureType) {
				case Ext.form.action.Action.CLIENT_INVALID:
					 me.showErrorMsg('Os dados enviados no formulário são inválidos.');
				break;
				case Ext.form.action.Action.CONNECT_FAILURE:
					 me.showErrorMsg(action.response.responseText);
				break;
				case Ext.form.action.Action.SERVER_INVALID:
					 me.showErrorMsg(result.msg);
				break;
				default: me.showErrorMsg(result.mensagemRetorno);
				break;
				}
		},

		
		required: '<span style="color:red; font-weight:bold" data-qtip="Required">*</span>',

		formataCpfCnpj: function (inputData, e, tipo) {

			var tecla = e.getKey();
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

					if (data.length >= 14) {
						data = data.substring(0,13);
					} 
					inputData.setValue(data);			
					//inputData.value = data; //atualiza o input da data					
					
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

					if (data.length >= 18) {
						data = data.substring(0,17);
					} 
					inputData.setValue(data);

					return true;
				} else if (tecla == 8 || tecla == 0) { // Backspace, Delete e setas direcionais(para mover o cursor, apenas para FF
					return true;	
				} else {
					return false;	
				}
			} else if (!tipo) {
				inputData.setValue('');
				return false;
			}	
		}

	}
})
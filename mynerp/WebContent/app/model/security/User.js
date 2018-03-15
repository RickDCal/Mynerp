Ext.define('Mynerp.model.security.User', {
	extend: 'Mynerp.model.security.Base',
	
	entityName: 'User', // security.User

	fields: [

	{name: 'apelido'},
	{name: 'user'},
	{name: 'email'},
	{name: 'nomeArquivoFoto'},
	{name: 'idPerfil', type: 'int'},
	{name: 'nomePerfil', type:'string', persist:false,
            convert:function(v, rec){
                var data = rec.data;
                if (data.group && data.group.name){
                    return data.group.name;
                }
                return data.idPerfil;
            }
        }	
	],

	validators: {
		name: [
			{type: 'presence', text: 'Este campo é obrigatório'},
			{type: 'length', min: 3, max: 100}
		],
		username: [
			{type: 'exclusion', list: ['Admin','Operator']},
			{type: 'format', matcher: /([a-z]+)/i},
			{type: 'presence', message: 'Este campo é obrigatório'},
			{type: 'length', min: 3, max: 25}
		],
		email: [
			{type: 'presence', message: 'Este campo é obrigatório'},
			{type: 'length', min: 5, max: 100},
			{type: 'email'}],
		idPerfil: 'presence' //só uma validação neste campo não precisa ser array
	}
});
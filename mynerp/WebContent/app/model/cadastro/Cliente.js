Ext.define('Mynerp.model.cadastro.Cliente', {
	extend: 'Mynerp.model.cadastro.Base',
	
	entityName: 'Cliente', // security.User

	fields: [

	{name: 'nomeFantasia'},
	{name: 'cpfCnpj'},
	{name: 'pfPj', type: 'int'},
	{name: 'idsexo', type: 'int'},
	{name: 'nascimento', type: 'date'},

	{name: 'email'},
	{name: 'ddd' },
	{name: 'telefone'},
	{name: 'dddCelular'},
	{name: 'celular'},
	{name: 'dddAdicional'},
	{name: 'telefoneAdicional'},
	
	{name: 'tipoLogradouro'},
	{name: 'logradouro'},
	{name: 'numero'},
	{name: 'complemento'},
	{name: 'bairro'},
	{name: 'ufSigla'},
	{name: 'nomeCidade'},
	{name: 'cep'},

	{name: 'vencimento', type: 'int'},
	{name: 'dataCadastro', type: 'date'}
	],

	validators: {
		// nome: [ // ver se vai funcionar pq o nome está na classe base
		// 	{type: 'presence', text: 'Este campo é obrigatório'},
		// 	{type: 'length', min: 3, max: 100}
		// ]//,
		// ddd: [
		// 	{type: 'length', min: 2, max: 3}
		// ],
		// telefone: [
		// 	{type: 'length', min: 8, max: 10}
		// ],
		// dddCelular: [
		// 	{type: 'length', min: 2, max: 3}
		// ],
		// celular: [
		// 	{type: 'length', min: 8, max: 10}
		// ]		
	}
});
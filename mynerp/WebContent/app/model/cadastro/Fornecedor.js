Ext.define('Mynerp.model.cadastro.Fornecedor', {
	extend: 'Mynerp.model.cadastro.Base',
	
	entityName: 'Fornecedor', // security.User

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
	]	
	
});
Ext.define('Mynerp.model.configuracao.ParametroFinanceiro', {
	extend: 'Mynerp.model.configuracao.Base',
	
	entityName: 'parametroFinanceiro', 

	fields: [
	{name: 'idCobranca' , type: 'int'},
	{name: 'idParametro' , type: 'int'},
	{name: 'idCondicao', type: 'int'},
	{name: 'nomeCobranca'},
	{name: 'nomeCondicao'}
	]

});
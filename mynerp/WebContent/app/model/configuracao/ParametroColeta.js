Ext.define('Mynerp.model.configuracao.ParametroColeta', {
	extend: 'Mynerp.model.configuracao.Base',
	
	entityName: 'parametroColeta', 

	fields: [
	{name: 'caminhoXmlCte'},
	{name: 'idCobranca' , type: 'int'},
	{name: 'idParametro' , type: 'int'},
	{name: 'idCondicao', type: 'int'},
	{name: 'nomeCobranca'},
	{name: 'nomeCondicao'}	]

});
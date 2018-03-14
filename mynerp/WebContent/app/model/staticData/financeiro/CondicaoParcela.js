Ext.define('Mynerp.model.staticData.financeiro.CondicaoParcela', {
	extend: 'Mynerp.model.staticData.Base',
	entityName: 'CondicaoParcela', 

	fields: [
	{name: 'idCondicao'},
    {name: 'sequencial', type: 'int'},
    {name: 'prazo', type: 'int'},
    {name: 'percentual'}   
    ] 
	
});

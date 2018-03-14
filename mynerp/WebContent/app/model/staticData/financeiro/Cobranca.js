Ext.define('Mynerp.model.staticData.financeiro.Cobranca', {
	extend: 'Mynerp.model.staticData.Base',
	
	entityName: 'Cobranca', 

	fields: [
    {name: 'isPagar', type: 'boolean'},
    {name: 'isReceber', type: 'boolean'}
    ] 
	
});

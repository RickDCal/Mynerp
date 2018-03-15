Ext.define('Mynerp.model.staticData.financeiro.CondicaoPagamento', {
	extend: 'Mynerp.model.staticData.Base',
	
	entityName: 'CondicaoPagamento', 
    

	fields: [
    {name: 'isPagar', type: 'boolean'},
    {name: 'isReceber', type: 'boolean'}
    ], 

    associations: [
        {
            type: 'hasMany',
            model: 'CondicaoParcela',
            name: 'parcelas',
            foreignKey: 'idCondicao'
        }
    ]
	
});

// Ext.define('Mynerp.model.staticData.financeiro.CondicaoPagamento', {
//     extend: 'Ext.data.Model',
    
//     //entityName: 'CondicaoPagamento', 
//     idProperty: 'id',

//     fields: [{name: 'id'},
//     {name: 'isPagar', type: 'boolean'},
//     {name: 'isReceber', type: 'boolean'}
//     ], 

    
//     // associations: [
//     //     {
//     //         type: 'hasMany',
//     //         model: 'CondicaoParcela',
//     //         name: 'parcelas',
//     //         foreignKey: 'idConta'
//     //     }
//     // ]
    
// });

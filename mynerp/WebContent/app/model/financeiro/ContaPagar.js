Ext.define('Mynerp.model.financeiro.ContaPagar', {
	extend:'Mynerp.model.financeiro.Conta',
	
	entityName: 'ContaPagar', 

    associations: [
        {
            type: 'hasOne',
            model: 'Fornecedor',
            name: 'fornecedor',
            foreignKey: 'idPessoa'
        },
        {
            type: 'hasMany',
            model: 'ParcelaPagar',
            name: 'parcelas',
            foreignKey: 'idConta'
        }

    ]
    
});

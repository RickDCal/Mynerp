Ext.define('Mynerp.model.financeiro.ContaReceber', {
	extend:'Mynerp.model.financeiro.Conta',
	
	entityName: 'ContaReceber', 

    associations: [
        {
            type: 'hasOne',
            model: 'Cliente',
            name: 'cliente',
            foreignKey: 'idPessoa'
        },
        {
            type: 'hasMany',
            model: 'ParcelaReceber',
            name: 'parcelas',
            foreignKey: 'idConta'
        }

    ]
    
});

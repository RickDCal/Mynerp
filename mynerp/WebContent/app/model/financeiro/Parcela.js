Ext.define('Mynerp.model.financeiro.Parcela', {
	extend:'Mynerp.model.financeiro.Base',
	
	entityName: 'Parcela', // colocar no filho

	fields: [

    {name: 'sequencial'},
    {name: 'dataEmissao', type: 'date'},
    {name: 'dataVencimento', type: 'date'},
    {name: 'dataQuitacao', type: 'date'},
    {name: 'valor'},
    {name: 'numeroDocto'},
    {name: 'idConta'},
    {name: 'idCobranca'},
    

    { name:'nomeCobranca', type:'string', persist:false,
            convert:function(v, rec){
                var data = rec.data;
                if (data.cobranca && data.cobranca.nome){
                    return data.cobranca.nome;
            }
            //return data.idPessoa;
            return null;
        }
    },

    { name:'nomePessoa', type:'string', persist:false,
            convert:function(v, rec){
                var data = rec.data;
                //if (data.conta && data.conta.pessoa.nome){
                if (data.nomePessoa){
                    return data.nomePessoa;
                }
                //return data.idPessoa;
                return null;
            }
    }

    ]
});

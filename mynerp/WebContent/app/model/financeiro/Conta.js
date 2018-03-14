Ext.define('Mynerp.model.financeiro.Conta', {
	extend: 'Mynerp.model.financeiro.Base',
	
	entityName: 'Conta', // security.User

	fields: [

    {name: 'numeroDocumento'},
    {name: 'dataEmissaoConta', type: 'date'},
    {name: 'valorTotal'},
    {name: 'observacao'},
    {name: 'idPessoa'},
    {name: 'dataAlteracao', type: 'date'},
    {name: 'idCTE'},
    
    //campos de uso do lado cliente
     { name:'nomePessoa', type:'string', persist:false,
             convert:function(v, rec){
                var data = rec.data;
                 if (data.cliente && data.cliente.nome){
                     return data.cliente.nome;
                } else if (data.fornecedor && data.fornecedor.nome){
                     return data.fornecedor.nome;
                } else if (data.pessoa && data.pessoa.nome){
                     return data.pessoa.nome;
                }
                return data.nomePessoa;
         }
     }

    ]
});

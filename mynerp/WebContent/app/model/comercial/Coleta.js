Ext.define('Mynerp.model.comercial.Coleta', {
	extend: 'Mynerp.model.comercial.Base',
	
	entityName: 'Coleta', // security.User

	fields: [

	{name: 'nomeLocal'},
	{name: 'data', type: 'date'},
	{name: 'cidade'},
	{name: 'quilometragem'},
	{name: 'numeroPedido'},
	{name: 'dataVencimento', type: 'date'},

	{name: 'numeroNota'},
	{name: 'valorMercadoria'},
	{name: 'peso'},//, type: 'float'*
	{name: 'valorFrete'},
	{name: 'observacao'},
	{name: 'seuNumero'},

	{name: 'quantidadeVolume'},
	{name: 'idCTE'},
	{name: 'idPessoa'},
    {name: 'nomePessoa'},

	//campos de uso do lado cliente
	{name: 'indGeraCR', defaultValue: 1},
    //{name:'nomeCliente', type:'string', persist:false
    //         convert:function(v, rec){
    //             var data = rec.data;
    //             if (data.cliente && data.cliente.nome){
    //                 return data.cliente.nome;
    //         }
    //             return data.idCliente;
    //     }
    // },
    {name: 'idCobranca'},
    {name: 'nomeCobranca'}
    //{name: 'idCondicao'},
    //{name: 'nomeCondicao'},
    // { name:'nomeCobranca', type:'string', persist:false,
    //         convert:function(v, rec){
    //             var data = rec.data;
    //             if (data.cobranca && data.cobranca.nome){
    //                 return data.cobranca.nome;
    //         }
    //             return data.idCondicao;
    //     }
    // }
	]//,	

	// hasOne: [
 //        {
 //            model: 'Cliente',
 //            name: 'cliente',
 //            foreignKey:'idPessoa',
 //            associationKey: 'cliente'
 //        }
 //    ]
	
});

Ext.define('Mynerp.view.financeiro.contaPagar.quitacoes.ParcelaQuitarModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.parcelasquitarcp',

    stores: {
        
        parcelasquitarcp: {

            fields: [
                {name: 'id'},
                {name: 'numeroDocto', persist: false}, 
                {name: 'dataVencimento', persist: false},
                {name: 'dataEmissao', persist: false},
                {name: 'dataQuitacao', type: 'date'},
                {name: 'nomePessoa', persist: false},
                {name: 'idCTE', persist: false},
                {name: 'nomeCobranca', type:'string', persist:false,
                    convert:function(v, rec){
                    var data = rec.data;
                    if (data.cobranca && data.cobranca.nome){
                        return data.cobranca.nome;
                        } 
                    return data.nomeCobranca;
                    }   
                }
            ], 

            proxy: {
                type: 'ajax',
                api :{
                    read: 'parcelapagarServlet?action=2',
                    //create: '{entityName:lowercase}Servlet?action=1',////create: '{prefix}/{entityName: lowercase}/create.php'//
                    update: 'parcelapagarServlet?action=5'// 5 = quitar
                    //destroy: '{entityName:lowercase}Servlet?action=4'//destroy: '{prefix}/{entityName: lowercase}/destroy.php'//
                },
                reader: {
                    type: 'json',
                    rootProperty: 'data'
                },
                writer: {
                    //type: 'associatedjson',
                    type: 'json',
                    writeAllFields: false, 
                    //writeAllFields false fará com que somente os campos persistiveis sejam enviados e os criticos tbem. neste caso só vai o id e data de quitação.
                    encode: true,
                    rootProperty: 'data',
                    allowSingle: false,
                    dateFormat: "Y-m-d H:i:s"
                },
                // listeners: {
                //     exception: function(proxy, response, operation){

                //         result = Mynerp.util.Util.decodeJSON(response.responseText); 
                //         if (result.mensagemRetorno) {
                //             Mynerp.util.Util.showErrorMsg(result.mensagemRetorno);
                //         } else {
                //             Mynerp.util.Util.showErrorMsg(response.responseText);
                //         }                   
                //     }
                // },
                filterParam: 'filter' // Parameter name to send filtering information in-- era query
            },

            remoteFilter: true,

            filters: [
                {property: 'idPagamento',value: 2}
            ],

            pageSize: 50,

            session: false,

            autoLoad: false

        }     
    }
});
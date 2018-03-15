Ext.define('Mynerp.view.listaSelecao.cobranca.ListaCobrancasGrid', {
    extend: 'Mynerp.view.listaSelecao.base.Grid',
    xtype: 'lista-cobrancas-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.staticData.financeiro.Cobranca',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.listaSelecao.cobranca.ListaCobrancasModel'
    ],

    viewModel: {
        type: 'listacobrancas'  
    },

    controller: 'listaselecao',

    bind : '{listacobrancas}',

    session: true,

    reference: 'listaCobrancasGrid',

    columns: [ ],           //só as duas colunas que já estão no grid base    

    dockedItems: [
        {
            dock: 'top',
            xtype: 'toolbar',//barra com o campo de filtro
            defaults: {
                xtype: 'mysearchfield',
                storeName: 'lista-cobrancas-grid' 
            },

            items: [

                {
                    flex:1,
                    fieldLabel: 'Nome',
                    labelWidth: 50,                    
                    filterName: 'nome'                                        
                }
            ]
        },
        {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            bind : {
                store: '{listacobrancas}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando Formas de Cobrança {0} - {1} of {2}',
            emptyMsg: "Sem formas de cobranca para mostrar."
        }
    ]

    
});
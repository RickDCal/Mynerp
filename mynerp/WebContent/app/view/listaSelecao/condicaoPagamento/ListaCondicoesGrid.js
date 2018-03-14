Ext.define('Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesGrid', {
    extend: 'Mynerp.view.listaSelecao.base.Grid',
    xtype: 'lista-condicoes-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.staticData.financeiro.CondicaoPagamento',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesModel'
    ],

    viewModel: {
        type: 'listacondicoes'  
    },

    controller: 'listaselecao',

    bind : '{condicoes}',

    session: true,

    reference: 'listaCondicoesGrid',

    columns: [ ],           //só as duas colunas que já estão no grid base    

    dockedItems: [
        {
            dock: 'top',
            xtype: 'toolbar',//barra com o campo de filtro
            defaults: {
                xtype: 'mysearchfield',
                storeName: 'lista-condicoes-grid' 
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
                store: '{condicoes}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando Confições de Pagamento {0} - {1} de {2}',
            emptyMsg: "Sem condições de pagamento para mostrar."
        }
    ]

    
});
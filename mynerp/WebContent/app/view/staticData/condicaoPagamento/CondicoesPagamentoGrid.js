Ext.define('Mynerp.view.staticData.condicaoPagamento.CondicoesPagamentoGrid', {
    extend: 'Mynerp.view.cadastro.base.BaseGrid',
    xtype: 'condicoes-pagamento-grid',

    requires: [ //da erro sem isso, tive que colocar
        'Mynerp.model.staticData.financeiro.CondicaoPagamento',
        'Mynerp.model.TextCombo',
        'Mynerp.view.staticData.condicaoPagamento.CondicoesPagamentoModel',
        'Mynerp.model.staticData.financeiro.CondicaoParcela'      
    ],

    viewModel: {
        type: 'condicoesPagamento'  
    },

    controller: 'condicoesPagamento',

    bind : '{condicoesPagamento}',

    session: true,

    reference: 'condicoesGrid',

    columns: [ 
        {
            text: 'Nome',
            flex: 1,
            dataIndex: 'nome'//,
        }

    ],           //só as duas colunas que já estão no grid base    

    dockedItems: [
        {
            dock: 'top',
            xtype: 'cadastros-grid-toolbar',//barra com o campo de filtro
            defaults: {
                xtype: 'mysearchfield',
                storeName: 'condicoes-grid' 
            },

            items: [

                {
                    flex:1,
                    fieldLabel: 'Código',
                    labelWidth: 50,                    
                    filterName: 'id'                                        
                },
                {
                    flex:5,
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
                store: '{condicoesPagamento}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando Condições de Pagamento {0} - {1} of {2}',
            emptyMsg: "Sem condições de pagamento para mostrar."
        }
    ],

    listeners: {
        //itemclick: 'onItemClick',
        rowdblclick: 'onDbClick'
    }

    
});
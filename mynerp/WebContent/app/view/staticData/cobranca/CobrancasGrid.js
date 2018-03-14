Ext.define('Mynerp.view.staticData.cobranca.CobrancasGrid', {
    extend: 'Mynerp.view.cadastro.base.BaseGrid',
    xtype: 'cobrancas-grid',

    requires: [ //da erro sem isso, tive que colocar
        'Mynerp.model.staticData.financeiro.Cobranca',
        'Mynerp.model.TextCombo',
        'Mynerp.view.staticData.cobranca.CobrancasModel'
    ],

    viewModel: {
        type: 'cobrancas'  
    },

    controller: 'staticData',

    bind : '{cobrancas}',

    session: true,

    reference: 'cobrancasGrid',

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
                storeName: 'cobrancas-grid' 
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
                store: '{cobrancas}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando Formas de Cobrança {0} - {1} of {2}',
            emptyMsg: "Sem formas de cobranca para mostrar."
        }
    ],

    listeners: {
        //itemclick: 'onItemClick',
        rowdblclick: 'onDbClick'
    }

    
});
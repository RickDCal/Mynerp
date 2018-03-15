Ext.define('Mynerp.view.listaSelecao.cliente.ListaClientesGrid', {
    extend: 'Mynerp.view.listaSelecao.base.Grid',
    xtype: 'lista-clientes-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.cadastro.Cliente',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.listaSelecao.cliente.ListaClientesModel'
    ],

    viewModel: {
        type: 'listaclientes'  
    },

    controller: 'listaselecao',

    bind : '{clientes}',

    session: true,

    reference: 'listaClientesGrid',

    columns: [

        {
            text: 'Nome Fantasia',
            flex: 1,
            dataIndex: 'nomeFantasia'
        },
        {
            text: 'Cidade',
            flex:1,
            dataIndex: 'nomeCidade'
        },
        {
            text: 'UF',
            width: 60,
            dataIndex: 'ufSigla'
        }
    
    ],

    dockedItems: [
        {
            dock: 'top',
            xtype: 'toolbar',//barra com o campo de filtro
            defaults: {
                xtype: 'mysearchfield',
                storeName: 'lista-clientes-grid'
            },

            items: [

                {
                    flex:1,
                    fieldLabel: 'Nome',
                    labelWidth: 50,                    
                    filterName: 'nome'                                        
                }, 
                {
                    flex: 1,
                    fieldLabel: 'Nome Fantasia',
                    labelWidth: 100,
                    filterName: 'nomeFantasia' 
                }
            ]
        },
        {
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            bind : {
                store: '{clientes}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando clientes {0} - {1} de {2}',
            emptyMsg: "Sem clientes para Mostrar"
        }
    ]

    
});
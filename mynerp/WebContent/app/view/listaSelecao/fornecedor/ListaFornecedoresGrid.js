Ext.define('Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresGrid', {
    extend: 'Mynerp.view.listaSelecao.base.Grid',
    xtype: 'lista-fornecedores-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.cadastro.Cliente',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresModel'
    ],

    viewModel: {
        type: 'listafornecedores'  
    },

    controller: 'listaselecao',

    bind : '{fornecedores}',

    session: true,

    reference: 'listaFornecedoresGrid',

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
                storeName: 'lista-fornecedores-grid'
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
                store: '{fornecedores}'
            },
            displayInfo: true,
            displayMsg: 'Mostrando fornecedores {0} - {1} de {2}',
            emptyMsg: "Sem fornecedores para Mostrar"
        }
    ]

    
});
Ext.define('Mynerp.view.comercial.coleta.ColetasGrid', {
    extend: 'Mynerp.view.cadastro.base.BaseGrid',
    xtype: 'coletas-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.comercial.Coleta',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.comercial.coleta.ColetasModel'
    ],

    viewModel: {
        type: 'coletas'  // por algum motivo não estava reconhecendo o viewmodel
    },

    controller: 'coletas',

    bind : '{coletas}',

    session: true,

    reference: 'coletasGrid',

    columns: [

        {
            xtype: 'datecolumn',
            text: 'Data',
            width: 100,
            dataIndex: 'data',
            format: 'd/m/Y',// H:i:s',
            filter: true
        },
        {
            text: 'Cliente',
            flex: 1.5,
            dataIndex: 'nomePessoa'//,
        },        
        {
            text: 'Nome do Local',
            flex: 1.5,
            dataIndex: 'nomeLocal'
        },
        {
            text: 'Cidade',
            flex: 1,
            dataIndex: 'cidade'
        },
        {
            text: 'Nº Pedido',
            width: 100,
            dataIndex: 'numeroPedido'
        },
        {
            text: 'Nº Nota',
            width: 100,
            dataIndex: 'numeroNota'
        },
        {
            text: 'Nº CT-e',
            width: 100,
            dataIndex: 'idCTE'
        }
    ],

    dockedItems: [
            {
            dock: 'top',
            xtype: 'cadastros-grid-toolbar',//xtype: 'toolbar',
            items: [
                //{ xtype: 'tbfill' },
                {
                    flex:1,
                    fieldLabel: 'Data Inicial',
                    labelWidth: 80,
                    xtype: 'mysearchfield',
                    filterName: 'dataInicial', //para usar na filtragem como parametro
                    storeName: 'coletas-grid'
                    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                     **queira uma nova instância de uma store que tem o xtype indicado 
                     **/
                }, 
                {
                    flex: 1,
                    fieldLabel: 'Data Final',
                    labelWidth: 70,
                    xtype: 'mysearchfield',
                    filterName: 'dataFinal', //para usar na filtragem como parametro
                    storeName: 'coletas-grid'
                }, 
                //'->', 
                {
                    flex: 2,
                    fieldLabel: 'Cliente',
                    labelWidth: 50,
                    xtype: 'mysearchfield',
                    filterName: 'nomeCliente', //para usar na filtragem como parametro
                    storeName: 'coletas-grid'
                }, 
                //'->', 
                {
                    flex:2,
                    fieldLabel: 'Local',
                    labelWidth: 50,
                    xtype: 'mysearchfield',
                    filterName: 'nomeLocal', //para usar na filtragem como parametro
                    storeName: 'coletas-grid'
                },
                //'->', 
                {
                    flex:1,
                    fieldLabel: 'Pagamento',
                    labelWidth: 80,
                    xtype: 'mysearchfieldcombo',
                    filterName: 'idPagamento', //para usar na filtragem como parametro
                    storeName: 'coletas-grid',
                    valueField: 'valor',
                    displayField: 'tipo',
                    typeAhead: true,
                    queryMode: 'local',
                    forceSelection: true,
                    store: [['0','Todos'],['1','Não Quitados'],['2','Quitados']]
                }           
            
            ]
        },
        {
                dock: 'bottom',
                xtype: 'pagingtoolbar',
                bind : {
                    store: '{coletas}'
                },
                displayInfo: true,
                displayMsg: 'Mostrando coletas {0} - {1} de {2}',
                emptyMsg: "Sem coletas para Mostrar"
        }
    ]
    
    ,listeners: {
        itemclick: 'onItemClick',
        selectcoleta: 'onColetaSelect',
        rowdblclick: 'onDbClick'
    }
});
Ext.define('Mynerp.view.financeiro.contaPagar.ParcelaPagarGrid', {
    extend:'Mynerp.view.financeiro.base.BaseGrid',
    xtype: 'parcela-pagar-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.financeiro.ParcelaPagar',
        'Mynerp.model.financeiro.ContaPagar', 
        'Mynerp.model.cadastro.Fornecedor', //para funcionar atributo do modelname do lista valor.
    	'Mynerp.model.TextCombo',
        'Mynerp.view.financeiro.contaPagar.ContaPagarModel'
    ],

    bind : '{parcelaspagar}',

    reference: 'parcelaPagarGrid',

    columns: [
        {
            text: 'Nº Docto.',
            width: 100,
            dataIndex: 'numeroDocto'
        },
        {
            text: 'Fornecedor',
            flex: 1.5,
            dataIndex: 'nomePessoa'//,
        }, 
        {
            xtype: 'datecolumn',
            text: 'Dt. Emissão',
            width: 100,
            dataIndex: 'dataEmissao',
            format: 'd/m/Y',// H:i:s',
            filter: true
        },
        {
            xtype: 'datecolumn',
            text: 'Dt. Vencto.',
            width: 100,
            dataIndex: 'dataVencimento',
            format: 'd/m/Y',// H:i:s',
            filter: true
        },             
        
        {
            text: 'Forma Cobrança',
            flex: 1,
            dataIndex: 'nomeCobranca'
        },
        {
            text: 'Valor',
            width: 100,
            dataIndex: 'valor'
        },
        {
            xtype: 'datecolumn',
            text: 'Dt. Pagto.',
            width: 100,
            dataIndex: 'dataQuitacao',
            format: 'd/m/Y',// H:i:s',
            filter: true
        } 
    ],

    dockedItems: [
        {
            dock: 'top',
            xtype: 'cadastros-grid-toolbar'//,//xtype: 'toolbar',      
            
        },

        {
            dock: 'top',
            xtype: 'toolbar',

            items: [

                {
                    flex: 1.2,
                    fieldLabel: 'Nº Docto',
                    labelWidth: 60,
                    xtype: 'mysearchfield',
                    filterName: 'numeroDocto', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                },
                {
                    flex: 2.3,
                    fieldLabel: 'Fornecedor',
                    labelWidth: 70,
                    xtype: 'mysearchfield',
                    filterName: 'nomePessoa', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                }, 
                //'->', 
                {
                    flex:1.5,
                    fieldLabel: 'Data Emissão',
                    labelWidth: 90,
                    xtype: 'mysearchfield',
                    filterName: 'dataEmissaoInicial', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                     **queira uma nova instância de uma store que tem o xtype indicado 
                     **/
                }, 
                {
                    flex:1,
                    fieldLabel: 'até',
                    labelWidth: 20,
                    xtype: 'mysearchfield',
                    filterName: 'dataEmissaoFinal', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                     **queira uma nova instância de uma store que tem o xtype indicado 
                     **/
                },
                {
                    flex:1.5,
                    fieldLabel: 'Data Vencto.',
                    labelWidth: 80,
                    xtype: 'mysearchfield',
                    filterName: 'dataVencimentoInicial', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                     **queira uma nova instância de uma store que tem o xtype indicado 
                     **/
                }, 
                {
                    flex:1,
                    fieldLabel: 'até',
                    labelWidth: 20,
                    xtype: 'mysearchfield',
                    filterName: 'dataVencimentoFinal', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid'
                    /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                     **queira uma nova instância de uma store que tem o xtype indicado 
                     **/
                },                 
                //'->',  
                {
                    flex:1,
                    fieldLabel: 'Pagamento',
                    labelWidth: 75,
                    xtype: 'mysearchfieldcombo',
                    filterName: 'idPagamento', //para usar na filtragem como parametro
                    storeName: 'parcela-pagar-grid',
                    valueField: 'valor',
                    displayField: 'tipo',
                    typeAhead: true,
                    queryMode: 'local',
                    forceSelection: true,
                    store: [['0','Todos'],['1','Quitados'],['2','Não Quitados']]
                } 

            ]
        },


        {
                dock: 'bottom',
                xtype: 'pagingtoolbar',
                bind : {
                    store: '{parcelaspagar}'
                },
                displayInfo: true,
                displayMsg: 'Mostrando parcelas {0} - {1} of {2}',
                emptyMsg: "Sem parcelas para Mostrar"
        }
    ]
    
    ,listeners: {
        itemclick: 'onItemClick',
        //selectcoleta: 'onColetaSelect',
        rowdblclick: 'onDbClick'
    }
});
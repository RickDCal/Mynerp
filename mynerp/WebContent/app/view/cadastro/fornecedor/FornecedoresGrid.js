Ext.define('Mynerp.view.cadastro.fornecedor.FornecedoresGrid', {
    extend: 'Mynerp.view.cadastro.base.BaseGrid',
    xtype: 'fornecedores-grid',

    requires: [ //da erro sem isso, tive que colocar
    	'Mynerp.model.cadastro.Fornecedor',
    	'Mynerp.model.TextCombo',
        'Mynerp.view.cadastro.fornecedor.FornecedoresModel'
    ],

    viewModel: {
        type: 'fornecedores'  // por algum motivo não estava reconhecendo o viewmodel
    },

    controller: 'fornecedores',

    bind : '{fornecedores}',

    session: true,

    reference: 'fornecedoresGrid',

    columns: [

        {
            text: 'Nome',
            flex: 1,
            dataIndex: 'nome'//,
            // renderer: function(value, metaData, record ){
            //     metaData['tdAttr'] = 'data-qtip="' +
            //         record.get('description') + '"';
            //     return value;
            // }
        },
        {
            text: 'Nome Fantasia',
            flex: 1,
            //width: 100,
            dataIndex: 'nomeFantasia'//,
            // renderer: function(value, metaData, record ){
            //     var languagesStore = Ext.getStore('staticData.Languages');
            //     var lang = languagesStore.findRecord('language_id', value);
            //     return lang != null ? lang.get('name') : value;
            // }
        },
        {
            text: 'E-mail',
            width: 200,
            dataIndex: 'email'
        },
        {
            text: 'Telefone',
            width: 100,
            dataIndex: 'telefone'//,
            // renderer: function(value, metaData, record ){
            //     return value + ' min';
            // }
        },
        {
            text: 'Celular',
            width: 100,
            dataIndex: 'celular'
        },
        {
            text: 'Vencimento',
            width: 100,
            dataIndex: 'vencimento',
            renderer: function(value, metaData, record ){
                return 'dia ' + value;
            }
        }
    ],


    dockedItems: [
            {
            dock: 'top',
            xtype: 'cadastros-grid-toolbar',//xtype: 'toolbar',
            items: [
            { xtype: 'tbfill' },
            {
                width: 400,
                fieldLabel: 'Nome',
                labelWidth: 50,
                xtype: 'mysearchfield',
                filterName: 'nome', //para usar na filtragem como parametro
                storeName: 'fornecedores-grid'
                /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                 **queira uma nova instância de uma store que tem o xtype indicado 
                 **/
            }, 
            //'->', 
            {
                width: 400,
                fieldLabel: 'Nome Fantasia',
                labelWidth: 100,
                xtype: 'mysearchfield',
                filterName: 'nomeFantasia', //para usar na filtragem como parametro
                storeName: 'fornecedores-grid'
                /**OBRIGATÓRIO .... container que possui a store ou o nome da store caso 
                 **queira uma nova instância de uma store que tem o xtype indicado 
                 **/
            }
            //,'->', 
            // {
            //     xtype: 'component',
            //     itemId: 'status',
            //     tpl: 'Registros: ',//{count}',
            //     style: 'margin-right:5px'
            // }
            
            ]
        },
        {
                dock: 'bottom',
                xtype: 'pagingtoolbar',
                bind : {
                    store: '{fornecedores}'
                },
                displayInfo: true,
                displayMsg: 'Mostrando fornecedores {0} - {1} of {2}',
                emptyMsg: "Sem fornecedores para Mostrar"
        }
        ]
    //,

    // dockedItems: [
    //     
    // ]//,

    // plugins: [{
    //     ptype: 'rowexpander',
    //     rowBodyTpl: [
    //         '<b>Description:</b> {description}</br>',
    //         '<b>Special Features:</b> {special_features}</br>',
    //         '<b>Rental Duration:</b> {rental_duration}</br>',
    //         '<b>Rental Rate:</b> {rental_rate}</br>',
    //         '<b>Replacement Cost:</b> {replacement_cost}</br>'
    //     ]
    // }],

    ,listeners: {
        itemclick: 'onItemClick',
        selectfornecedor: 'onFornecedorSelect',
        rowdblclick: 'onDbClick'
    }
});
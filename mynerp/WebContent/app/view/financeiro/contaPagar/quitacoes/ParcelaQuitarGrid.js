Ext.define('Mynerp.view.financeiro.contaPagar.quitacoes.ParcelaQuitarGrid', {
    extend: 'Ext.grid.Panel',

    requires: [
    'Mynerp.util.Glyphs',
    'Mynerp.util.SearchField'
    ],   

    xtype: 'parcela-quitar-grid-cp',
    reference: 'parcelaQuitarGridCp',

    bind : '{parcelasquitarcp}',    

    selModel: {
          selType: 'checkboxmodel',
          checkOnly: true,

          listeners: {
            select: 'onSelect',
            deselect: 'onDeselect'
          }
    },

    plugins: [Ext.create('Ext.grid.plugin.CellEditing', {clicksToEdit: 1, pluginId: 'cellplugin'})],

    columns: [
        {
            text: 'Nº Docto.',
            width: 100,
            dataIndex: 'numeroDocto'
        },

        {
            text: 'Nº id',
            width: 100,
            dataIndex: 'id'
        },
        {
            text: 'NF/CT-e',
            width: 100,
            dataIndex: 'idCTE'
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
            text: 'Valor',
            width: 100,
            dataIndex: 'valor',
            align: 'right',
            formatter: 'usMoney'
        },       
        {
            text: 'Dt. Quitacão',
            dataIndex: 'dataQuitacao',
            xtype: 'datecolumn',
            //width: null,
            format: 'd/m/Y',
            editor: {
                xtype: 'datefield',
                format: 'Y-m-d H:i:s',
                minValue: '01/01/15',
                listeners: {
                    blur: 'onBlurData'
                    //change: onChangeData
                }
                //disabledDays: [0, 6],
                //disabledDaysText: 'Plants are not available on the weekends'
            }
        },
        {
            text: 'Forma Cobrança',
            flex: 1,
            dataIndex: 'nomeCobranca'
        }
    ],

    dockedItems: [
        
        {
            dock: 'top',
            xtype: 'toolbar',
            //ui: 'footer',

            defaults: { 
                xtype: 'mysearchfield',
                storeName: 'parcela-quitar-grid-cp'//'parcelasquitar' // vai pegar em todos mas nem todos precisam
            }, 

            items: [

                {
                    xtype: 'button',
                    text: 'Listar',
                    itemId: 'refresh',
                    glyph: Mynerp.util.Glyphs.getGlyph('refresh'),
                    listeners: {
                        click: 'onBuscarParcelas'
                    }               
                },
                {
                    flex: 1.2,
                    fieldLabel: 'Nº Docto',
                    labelWidth: 60,                    
                    filterName: 'numeroDocto' //para usar na filtragem como parametro
                },
                {
                    flex: 1.5,
                    fieldLabel: 'NF/CT-e',
                    labelWidth: 60,
                    filterName: 'idCTE' //para usar na filtragem como parametro
                },
                {
                    flex: 2.3,
                    fieldLabel: 'Fornecedor',
                    labelWidth: 70,
                    filterName: 'nomePessoa' //para usar na filtragem como parametro
                }, 
                //'->', 
                {
                    flex:1.5,
                    fieldLabel: 'Data Emissão',
                    labelWidth: 90,
                    filterName: 'dataEmissaoInicial' //para usar na filtragem como parametro
                }, 
                {
                    flex:1,
                    fieldLabel: 'até',
                    labelWidth: 20,
                    filterName: 'dataEmissaoFinal' //para usar na filtragem como parametro
                },
                {
                    flex:1.5,
                    fieldLabel: 'Data Vencto.',
                    labelWidth: 80,
                    filterName: 'dataVencimentoInicial' //para usar na filtragem como parametro
                }, 
                {
                    flex:1,
                    fieldLabel: 'até',
                    labelWidth: 20,
                    filterName: 'dataVencimentoFinal' //para usar na filtragem como parametro
                }
            ]
        },

        {
            dock: 'bottom',
            xtype: 'toolbar',
            ui: 'footer',
            items: [
                {
                    xtype: 'button',
                    text: 'Efetuar Quitações',
                    glyph: Mynerp.util.Glyphs.getGlyph('edit'),
                    listeners: {
                        click: 'onQuitar'
                    }
                },
                {
                    xtype: 'tbseparator'
                },
                {
                    xtype: 'tbfill'
                },
                {
                    xtype: 'textfield',
                    width:200,
                    fieldLabel: 'Registros',
                    reference: 'contaRegistros',
                    labelWidth: 55,
                    editable: false
                },
                {
                    xtype: 'textfield',
                    width:200,
                    fieldLabel: 'Valor Total',
                    reference: 'total',
                    labelWidth: 65,
                    editable: false,
                    allowDecimals: true,
                    decimalPrecision: 2
                }
            ]
            
        },


        {
                dock: 'bottom',
                xtype: 'pagingtoolbar',
                bind : {
                    store: '{parcelasquitarcp}'
                },
                displayInfo: true,
                displayMsg: 'Mostrando parcelas {0} - {1} of {2}',
                emptyMsg: "Sem parcelas para Mostrar",
                listeners: {
                    beforeChange: 'beforeChangePage'
                }
        }
    ]
    
    ,listeners: {
        itemclick: 'onItemClick'
        //rowdblclick: 'onDbClick'
    }
});
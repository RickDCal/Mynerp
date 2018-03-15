Ext.define('Mynerp.view.comercial.Coletas', {
    extend: 'Ext.panel.Panel',
    xtype: 'coletas',

    requires: [
        'Mynerp.view.comercial.coleta.ColetasGrid',
        'Mynerp.view.comercial.coleta.ColetasModel',
        'Mynerp.view.comercial.coleta.ColetasController',
        'Mynerp.model.staticData.financeiro.Cobranca',
        'Mynerp.model.configuracao.ParametroColeta'
    ],

    controller: 'coletas',
    viewModel: {
        type: 'coletas'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'coletas-grid',
        flex: 1
    }]
});
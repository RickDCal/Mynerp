Ext.define('Mynerp.view.listaSelecao.cobranca.ListaCobrancas', {
    extend: 'Ext.panel.Panel',
    xtype: 'listacobrancas',

    requires: [
        'Mynerp.view.listaSelecao.cobranca.ListaCobrancasGrid',
        'Mynerp.view.listaSelecao.cobranca.ListaCobrancasModel',
        'Mynerp.view.listaSelecao.base.ListaSelecaoController'
    ],

    controller: 'listaselecao',
    viewModel: {
        type: 'listacobrancas'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'lista-cobrancas-grid',
        flex: 1
    }],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]
});
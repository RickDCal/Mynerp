Ext.define('Mynerp.view.listaSelecao.cliente.ListaClientes', {
    extend: 'Ext.panel.Panel',
    xtype: 'listaclientes',

    requires: [
        'Mynerp.view.listaSelecao.cliente.ListaClientesGrid',
        'Mynerp.view.listaSelecao.cliente.ListaClientesModel',
        'Mynerp.view.listaSelecao.base.ListaSelecaoController'
    ],

    controller: 'listaselecao',
    viewModel: {
        type: 'listaclientes'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'lista-clientes-grid',
        flex: 1
    }],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]
});
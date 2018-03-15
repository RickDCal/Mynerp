Ext.define('Mynerp.view.listaSelecao.fornecedor.ListaFornecedores', {
    extend: 'Ext.panel.Panel',
    xtype: 'listafornecedores',

    requires: [
        'Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresGrid',
        'Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresModel',
        'Mynerp.view.listaSelecao.base.ListaSelecaoController'
    ],

    controller: 'listaselecao',
    viewModel: {
        type: 'listafornecedores'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'lista-fornecedores-grid',
        flex: 1
    }],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]
});
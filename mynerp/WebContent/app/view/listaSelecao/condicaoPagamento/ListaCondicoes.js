Ext.define('Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoes', {
    extend: 'Ext.panel.Panel',
    xtype: 'listacondicoes',

    requires: [
        'Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesGrid',
        'Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesModel',
        'Mynerp.view.listaSelecao.base.ListaSelecaoController'
    ],

    controller: 'listaselecao',
    viewModel: {
        type: 'listacondicoes'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'lista-condicoes-grid',
        flex: 1
    }],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]
});
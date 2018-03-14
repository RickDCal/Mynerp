Ext.define('Mynerp.view.cadastro.fornecedor.Fornecedores', {
    extend: 'Ext.panel.Panel',
    xtype: 'fornecedores',

    requires: [
        'Mynerp.view.base.TopToolBar',
        'Mynerp.view.cadastro.fornecedor.FornecedoresGrid',
        'Mynerp.view.cadastro.fornecedor.FornecedoresModel',
        'Mynerp.view.cadastro.fornecedor.FornecedoresController'
    ],

    controller: 'fornecedores',
    viewModel: {
        type: 'fornecedores'
    },

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'fornecedores-grid',
        flex: 1
    }],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]
});
Ext.define('Mynerp.view.financeiro.contaPagar.quitacoes.Parcelas', {
    extend: 'Ext.panel.Panel',
    xtype: 'parcelas-quitar-pagar',

    requires: [
        'Mynerp.view.base.TopToolBar',
        'Mynerp.view.financeiro.contaPagar.quitacoes.ParcelaQuitarGrid'
    ],

    viewModel: {
        type: 'parcelasquitarcp'
    },

    controller: 'quitarparcelascp',

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'parcela-quitar-grid-cp',
        flex: 1
    }]
});
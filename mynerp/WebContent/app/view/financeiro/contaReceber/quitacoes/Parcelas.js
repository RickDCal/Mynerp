Ext.define('Mynerp.view.financeiro.contaReceber.quitacoes.Parcelas', {
    extend: 'Ext.panel.Panel',
    xtype: 'parcelas-quitar-receber',

    requires: [
        'Mynerp.view.base.TopToolBar',
        'Mynerp.view.financeiro.contaReceber.quitacoes.ParcelaQuitarGrid'
    ],

    viewModel: {
        type: 'parcelasquitarcr'
    },

    controller: 'quitarparcelascr',

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'parcela-quitar-grid-cr',
        flex: 1
    }]
});
Ext.define('Mynerp.view.financeiro.contaPagar.Parcelas', {
    extend: 'Ext.panel.Panel',
    xtype: 'parcelasCP', 

    requires: [
        'Mynerp.view.base.TopToolBar',
        'Mynerp.view.financeiro.contaPagar.ParcelaPagarGrid',
        'Mynerp.view.financeiro.contaPagar.ContaPagarModel',
        'Mynerp.model.configuracao.ParametroFinanceiro'
    ],

    viewModel: {
        type: 'contaspagar'
    },

    controller: 'contaspagar',

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'parcela-pagar-grid',
        flex: 1
    }]
});
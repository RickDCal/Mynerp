Ext.define('Mynerp.view.financeiro.contaReceber.Parcelas', {
    extend: 'Ext.panel.Panel',
    xtype: 'parcelasCR',

    requires: [
        'Mynerp.view.base.TopToolBar',
        'Mynerp.view.financeiro.contaReceber.ParcelaReceberGrid',
        'Mynerp.view.financeiro.contaReceber.ContaReceberModel',
        'Mynerp.model.configuracao.ParametroFinanceiro'
    ],

    viewModel: {
        type: 'contasreceber'
    },

    controller: 'contasreceber',

    session: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'parcela-receber-grid',
        flex: 1
    }]
});
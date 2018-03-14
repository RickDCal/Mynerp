Ext.define('Mynerp.view.relatorio.financeiro.RelContasReceber', {
    extend: 'Ext.panel.Panel',
    xtype: 'rel-cad-cr',
    layout: 'fit',

    items: [{
            xtype: 'box',
            autoEl: {
                tag: 'iframe',
                src: '../mynerp/relatorios/filtrosRelatorioContasReceber.jsp'
                }
            }
        ]

});
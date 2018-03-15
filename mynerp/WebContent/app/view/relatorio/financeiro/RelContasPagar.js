Ext.define('Mynerp.view.relatorio.financeiro.RelContasPagar', {
    extend: 'Ext.panel.Panel',
    xtype: 'rel-cad-cp',
    layout: 'fit',

    items: [{
            xtype: 'box',
            autoEl: {
                tag: 'iframe',
                src: '../mynerp/relatorios/filtrosRelatorioContasPagar.jsp'
                }
            }
        ]

});
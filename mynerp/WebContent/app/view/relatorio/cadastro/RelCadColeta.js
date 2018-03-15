Ext.define('Mynerp.view.relatorio.cadastro.RelCadColeta', {
    extend: 'Ext.panel.Panel',
    xtype: 'rel-cad-coleta',
    layout: 'fit',

    items: [{
            xtype: 'box',
            autoEl: {
                tag: 'iframe',
                src: '../mynerp/relatorios/filtrosRelatorioCadColetas.jsp'
                }
            }
        ]

});
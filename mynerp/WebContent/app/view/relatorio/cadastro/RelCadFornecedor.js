Ext.define('Mynerp.view.relatorio.cadastro.RelCadFornecedor', {
    extend: 'Ext.panel.Panel',
    xtype: 'rel-cad-fornecedor',
    layout: 'fit',

    items: [{
            xtype: 'box',
            autoEl: {
                tag: 'iframe',
                src: '../mynerp/relatorios/filtrosRelatorioCadFornecedores.jsp'
                }
            }
        ]

});
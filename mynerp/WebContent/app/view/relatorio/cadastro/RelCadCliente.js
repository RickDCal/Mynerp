Ext.define('Mynerp.view.relatorio.cadastro.RelCadCliente', {
    extend: 'Ext.panel.Panel',
    xtype: 'rel-cad-cliente',
    layout: 'fit',

    items: [{
            xtype: 'box',
            autoEl: {
                tag: 'iframe',
                src: '../mynerp/relatorios/filtrosRelatorioCadClientes.jsp'
                }
            }
        ]

});
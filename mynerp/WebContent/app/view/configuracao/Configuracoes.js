Ext.define('Mynerp.view.configuracao.Panel', {
    extend: 'Ext.panel.Panel',
    xtype: 'configuracoes',
    

    requires: [
        'Mynerp.view.configuracao.ConfiguracoesModel',
        'Mynerp.model.configuracao.ParametroColeta'
    ],

    controller: 'configuracoes',
    
    viewModel: {
        type: 'configuracoes'
    },

    //session: true,

    layout: {
        type: 'border'
        //align: 'stretch'
    },

    items: [
        {
            xtype: 'config-menu',
            region: 'west'
        },
        {
            xtype: 'container',// 'panel',
            layout: {
                type: 'fit'
            },
            reference: 'panelConfiguracoes',
            region: 'center'
        }
        // {
        //     xtype: 'container',
        //     reference: 'containerConfiguracoes',
        //     region: 'center'
        // }
        // {
        //     xtype: 'appfooter',
        //     region: 'south'  
        // }        
        ]
});
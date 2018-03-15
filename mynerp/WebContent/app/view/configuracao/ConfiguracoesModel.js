Ext.define('Mynerp.view.configuracao.ConfiguracoesModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.configuracoes',

    stores: {
        menuconfiguracoes: {
            type: 'menu-configuracoes'
        },
        parametroColeta: {
            alias: 'store.parametroColeta',
            model: 'Mynerp.model.configuracao.ParametroColeta',
            pageSize: 5,
            autoLoad: true,
            session: true
        }        
    }
});
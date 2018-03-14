Ext.define('Mynerp.view.comercial.coleta.ColetasModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.coletas',

    stores: {
        coletas: {
            //type: 'buscar-clientes',
            alias: 'store.coletas',
            model: 'Mynerp.model.comercial.Coleta',
            pageSize: 25,
            autoLoad: true,
            session: true
        } ,
        parametroColeta: {
            //type: 'buscar-clientes',
            alias: 'store.parametroColeta',
            model: 'Mynerp.model.configuracao.ParametroColeta',
            pageSize: 1,
            autoLoad: true,
            session: true
        }         
    }
});
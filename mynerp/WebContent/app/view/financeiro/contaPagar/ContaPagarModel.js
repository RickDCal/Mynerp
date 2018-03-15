Ext.define('Mynerp.view.financeiro.contaPagar.ContaPagarModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.contaspagar',

    stores: {

        contaspagar: {
            //type: 'buscar-clientes',
            alias: 'store.contaspagar',
            model: 'Mynerp.model.financeiro.ContaPagar',
            pageSize: 25,
            autoLoad: true,
            session: true
        },
        parcelaspagar: {
            //type: 'buscar-clientes',
            alias: 'store.parcelaspagar',
            model: 'Mynerp.model.financeiro.ParcelaPagar',
            pageSize: 25,
            autoLoad: true,
            session: true
        },
        parametroFinanceiro: {
            //type: 'buscar-clientes',
            alias: 'store.parametroFinanceiro',
            model: 'Mynerp.model.configuracao.ParametroFinanceiro',
            pageSize: 1,
            //autoLoad: true,
            session: true
        }         
    }
});
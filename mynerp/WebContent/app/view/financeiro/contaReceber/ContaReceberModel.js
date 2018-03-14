Ext.define('Mynerp.view.financeiro.contaReceber.ContaReceberModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.contasreceber',

    stores: {

        contasreceber: {
            //type: 'buscar-clientes',
            alias: 'store.contasreceber',
            model: 'Mynerp.model.financeiro.ContaReceber',
            pageSize: 25,
            autoLoad: true,
            session: true
        },
        parcelasreceber: {
            //type: 'buscar-clientes',
            alias: 'store.parcelasreceber',
            model: 'Mynerp.model.financeiro.ParcelaReceber',
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
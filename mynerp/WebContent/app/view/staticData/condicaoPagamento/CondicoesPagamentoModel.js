Ext.define('Mynerp.view.staticData.condicaoPagamento.CondicoesPagamentoModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.condicoesPagamento',

    stores: {
        condicoesPagamento: {
            //type: 'buscar-clientes',
            alias: 'store.condicoesPagamento',
            model: 'Mynerp.model.staticData.financeiro.CondicaoPagamento',
            pageSize: 20,
            autoLoad: true,
            session: true // verificar isso
        }        
    }
    
});

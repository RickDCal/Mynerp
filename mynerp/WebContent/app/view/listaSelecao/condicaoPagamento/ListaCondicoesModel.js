Ext.define('Mynerp.view.listaSelecao.condicaoPagamento.ListaCondicoesModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.listacondicoes',

    stores: {
        condicoes: {
            //type: 'buscar-clientes',
            alias: 'store.condicoes',
            model: 'Mynerp.model.staticData.financeiro.CondicaoPagamento',
            pageSize: 20,
            autoLoad: true,
            session: false // verificar isso
        }        
    }
});
Ext.define('Mynerp.view.listaSelecao.cobranca.ListaCobrancasModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.listacobrancas',

    stores: {
        listacobrancas: {
            type: 'buscar-cobrancas'
            // alias: 'store.cobrancas',
            // model: 'Mynerp.model.staticData.financeiro.Cobranca',
            // pageSize: 20,
            // autoLoad: true,
            // session: false // verificar isso
        } ,
        cobrancas: {
             alias: 'store.cobrancas',
             model: 'Mynerp.model.staticData.financeiro.Cobranca',
             pageSize: 25,
             autoLoad: true,
             session: true // verificar isso
        }       
    }
});
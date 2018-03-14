Ext.define('Mynerp.view.listaSelecao.fornecedor.ListaFornecedoresModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.listafornecedores',

    stores: {
        fornecedores: {
            //type: 'buscar-fornecedors',
            alias: 'store.fornecedores',
            model: 'Mynerp.model.cadastro.Fornecedor',
            pageSize: 20,
            autoLoad: true,
            session: false // verificar isso
        }        
    }
});